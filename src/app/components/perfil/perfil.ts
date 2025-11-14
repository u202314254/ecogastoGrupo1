import { Component } from '@angular/core';
import { ActivatedRoute, RouterOutlet } from '@angular/router';
import { Perfillistar } from './perfillistar/perfillistar';

@Component({
    selector: 'app-perfil',
    imports: [RouterOutlet, Perfillistar],
    templateUrl: './perfil.html',
    styleUrl: './perfil.css',
})
export class Perfil {
    constructor(public route:ActivatedRoute) {}
}