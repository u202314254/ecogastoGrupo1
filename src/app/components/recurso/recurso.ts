import { Component } from '@angular/core';
import { ActivatedRoute, RouterOutlet } from '@angular/router';
import { Recursolistar } from './recursolistar/recursolistar';

@Component({
    selector: 'app-recurso',
    imports: [RouterOutlet, Recursolistar],
    templateUrl: './recurso.html',
    styleUrl: './recurso.css',
})
export class Recurso {
    constructor(public route:ActivatedRoute) {}
}