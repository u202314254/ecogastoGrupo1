import { Component, OnInit } from '@angular/core';
import {MatTableDataSource, MatTableModule } from '@angular/material/table';
import { Usuario } from '../../../models/Usuario';
import { Usuarioservice } from '../../../services/usuarioservice';
import { RouterLink } from '@angular/router';
import { MatIconModule } from '@angular/material/icon';

@Component({
    selector: 'app-usuariolistar',
    imports: [MatTableModule,RouterLink, MatIconModule],
    templateUrl: './usuariolistar.html',
    styleUrl: './usuariolistar.css',
})
export class Usuariolistar implements OnInit {
    dataSource:MatTableDataSource<Usuario>=new MatTableDataSource<Usuario>()
    displayedColumns: string[] = ['c1', 'c2', 'c3', 'c4', 'c5', 'c6'];

    constructor(private uS:Usuarioservice) {}

    ngOnInit(): void {
    this.uS.list().subscribe(data=>{
    this.dataSource=new MatTableDataSource(data)
    });
    this.uS.getList().subscribe((data) => {
      this.dataSource = new MatTableDataSource(data);
    });
    }

      eliminar(id: number) {
    this.uS.delete(id).subscribe(data=>{
      this.uS.list().subscribe(data=>{
        this.uS.setList(data)
      })
    })
  }
}