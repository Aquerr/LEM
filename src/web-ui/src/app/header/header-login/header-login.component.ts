import { Component } from '@angular/core';

@Component({
  selector: 'lem-header-login',
  templateUrl: './header-login.component.html',
  styleUrls: ['./header-login.component.css']
})
export class HeaderLoginComponent {

  isLoggedIn() {
    return false;
  }

  getCurrentUser() {
    return "Username"
  }
}
