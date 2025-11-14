import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { RouterLink } from '@angular/router';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';

import { Meta } from '../../../models/Meta';
import { Metaservice } from '../../../services/metaservice';

@Component({
  selector: 'app-metalistar',
  standalone: true,
  templateUrl: './metalistar.html',
  styleUrl: './metalistar.css',
  imports: [
    CommonModule,
    MatTableModule,
    RouterLink,
    MatIconModule,
    MatButtonModule,
  ],
})
export class Metalistar implements OnInit {
  dataSource: MatTableDataSource<Meta> = new MatTableDataSource<Meta>();
  displayedColumns: string[] = [
    'c1',
    'c2',
    'c3',
    'c4',
    'c5',
    'c6',
    'c7',
    'c8',
    'c9',
    'c10',
  ];

  constructor(private mS: Metaservice) {}

  ngOnInit(): void {
    this.mS.list().subscribe((data) => {
      this.dataSource = new MatTableDataSource(data);
    });

    this.mS.getList().subscribe((data) => {
      this.dataSource = new MatTableDataSource(data);
    });
  }

  eliminar(id: number): void {
    this.mS.delete(id).subscribe(() => {
      this.mS.list().subscribe((data) => {
        this.mS.setList(data);
      });
    });
  }
}
