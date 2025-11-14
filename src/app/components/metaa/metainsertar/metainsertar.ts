import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import {
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { ActivatedRoute, Params, Router } from '@angular/router';

import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatButtonModule } from '@angular/material/button';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatRadioModule } from '@angular/material/radio';
import { MatSelectModule } from '@angular/material/select';
import {
  provideNativeDateAdapter,
  MAT_DATE_LOCALE,
  MAT_DATE_FORMATS,
} from '@angular/material/core';

import { Meta } from '../../../models/Meta';
import { Metaservice } from '../../../services/metaservice';
import { Usuarioservice } from '../../../services/usuarioservice';
import { Recursoservice } from '../../../services/recursoservice';
import { Usuario } from '../../../models/Usuario';
import { Recurso } from '../../../models/Recurso';

export const ES_DATE_FORMATS = {
  parse: {
    dateInput: 'DD/MM/YYYY',
  },
  display: {
    dateInput: 'DD/MM/YYYY',
    monthYearLabel: 'MMMM YYYY',
    dateA11yLabel: 'LL',
    monthYearA11yLabel: 'MMMM YYYY',
  },
};

@Component({
  selector: 'app-metainsertar',
  standalone: true,
  templateUrl: './metainsertar.html',
  styleUrl: './metainsertar.css',
  providers: [
    provideNativeDateAdapter(),
    { provide: MAT_DATE_LOCALE, useValue: 'es-PE' },
    { provide: MAT_DATE_FORMATS, useValue: ES_DATE_FORMATS },
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatInputModule,
    MatFormFieldModule,
    MatButtonModule,
    MatDatepickerModule,
    MatRadioModule,
    MatSelectModule,
  ],
})
export class Metainsertar implements OnInit {
  form: FormGroup = new FormGroup({});
  edicion: boolean = false;
  id: number = 0;

  usuarios: Usuario[] = [];
  recursos: Recurso[] = [];

  constructor(
    private mS: Metaservice,
    private uS: Usuarioservice,
    private rS: Recursoservice,
    private router: Router,
    private formBuilder: FormBuilder,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    // Formulario: guardamos objetos usuario y recurso
    this.form = this.formBuilder.group({
      idMeta: [0],
      usuario: [null, Validators.required],
      recurso: [null, Validators.required],
      nombre: ['', Validators.required],
      estado: [true, Validators.required],
      fechainicio: ['', Validators.required],
      fechafin: ['', Validators.required],
      progreso: [
        0,
        [Validators.required, Validators.min(0), Validators.max(100)],
      ],
    });

    // Combos
    this.uS.list().subscribe((data: Usuario[]) => {
      this.usuarios = data;
    });

    this.rS.list().subscribe((data: Recurso[]) => {
      this.recursos = data;
    });

    // Ver si es edición
    this.route.params.subscribe((params: Params) => {
      this.id = params['id'];
      this.edicion = this.id != null;
      if (this.edicion) {
        this.init();
      }
    });
  }

  private formatDate(date: any): string {
    const d = new Date(date);
    const year = d.getFullYear();
    const month = (d.getMonth() + 1).toString().padStart(2, '0');
    const day = d.getDate().toString().padStart(2, '0');
    return `${year}-${month}-${day}`; // "2025-11-27"
  }

  init(): void {
    this.mS.listId(this.id).subscribe((data: Meta) => {
      this.form.patchValue({
        idMeta: data.idMeta,
        usuario: data.usuario,   // objeto completo
        recurso: data.recurso,   // objeto completo
        nombre: data.nombre,
        estado: data.estado,
        fechainicio: new Date(data.fechainicio),
        fechafin: new Date(data.fechafin),
        progreso: data.progreso,
      });
    });
  }

  aceptar(): void {
    console.log('¿Formulario válido?', this.form.valid);
    console.log('Valor del formulario:', this.form.value);

    if (this.form.invalid) {
      alert('Faltan completar datos o hay errores en el formulario.');
      return;
    }

    const v = this.form.value;
    const usuarioSel = v.usuario as Usuario;
    const recursoSel = v.recurso as Recurso;

    const metaEnviar: any = {
      idMeta: v.idMeta,
      usuario: { idUsuario: usuarioSel.idUsuario },
      recurso: { idRecurso: recursoSel.idRecurso },
      nombre: v.nombre,
      estado: v.estado,
      fechainicio: this.formatDate(v.fechainicio),
      fechafin: this.formatDate(v.fechafin),
      progreso: v.progreso,
    };

    console.log('META QUE SE ENVÍA AL BACKEND:', metaEnviar);

    if (this.edicion) {
      this.mS.update(metaEnviar).subscribe({
        next: (res) => {
          console.log('Respuesta update:', res);
          this.mS.list().subscribe((data) => {
            this.mS.setList(data);
            this.router.navigate(['/metas']);   // ruta absoluta
          });
        },
        error: (err) => {
          console.error('Error al actualizar meta', err);
          alert('Error al actualizar la meta. Revisa la consola (F12 → Console / Network).');
        },
      });
    } else {
      metaEnviar.idMeta = 0;
      this.mS.insert(metaEnviar).subscribe({
        next: (res) => {
          console.log('Respuesta insert:', res);
          this.mS.list().subscribe((data) => {
            this.mS.setList(data);
            this.router.navigate(['/metas']);   // ruta absoluta
          });
        },
        error: (err) => {
          console.error('Error al registrar meta', err);
          alert('Error al registrar la meta. Revisa la consola (F12 → Console / Network).');
        },
      });
    }
  }

  cancelar(): void {
    this.router.navigate(['/metas']);
  }
}
