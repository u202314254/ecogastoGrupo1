import { Component, OnInit } from '@angular/core';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { Perfil } from '../../../models/Perfil';
import { Perfilservice } from '../../../services/perfilservice';
import { RouterLink } from '@angular/router';
import { MatIconModule } from '@angular/material/icon';

@Component({
  selector: 'app-perfillistar',
  imports: [MatTableModule,RouterLink, MatIconModule],
  templateUrl: './perfillistar.html',
  styleUrl: './perfillistar.css',
})
export class Perfillistar implements OnInit {
  dataSource: MatTableDataSource<Perfil> = new MatTableDataSource<Perfil>();
  displayedColumns: string[] = [
    'c1',
    'c2',
    'c3',
    'c4',
    'c5',
    'c6',
    'c7',
    'c8'
  ];

  constructor(private pS: Perfilservice) {}

  ngOnInit(): void {
    this.pS.list().subscribe((data) => {
      this.dataSource = new MatTableDataSource(data);
    });

    this.pS.getList().subscribe((data) => {
      this.dataSource = new MatTableDataSource(data);
    });
  }

  eliminar(id: number) {
    this.pS.delete(id).subscribe(data => {
      this.pS.list().subscribe((data) => {
        this.pS.setList(data);
      });
    });
  }
}
