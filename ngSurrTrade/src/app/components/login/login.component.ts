import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css',
})
export class LoginComponent {
  loginData = {
    username: '',
    password: '',
  };
  errorMessage = '';
  constructor(private authService: AuthService, private router: Router) {}

  onLogin() {
    this.authService
      .login(this.loginData.username, this.loginData.password)
      .subscribe({
        next: (res) => {
          console.log('Login successful:', res);
          this.errorMessage = '';
          this.router.navigate(['/profile']);
        },
        error: (err) => {
          console.error('Login failed:', err);
          this.errorMessage = 'Invalid username or password';
        },
      });
  }

  navigateToFillout(): void {
    this.router.navigate(['/login/fillout']);
  }

  navigateToRegister(): void {
    this.router.navigate(['/login/fillout']);
  }

  loggedIn(): boolean {
    return !!localStorage.getItem('token');
  }
}
