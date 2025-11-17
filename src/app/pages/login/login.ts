import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MatMenuItem } from '@angular/material/menu';
import { Router, RouterLink } from '@angular/router';

@Component({
  selector: 'app-login',
  imports: [RouterLink, FormsModule],
  templateUrl: './login.html',
  styleUrl: './login.css',
})
export class Login {

}
