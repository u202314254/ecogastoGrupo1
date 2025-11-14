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
import { Insignia } from '../../../models/Insignia';
import { Insigniaservice } from '../../../services/insigniaservice';
import { MatRadioModule } from '@angular/material/radio';
import { Meta } from '../../../models/Meta';
import { Metaservice } from '../../../services/metaservice';

@Component({
  selector: 'app-insigniainsertar',
  imports: [
    ReactiveFormsModule,
    MatInputModule,
    MatFormFieldModule,
    MatDatepickerModule,
    MatButtonModule,
    MatRadioModule,
    MatSelectModule 
  ],
  templateUrl: './insigniainsertar.html',
  providers: [provideNativeDateAdapter()],
  styleUrl: './insigniainsertar.css',
})
export class Insigniainsertar implements OnInit {
  form: FormGroup = new FormGroup({});
  ins: Insignia = new Insignia();
  edicion: boolean = false;
  id: number = 0;
  metas: Meta[] = [];

  constructor(
    private iS: Insigniaservice,
    private router: Router,
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private mS:Metaservice,
  ) {}

  ngOnInit(): void {
    this.route.params.subscribe((data: Params) => {
      this.id = data['id'];
      this.edicion = data['id'] != null;
      this.init();
    }); 

    this.mS.list().subscribe((data) => {
      this.metas = data;
    });

    this.form = this.formBuilder.group({
      codigo: [''],
      idMeta: ['', Validators.required],
      nombre_logro: ['', Validators.required],
      descripcion: ['', Validators.required],
      puntos: ['', Validators.required],
    });
  }

  aceptar(): void {
    if (this.form.valid) {
      this.ins.idInsignia = this.form.value.codigo;
      this.ins.meta.idMeta = this.form.value.idMeta;
      this.ins.nombre_logro = this.form.value.nombre_logro;
      this.ins.descripcion = this.form.value.descripcion;
      this.ins.puntos = this.form.value.puntos;

      if (this.edicion) {
        this.iS.update(this.ins).subscribe(() => {
          this.iS.list().subscribe((data) => this.iS.setList(data));
        });
      } else {
        this.iS.insert(this.ins).subscribe(() => {
          this.iS.list().subscribe((data) => this.iS.setList(data));
        });
      }

      this.router.navigate(['insignias']);
    }
  }

  init() {
    if (this.edicion) {
      this.iS.listId(this.id).subscribe((data) => {
        this.form = new FormGroup({
          codigo: new FormControl(data.idInsignia),
          idMeta: new FormControl(data.meta.idMeta),
          nombre_logro: new FormControl(data.nombre_logro),
          descripcion: new FormControl(data.descripcion),
          puntos: new FormControl(data.puntos),
        });
      });
    }
  }
}
