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
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatSelectModule } from '@angular/material/select';
import { provideNativeDateAdapter } from '@angular/material/core';
import { Recomendacion } from '../../../models/Recomendacion';
import { Recomendacionservice } from '../../../services/recomendacionservice';
import { MatRadioModule } from '@angular/material/radio';
import { Meta } from '../../../models/Meta';
import { Metaservice } from '../../../services/metaservice';

@Component({
  selector: 'app-recomendacioninsertar',
  imports: [
    ReactiveFormsModule,
    MatInputModule,
    MatFormFieldModule,
    MatDatepickerModule,
    MatButtonModule,
    MatRadioModule,
    MatSelectModule,
  ],
  templateUrl: './recomendacioninsertar.html',
  providers: [provideNativeDateAdapter()],
  styleUrl: './recomendacioninsertar.css',
})
export class Recomendacioninsertar implements OnInit {
  form: FormGroup = new FormGroup({});
  rec: Recomendacion = new Recomendacion();

  edicion: boolean = false;
  id: number = 0;
  
  metas: Meta[] = [];

  constructor(
    private rS: Recomendacionservice,
    private router: Router,
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private mS: Metaservice
  ) {}

  ngOnInit(): void {
    this.route.params.subscribe((data: Params) => {
      this.id = data['id'];
      this.edicion = data['id'] != null;
      this.init();

      this.mS.list().subscribe((data) => {
      this.metas = data;
    });    

      this.form = this.formBuilder.group({
        codigo: [''],
        idMeta: ['', Validators.required],
        descripcion: ['', Validators.required],
        categoria: ['', Validators.required],
        fechapublicacion: ['', Validators.required],
        fuente: ['', Validators.required],
      });
    });
  }

  aceptar(): void {
    if (this.form.valid) {
      this.rec.idRecomendacion = this.form.value.codigo;
      this.rec.meta.idMeta = this.form.value.idMeta;
      this.rec.descripcion = this.form.value.descripcion;
      this.rec.categoria = this.form.value.categoria;
      this.rec.fechapublicacion = this.form.value.fechapublicacion;
      this.rec.fuente = this.form.value.fuente;

      if (this.edicion) {
        this.rS.update(this.rec).subscribe(() => {
          this.rS.list().subscribe((data) => {
            this.rS.setList(data);
          });
        });
      } else {
        this.rS.insert(this.rec).subscribe(() => {
          this.rS.list().subscribe((data) => this.rS.setList(data));
        });
      }

      this.router.navigate(['recomendaciones']);
    }
  }

  init() {
    if (this.edicion) {
      this.rS.listId(this.id).subscribe((data) => {
        this.form = new FormGroup({
          codigo: new FormControl(data.idRecomendacion),
          idMeta: new FormControl(data.meta.idMeta),
          descripcion: new FormControl(data.descripcion),
          categoria: new FormControl(data.categoria),
          fechapublicacion: new FormControl(data.fechapublicacion),
          fuente: new FormControl(data.fuente),
        });
      });  
    }
  }
}
