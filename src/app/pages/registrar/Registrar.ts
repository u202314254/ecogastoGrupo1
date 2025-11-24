// 1. AGREGA ViewChild A LOS IMPORTS
import { Component, OnInit, AfterViewInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { BehaviorSubject, debounceTime, switchMap, of, Observable } from 'rxjs';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { AsyncPipe, CommonModule } from '@angular/common';
import * as L from 'leaflet';

// 2. IMPORTA MatStepper (NO SOLO EL MODULO)
import { MatStepperModule, MatStepper } from '@angular/material/stepper';

// ... tus importaciones de modelos y servicios ...
import { Hogar } from '../../models/Hogar';
import { Usuario } from '../../models/Usuario';
import { Perfil } from '../../models/Perfil';
import { Hogarservice } from '../../services/hogarservice';
import { Usuarioservice } from '../../services/usuarioservice';
import { Perfilservice } from '../../services/perfilservice';
import { MatError } from '@angular/material/input';
import { customEmailValidator } from '../../components/validator';

@Component({
  selector: 'app-registrar',
  standalone: true,
  imports: [ReactiveFormsModule, HttpClientModule, CommonModule, MatStepperModule, RouterLink, MatError],
  templateUrl: './registrar.html',
  styleUrls: ['./registrar.css']
})
export class Registrar implements OnInit, AfterViewInit {

  // 3. ESTA LÍNEA ES CRUCIAL: CONECTA CON EL #stepper DEL HTML
  @ViewChild('stepper') stepper!: MatStepper;

  formHogar!: FormGroup;
  formCuenta!: FormGroup;
  formPerfil!: FormGroup;

  // La variable 'paso' ya no sirve para controlar la vista, puedes borrarla si quieres.
  
  hogarCreado: Hogar | null = null;
  usuarioCreado: Usuario | null = null;

  listaHogares: Hogar[] = [];
  distritos$ = new BehaviorSubject<string[]>([]);
  ubicaciones$ = new BehaviorSubject<any[]>([]);

  // ... tus listas (listaDistritos, tipohogar) ...
  listaDistritos = [
    "Santiago de Surco", "Miraflores", "San Isidro", "Barranco", "La Molina",
    "San Borja", "Villa El Salvador", "Villa María del Triunfo",
    "San Juan de Miraflores", "San Juan de Lurigancho", "Lince",
    "Jesús María", "Cercado de Lima", "Pueblo Libre", "Rímac",
    "Los Olivos", "San Martín de Porres", "El Agustino"
  ];

  map!: L.Map;
  marker!: L.Marker;
  private mapInitialized = false;

  constructor(
    private fb: FormBuilder,
    private hogarService: Hogarservice,
    private usuarioService: Usuarioservice,
    private perfilService: Perfilservice,
    private router: Router,
    private http: HttpClient
  ) {}

  ngOnInit(): void {
    // ... tus validaciones (formHogar, formCuenta, formPerfil) ...
    this.formHogar = this.fb.group({
      distrito: ['', [Validators.required, Validators.maxLength(60)]],
      ubicacion: ['', [Validators.required, Validators.maxLength(500)]],
      tipohogar: ['', [Validators.required, Validators.maxLength(50)]],
      numpersonas: [1, [Validators.required, Validators.min(1)]]
    });

    this.formCuenta = this.fb.group({
      correo: ['', [Validators.required, Validators.maxLength(50), customEmailValidator()]],
      username: ['', [Validators.required, Validators.maxLength(50)]],
      password: ['', [Validators.required, Validators.maxLength(100)]],
      hogar: ['', Validators.required]
    });

    this.formPerfil = this.fb.group({
      nombre: ['', [Validators.required, Validators.maxLength(50)]],
      edad: ['', [Validators.required, Validators.min(18), Validators.max(80)]],
      genero: ['', [Validators.required, Validators.maxLength(50)]],
      telefono: ['', [Validators.required, Validators.pattern('^[0-9]{9}$')]]
    });

    // ... tus observables de autocompletado ...
    this.formHogar.get('distrito')!.valueChanges.pipe(debounceTime(200)).subscribe(texto => {
      if (!texto) { this.distritos$.next([]); return; }
      this.distritos$.next(this.listaDistritos.filter(d => d.toLowerCase().includes(texto.toLowerCase())));
    });

    this.formHogar.get('ubicacion')!.valueChanges.pipe(
      debounceTime(300),
      switchMap(v => this.buscarUbicacion(v))
    ).subscribe(res => this.ubicaciones$.next(res));

    this.hogarService.list().subscribe(lista => this.listaHogares = lista);
  }

  ngAfterViewInit(): void {
    setTimeout(() => this.initializeMap(), 200);
  }

  // ... tus funciones del mapa (initializeMap, buscarUbicacion, selects...) ...
  private initializeMap(): void {
    if (this.mapInitialized) return;
    this.map = L.map('map').setView([-12.031747, -77.001200], 13);
    L.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png', { maxZoom: 19 }).addTo(this.map);
    this.map.on('click', (e: L.LeafletMouseEvent) => {
      this.seleccionarUbicacionDesdeMapa(e.latlng.lat, e.latlng.lng);
    });
    this.mapInitialized = true;
  }

  buscarUbicacion(q: string): Observable<any[]> {
    if (!q || q.length < 3) return of([]);
    const url = `https://nominatim.openstreetmap.org/search?q=${encodeURIComponent(q)}&format=json&addressdetails=1&limit=5&countrycodes=pe`;
    return this.http.get<any[]>(url);
  }

  seleccionarDistrito(d: string) {

    this.formHogar.get('distrito')?.setValue(d, { emitEvent: false });

    this.distritos$.next([]);
  }
  seleccionarUbicacion(item: any) { /* ... */ }

  seleccionarUbicacionDesdeMapa(lat: number, lon: number) {
    if (this.marker) this.map.removeLayer(this.marker);
    this.marker = L.marker([lat, lon]).addTo(this.map);
    this.reverseGeocode(lat, lon);
  }

  reverseGeocode(lat: number, lon: number) {
    const url = `https://nominatim.openstreetmap.org/reverse?format=json&lat=${lat}&lon=${lon}&addressdetails=1`;
    this.http.get<any>(url).subscribe(data => {
      if (data?.display_name) this.formHogar.patchValue({ ubicacion: data.display_name });
    });
  }

  // 4. MODIFICAMOS LA FUNCIÓN SIGUIENTE
  siguiente() {
    if (this.formHogar.invalid) { 
      this.formHogar.markAllAsTouched(); 
      return; 
    }
    
    const hogar: Hogar = {
      idHogar: 0,
      distrito: this.formHogar.value.distrito,
      ubicacion: this.formHogar.value.ubicacion,
      tipohogar: this.formHogar.value.tipohogar,
      numpersonas: this.formHogar.value.numpersonas
    };

    this.hogarService.insert(hogar).subscribe(h => {
      this.hogarCreado = h;
      // Actualizamos la lista para el siguiente paso
      this.hogarService.list().subscribe(lista => {
        this.listaHogares = lista;
        
        // ¡AQUÍ ESTÁ LA CLAVE! FORZAMOS AL STEPPER A AVANZAR
        this.stepper.next(); 
      });
    });
  }

  // 5. MODIFICAMOS REGISTRAR USUARIO
  registrarUsuario() {
    if (this.formCuenta.invalid) { 
      this.formCuenta.markAllAsTouched(); 
      return; 
    }

    // Buscar el hogar seleccionado o usar el recién creado si no seleccionaron nada
    let idHogar = this.formCuenta.value.hogar;
    if(!idHogar && this.hogarCreado) {
        idHogar = this.hogarCreado.idHogar;
    }

    const hogarSeleccionado = this.listaHogares.find(h => h.idHogar === +idHogar);
    if (!hogarSeleccionado) { alert("Error: selecciona el hogar."); return; }

    const usuario: Usuario = {
      idUsuario: 0,
      estado: true,
      hogar: hogarSeleccionado,
      correo: this.formCuenta.value.correo,
      username: this.formCuenta.value.username,
      password: this.formCuenta.value.password
    };

    this.usuarioService.insert(usuario).subscribe(() => {
      this.usuarioService.list().subscribe(lista => {
        const encontrado = lista.find(u => u.correo === usuario.correo);
        if (encontrado) { 
          this.usuarioCreado = encontrado; 
          
          // AVANZAMOS AL SIGUIENTE PASO
          this.stepper.next();
        }
      });
    });
  }

  registrar() {
    if (this.formPerfil.invalid || !this.usuarioCreado) return;
    const perfil: Perfil = {
      idPerfil: 0,
      nombre: this.formPerfil.value.nombre,
      edad: this.formPerfil.value.edad,
      genero: this.formPerfil.value.genero,
      telefono: this.formPerfil.value.telefono,
      usuario: this.usuarioCreado
    };
    this.perfilService.insert(perfil).subscribe(() => {
      alert("Registro completo!");
      this.router.navigate(['login']);
    });
  }
}