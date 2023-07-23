import { Component } from '@angular/core';
import {AuthService} from "../service/auth.service";

@Component({
  selector: 'lem-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent {
  constructor(private authService: AuthService) {}

  isAuthenticated(): boolean {
    return this.authService.isAuthenticated();
  }
}
