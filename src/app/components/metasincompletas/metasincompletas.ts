import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { MatIconModule } from '@angular/material/icon';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { RouterLink } from '@angular/router';
import { CantidadMetaActivaDTO } from '../../models/CantidadMetaActivaDTO';
import { Metaservice } from '../../services/metaservice';

@Component({
  selector: 'app-metasincompletas',
  imports: [MatTableModule,CommonModule,MatIconModule,RouterLink],
  templateUrl: './metasincompletas.html',
  styleUrl: './metasincompletas.css',
})
export class Metasincompletas implements OnInit{
  dataSource: MatTableDataSource<CantidadMetaActivaDTO> = new MatTableDataSource();
  displayedColumns: string[] = ['c1', 'c2', 'c3', 'c4','c5'];

  constructor(private mS: Metaservice){}
  ngOnInit(): void {
    this.mS.getCantidadMetasInactivas().subscribe(data => {
      this.dataSource = new MatTableDataSource(data);
    })
  }
}
