import { Component } from '@angular/core';
import { ActivatedRoute, RouterOutlet } from '@angular/router';
import { Insignialistar } from './insignialistar/insignialistar';

@Component({
    selector: 'app-insignia',
    imports: [RouterOutlet, Insignialistar],
    templateUrl: './insignia.html',
    styleUrl: './insignia.css',
})
export class Insignia {
    constructor(public route:ActivatedRoute) {}
}