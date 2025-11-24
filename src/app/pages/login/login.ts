import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';

import { Usuarioservice } from '../../services/usuarioservice';
import { Usuario } from '../../models/Usuario';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [RouterLink, ReactiveFormsModule, HttpClientModule],
  templateUrl: './login.html',
  styleUrls: ['./login.css'],
})
export class Login implements OnInit {

  formLogin!: FormGroup;

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private usuarioService: Usuarioservice
  ) {}

  ngOnInit() {
    this.formLogin = this.fb.group({
      correo: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required],
    });
  }

  iniciarSesion() {
    if (this.formLogin.invalid) {
      alert('Completa los campos correctamente');
      return;
    }

    const correoIngresado = this.formLogin.value.correo.trim();
    const passwordIngresado = this.formLogin.value.password.trim();

    // PASO 1: Pedimos la lista general (que viene sin contraseña)
    this.usuarioService.list().subscribe({
      next: (listaUsuarios: Usuario[]) => {
        
        // Buscamos si existe el correo en la lista
        const usuarioEncontrado = listaUsuarios.find(u => u.correo === correoIngresado);

        if (usuarioEncontrado) {
          // PASO 2: ¡EL TRUCO! 
          // Usamos el ID para pedir los datos completos de ESTE usuario específico
          // (Tu backend sí devuelve el password cuando pides por ID)
          this.usuarioService.listId(usuarioEncontrado.idUsuario).subscribe({
            next: (usuarioCompleto: Usuario) => {
              
              // PASO 3: Validamos la contraseña con el dato que acabamos de traer
              if (usuarioCompleto.password === passwordIngresado) {
                console.log("¡Login Exitoso!");
                
                // Guardamos sesión y entramos
                localStorage.setItem('usuarioSesion', JSON.stringify(usuarioCompleto));
                this.router.navigate(['/menu']);
              } else {
                alert("Contraseña incorrecta");
              }
            },
            error: (e) => alert("Error al verificar la contraseña")
          });

        } else {
          alert("El correo no está registrado");
        }
      },
      error: (e) => {
        console.error(e);
        alert("Error de conexión con el servidor");
      }
    });
  }
}