import { Component, OnInit, ViewChild } from '@angular/core';
import { CommonModule, DatePipe } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { ChartDataset, ChartOptions, ChartType } from 'chart.js';
import { BaseChartDirective, provideCharts, withDefaultRegisterables } from 'ng2-charts';
import { Consumo } from '../../models/Consumo';
import { Consumoservice } from '../../services/consumoservice';
import { Router, RouterLink } from '@angular/router';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatTableModule } from '@angular/material/table';
import { MatIconModule } from '@angular/material/icon';

@Component({
  selector: 'app-miconsumo',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    BaseChartDirective,
    RouterLink,
    MatTableModule,
    MatIconModule,
    MatButtonModule,
    DatePipe,
    MatCardModule,
    MatPaginatorModule,
  ],
  templateUrl: './miconsumo.html',
  styleUrls: ['./miconsumo.css'],
  providers: [provideCharts(withDefaultRegisterables())],
})
export class MiConsumo implements OnInit {
  @ViewChild('barChart') barChart?: BaseChartDirective;
  @ViewChild('areaChart') areaChart?: BaseChartDirective;

  idUsuario = 0;
  data: Consumo[] = [];

  barType: ChartType = 'bar';
  barLabels: string[] = [];
  barData: ChartDataset[] = [];
  barOptions: ChartOptions = { responsive: true, maintainAspectRatio: false };

  areaType: ChartType = 'line';
  areaLabels: string[] = ['Ene','Feb','Mar','Abr','May','Jun','Jul','Ago','Sep','Oct','Nov','Dic'];
  areaData: ChartDataset[] = [];
  areaOptions: ChartOptions = { responsive: true, maintainAspectRatio: false };

  meses = [
    { id: 1, nombre: 'Enero' },{ id: 2, nombre: 'Febrero' },{ id: 3, nombre: 'Marzo' },
    { id: 4, nombre: 'Abril' },{ id: 5, nombre: 'Mayo' },{ id: 6, nombre: 'Junio' },
    { id: 7, nombre: 'Julio' },{ id: 8, nombre: 'Agosto' },{ id: 9, nombre: 'Septiembre' },
    { id: 10, nombre: 'Octubre' },{ id: 11, nombre: 'Noviembre' },{ id: 12, nombre: 'Diciembre' },
  ];
  mesSeleccionado = new Date().getMonth() + 1;

  anios: number[] = [];
  anioSeleccionado = new Date().getFullYear();

  totalMes = 0;
  promedioRecurso = 0;
  variacion = '0%';

  constructor(private consumoService: Consumoservice, private router: Router) {}

  ngOnInit(): void {
    const sesion = localStorage.getItem('usuarioSesion');
    if (sesion) {
      this.idUsuario = JSON.parse(sesion).idUsuario;
    }
    this.cargarDatos();
  }

  cargarDatos() {
    this.consumoService.list().subscribe((resp) => {
      this.data = resp.filter(c => c.usuario?.idUsuario === this.idUsuario);

      const min = 2020;
      const max = Math.max(new Date().getFullYear(), ...this.data.map(x => new Date(x.fecha).getFullYear()));

      this.anios = [];
      for (let a = min; a <= max; a++) this.anios.push(a);

      this.actualizarBarChart();
      this.actualizarAreaChart();
    });
  }

  actualizarBarChart() {
    const anioActual = this.anioSeleccionado;
    const mes = this.mesSeleccionado - 1;

    const consumos = this.data.filter(c => {
      const f = new Date(c.fecha);
      return f.getFullYear() === anioActual && f.getMonth() === mes;
    });

    const map = new Map<string, number>();
    consumos.forEach(c => {
      const r = c.recurso?.nombreRecurso || 'Otros';
      map.set(r, (map.get(r) || 0) + c.costo);
    });

    this.barLabels = [...map.keys()];
    const values = [...map.values()];

    this.barData = [{
      label: `Consumo en ${this.meses[this.mesSeleccionado - 1].nombre}`,
      data: values,
      backgroundColor: ['#4FC3F7', '#FDD835', '#FF7043', '#8cff66'],
      borderRadius: 8,
      barThickness: 45,
    }];

    this.totalMes = values.reduce((a, b) => a + b, 0);
    this.promedioRecurso = values.length ? +(this.totalMes / values.length).toFixed(2) : 0;

    const mesAnterior = mes === 0 ? 11 : mes - 1;
    const anioAnterior = mes === 0 ? anioActual - 1 : anioActual;

    const consAnt = this.data.filter(c => {
      const f = new Date(c.fecha);
      return f.getFullYear() === anioAnterior && f.getMonth() === mesAnterior;
    });

    const totalAnt = consAnt.reduce((a,b)=>a+b.costo,0);

    if (totalAnt > 0) {
      const v = ((this.totalMes - totalAnt) / totalAnt) * 100;
      this.variacion = `${v >= 0 ? "+" : ""}${v.toFixed(1)}%`;
    } else {
      this.variacion = "N/A";
    }

    setTimeout(() => this.barChart?.update());
  }

  actualizarAreaChart() {
    const year = this.anioSeleccionado;
    const totales = Array(12).fill(0);

    this.data.forEach(c => {
      const f = new Date(c.fecha);
      if (f.getFullYear() === year) totales[f.getMonth()] += c.costo;
    });

    this.areaData = [{
      label: `Consumo Anual ${year}`,
      data: [...totales],
      fill: true,
      tension: 0.35,
      backgroundColor: 'rgba(140,255,102,0.25)',
      borderColor: 'rgba(140,255,102,1)',
    }];

    setTimeout(() => this.areaChart?.update());
  }

  onMesChange() {
    this.actualizarBarChart();
  }

  onAnioChange() {
    this.actualizarAreaChart();
  }

  // ⭐⭐⭐ AQUI ESTÁN TUS 2 BOTONES ⭐⭐⭐
  goRegistrar() {
    this.router.navigate(['/consumos/Cnuevo']);  // tu componente de registrar
  }

  goActualizar() {
    this.router.navigate(['/consumos']); // tu listado que ya puede editar
  }

  goListar() {
    this.router.navigate(['/consumos']);
  }
}
