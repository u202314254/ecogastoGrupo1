import { Component, OnInit } from '@angular/core';
import {MatTableDataSource, MatTableModule } from '@angular/material/table';
import { Rol } from '../../../models/Rol';
import { Rolservice } from '../../../services/rolservice';
import { RouterLink } from '@angular/router';
import { MatIconModule } from '@angular/material/icon';

@Component({
    selector: 'app-rollistar',
    imports: [MatTableModule,RouterLink, MatIconModule],
    templateUrl: './rollistar.html',
    styleUrl: './rollistar.css',
})
export class Rollistar implements OnInit {
    dataSource:MatTableDataSource<Rol>=new MatTableDataSource<Rol>()
    displayedColumns: string[] = ['c1', 'c2', 'c3', 'c4', 'c5'];

    constructor(private roS:Rolservice) {}

    ngOnInit(): void {
    this.roS.list().subscribe(data=>{
    this.dataSource=new MatTableDataSource(data)
    });
    this.roS.getList().subscribe((data) => {
      this.dataSource = new MatTableDataSource(data);
    });
    }

    eliminar(id: number) {
    this.roS.delete(id).subscribe(data=>{
      this.roS.list().subscribe(data=>{
        this.roS.setList(data)
      })
    })
  }
}