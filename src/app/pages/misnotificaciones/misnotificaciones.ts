import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { MatIconModule } from '@angular/material/icon';
import { Notificacionservice } from '../../services/notificacionservice';

@Component({
  selector: 'app-misnotificaciones',
  standalone: true,
  imports: [CommonModule, MatIconModule],
  templateUrl: './misnotificaciones.html',
  styleUrls: ['./misnotificaciones.css'],
})
export class Misnotificaciones implements OnInit {

  listaNotificaciones: any[] = [];

  constructor(private nS: Notificacionservice) {}

  ngOnInit() {
    this.cargarNotificaciones();
  }

  cargarNotificaciones() {
    this.nS.list().subscribe(data => {
      this.listaNotificaciones = data;
    });
  }

  eliminar(id: number) {
    this.nS.delete(id).subscribe(() => {
      this.cargarNotificaciones();
    });
  }

  toggleLeido(notificacion: any) {
    const actualizado = {
      ...notificacion,
      leido: !notificacion.leido
    };

    this.nS.update(actualizado).subscribe(() => {
      this.cargarNotificaciones();
    });
  }
}
