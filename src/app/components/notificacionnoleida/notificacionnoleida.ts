import { Component, OnInit } from '@angular/core';
import { MatIconModule } from '@angular/material/icon';
import { ChartDataset, ChartOptions, ChartType } from 'chart.js';
import { BaseChartDirective, provideCharts, withDefaultRegisterables } from 'ng2-charts';
import { Notificacionservice } from '../../services/notificacionservice';

@Component({
  selector: 'app-notificacionnoleida',
  imports: [MatIconModule, BaseChartDirective],
  templateUrl: './notificacionnoleida.html',
  styleUrl: './notificacionnoleida.css',
  providers: [provideCharts(withDefaultRegisterables())],
})
export class Notificacionnoleida implements OnInit {
  hasData = false;
  barChartOptions: ChartOptions = {
    responsive: true,
  };
  barChartLegend = true;
  barChartLabels: string[] = [];

  barChartData: ChartDataset[] = [];
  barChartType: ChartType = 'bar';

  constructor(private nS: Notificacionservice) {}

  ngOnInit(): void {
    this.nS.getNotificacionesNoLeidas().subscribe((data) => {
      if (data.length > 0) {
        this.hasData = true;
        this.barChartLabels = data.map((item) => 'Notificaciones No Leidas');
        this.barChartData = [
          {
            data: data.map((item) => item.total),
            label: 'Total Notificaciones No Leidas',
            backgroundColor: [
              '#ec830af5', 
              '#f5862ce0', 

              '#f5863dff', 
              '#f06119ff', 
              '#f7863aff',
            ],
          },
        ];
      } else{
        this.hasData = false;
      }
    });
  }
}
