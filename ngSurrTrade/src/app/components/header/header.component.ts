import { Component } from '@angular/core';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css'],
})
export class HeaderComponent {
  constructor(public authService: AuthService) {}
  isLoggedIn(): boolean {
    return this.authService.checkLogin();
  }

  logout() {
    this.authService.logout();
  }
}