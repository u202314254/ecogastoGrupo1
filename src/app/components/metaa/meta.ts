import { Component } from '@angular/core';
import { ActivatedRoute, RouterOutlet } from '@angular/router';
import { Metalistar } from './metalistar/metalistar';

@Component({
    selector: 'app-meta',
    standalone: true,
    imports: [RouterOutlet, Metalistar],
    templateUrl: './meta.html',
    styleUrl: './meta.css',
})
export class Meta {
    constructor(public route:ActivatedRoute) {}
}