import { Component, OnInit } from '@angular/core';
import {MatTableDataSource, MatTableModule } from '@angular/material/table';
import { Recurso } from '../../../models/Recurso';
import { Recursoservice } from '../../../services/recursoservice';
import { RouterLink } from '@angular/router';
import { MatIconModule } from '@angular/material/icon';

@Component({
    selector: 'app-recursolistar',
    imports: [MatTableModule, RouterLink, MatIconModule],
    templateUrl: './recursolistar.html',
    styleUrl: './recursolistar.css',
})
export class Recursolistar implements OnInit {
    dataSource:MatTableDataSource<Recurso>=new MatTableDataSource<Recurso>()
    displayedColumns: string[] = ['c1', 'c2', 'c3', 'c4'];

    constructor(private rS:Recursoservice) {}

    ngOnInit(): void {
    this.rS.list().subscribe(data=>{
    this.dataSource=new MatTableDataSource(data)
    });
    this.rS.getList().subscribe((data) => {
      this.dataSource = new MatTableDataSource(data);
    });
    }

    eliminar(id: number) {
    this.rS.delete(id).subscribe(data=>{
      this.rS.list().subscribe(data=>{
        this.rS.setList(data)
      })
    })
  }
}