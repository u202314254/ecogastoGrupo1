import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { ReactiveFormsModule, FormBuilder, FormGroup } from '@angular/forms';

import { Usuarioservice } from '../../services/usuarioservice';
import { Hogarservice } from '../../services/hogarservice';

@Component({
  selector: 'app-configuracion',
  standalone: true,
  imports: [CommonModule, MatCardModule, MatIconModule, ReactiveFormsModule],
  templateUrl: './configuracion.html',
  styleUrl: './configuracion.css',
})
export class Configuracion implements OnInit {

  usuario: any = null;
  editMode = false;

  form: FormGroup;

  constructor(
    private fb: FormBuilder,
    private usuarioService: Usuarioservice,
    private hogarService: Hogarservice
  ) {
    this.form = this.fb.group({
      username: [''],
      correo: [''],
      tipohogar: [''],
      distrito: [''],
      ubicacion: [''],
      numpersonas: [''],
    });
  }

  ngOnInit(): void {
    const usernameSesion = sessionStorage.getItem('username');
    if (!usernameSesion) return;

    this.usuarioService.list().subscribe(usuarios => {

      // Buscar usuario que inició sesión
      const encontrado = usuarios.find(u => u.username === usernameSesion);
      if (!encontrado) return;

      this.usuario = encontrado;

      // 🟢 Cargar hogar por idUsuario, porque tu BD así lo guarda
      this.hogarService.listId(this.usuario.idUsuario).subscribe(hogar => {
        this.usuario.hogar = hogar;
      });

      // 🟢 PERFIL NO EXISTE → se crea vacío
      this.usuario.perfil = {
        nombre: '',
        edad: '',
        genero: '',
        telefono: ''
      };

    });
  }

  private cargarValoresEnFormulario() {
    if (!this.usuario) return;

    this.form.patchValue({
      username: this.usuario.username,
      correo: this.usuario.correo,
      tipohogar: this.usuario.hogar?.tipohogar,
      distrito: this.usuario.hogar?.distrito,
      ubicacion: this.usuario.hogar?.ubicacion,
      numpersonas: this.usuario.hogar?.numpersonas,
    });
  }

  toggleEdit() {
    this.editMode = !this.editMode;
    if (this.editMode) this.cargarValoresEnFormulario();
  }

  cancelar() {
    this.editMode = false;
  }

  guardarCambios() {

    const usuarioActualizado = {
      ...this.usuario,
      username: this.form.value.username,
      correo: this.form.value.correo
    };
    this.usuarioService.update(usuarioActualizado).subscribe();


    const hogarActualizado = {
      ...this.usuario.hogar,
      tipohogar: this.form.value.tipohogar,
      distrito: this.form.value.distrito,
      ubicacion: this.form.value.ubicacion,
      numpersonas: this.form.value.numpersonas
    };
    this.hogarService.update(hogarActualizado).subscribe();

    this.usuario = {
      ...usuarioActualizado,
      hogar: hogarActualizado
    };

    this.editMode = false;
  }
}
