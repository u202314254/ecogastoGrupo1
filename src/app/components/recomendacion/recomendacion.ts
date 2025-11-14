import { Component } from '@angular/core';
import { ActivatedRoute, RouterOutlet } from '@angular/router';
import { Recomendacionlistar } from './recomendacionlistar/recomendacionlistar';

@Component({
    selector: 'app-recomendacion',
    imports: [RouterOutlet, Recomendacionlistar],
    templateUrl: './recomendacion.html',
    styleUrl: './recomendacion.css',
})
export class Recomendacion {
    constructor(public route:ActivatedRoute) {}
}