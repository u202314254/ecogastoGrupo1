import { Component, OnInit } from '@angular/core';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { Consumo } from '../../../models/Consumo';
import { Consumoservice } from '../../../services/consumoservice';
import { RouterLink } from '@angular/router';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
 
@Component({
  selector: 'app-consumolistar',
  imports: [MatTableModule, RouterLink, MatIconModule, MatButtonModule],
  templateUrl: './consumolistar.html',
  styleUrl: './consumolistar.css',
})
export class Consumolistar implements OnInit {
  dataSource: MatTableDataSource<Consumo> = new MatTableDataSource<Consumo>();
  displayedColumns: string[] = ['c1', 'c2', 'c3', 'c4', 'c5', 'c6', 'c7', 'c8', 'c9'];

  constructor(private cS: Consumoservice) {}

  ngOnInit(): void {
    this.cS.list().subscribe((data) => {
      this.dataSource = new MatTableDataSource(data);
    });
    this.cS.getList().subscribe((data) => {
      this.dataSource = new MatTableDataSource(data);
    });
  }

  Celiminar(id:number){

    this.cS.delete(id).subscribe(data=>{this.cS.list().subscribe(data=>{this.cS.setList(data)})})
  }
}
