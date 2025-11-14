import { Component, OnInit } from '@angular/core';
import {MatTableDataSource, MatTableModule } from '@angular/material/table';
import { Recomendacion } from '../../../models/Recomendacion';
import { Recomendacionservice } from '../../../services/recomendacionservice';
import { RouterLink } from '@angular/router';
import { MatIconModule } from '@angular/material/icon';


@Component({ 
    selector: 'app-recomendacionlistar',
    imports: [MatTableModule, RouterLink, MatIconModule],
    templateUrl: './recomendacionlistar.html',
    styleUrl: './recomendacionlistar.css',
})
export class Recomendacionlistar implements OnInit {
    dataSource:MatTableDataSource<Recomendacion>=new MatTableDataSource<Recomendacion>()
    displayedColumns: string[] = ['c1', 'c3', 'c4', 'c5', 'c6', 'c7', 'c8'];

    constructor(private reS:Recomendacionservice) {}

    ngOnInit(): void {
    this.reS.list().subscribe(data=>{
    this.dataSource=new MatTableDataSource(data)
    });
    this.reS.getList().subscribe((data) => {
      this.dataSource = new MatTableDataSource(data);
    }); 
    }

    eliminar(id: number) {
    this.reS.delete(id).subscribe(data=>{
      this.reS.list().subscribe(data=>{
        this.reS.setList(data)
      })
    })
  }
} 