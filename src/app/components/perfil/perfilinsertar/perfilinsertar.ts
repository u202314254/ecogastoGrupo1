import { Component, OnInit } from '@angular/core';
import {
  FormBuilder,
  FormControl,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatButtonModule } from '@angular/material/button';
import { Perfil } from '../../../models/Perfil';
import { Perfilservice } from '../../../services/perfilservice';
import { Usuarioservice } from '../../../services/usuarioservice';
import { provideNativeDateAdapter } from '@angular/material/core';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatRadioModule } from '@angular/material/radio';
import { MatSelectModule } from '@angular/material/select';
import { Usuario } from '../../../models/Usuario';

@Component({
  selector: 'app-perfilinsertar',
  imports: [
    ReactiveFormsModule,
    MatInputModule,
    MatFormFieldModule,
    MatDatepickerModule,
    MatButtonModule,
    MatRadioModule,
    MatSelectModule 
  ],
  templateUrl: './perfilinsertar.html',
  providers: [provideNativeDateAdapter()],
  styleUrl: './perfilinsertar.css',
})
export class Perfilinsertar implements OnInit {
  form: FormGroup = new FormGroup({});
  perfil: Perfil = new Perfil();
  edicion: boolean = false;
  id: number = 0;
  listaUsuarios: Usuario[] = [];

  generos: { value: string; viewValue: string }[] = [
    { value: 'Maculino', viewValue: 'Masculino' },
    { value: 'Femenino', viewValue: 'Femenino' },
    { value: 'Ivan Villanueva 🏳️‍🌈', viewValue: 'Ivan Villanueva 🏳️‍🌈' },
  ];
  constructor(
    private pS: Perfilservice,
    private router: Router,
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private uS: Usuarioservice
  ) {}

  ngOnInit(): void {
    this.route.params.subscribe((data: Params) => {
      this.id = data['id'];
      this.edicion = data['id'] != null;
      this.init();
    });
    this.uS.list().subscribe((data) => {
      this.listaUsuarios = data;
    });

    this.form = this.formBuilder.group({
      codigo: [''],
      idUsuario: ['', Validators.required],
      name: ['', Validators.required],
      Edad: ['', Validators.required],
      Genero: ['', Validators.required],
      Telefono: ['', Validators.required],
    });
    
  }

  aceptar(): void {
    if (this.form.valid) {
      this.perfil.idPerfil = this.form.value.codigo;
      this.perfil.usuario.idUsuario = this.form.value.idUsuario;
      this.perfil.nombre = this.form.value.name;
      this.perfil.edad = this.form.value.Edad;
      this.perfil.genero = this.form.value.Genero;
      this.perfil.telefono = this.form.value.Telefono;

      if (this.edicion) {
        this.pS.update(this.perfil).subscribe(() => {
          this.pS.list().subscribe((data) => {
            this.pS.setList(data);
          });
        });
      } else {
        this.pS.insert(this.perfil).subscribe(() => {
          this.pS.list().subscribe((data) => {
            this.pS.setList(data);
          });
        });
      }

      this.router.navigate(['perfiles']);
    }
  }

  init() {
    if (this.edicion) {
      this.pS.listId(this.id).subscribe((data) => {
        this.form = new FormGroup({
          codigo: new FormControl(data.idPerfil),
          idUsuario: new FormControl(data.usuario.idUsuario),
          name: new FormControl(data.nombre),
          Edad: new FormControl(data.edad),
          Genero: new FormControl(data.genero),
          Telefono: new FormControl(data.telefono),
        });
      });
    }
  }
}
