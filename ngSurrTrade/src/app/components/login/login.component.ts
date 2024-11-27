import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  @ViewChild('usernameInput', { static: true }) usernameInput!: ElementRef;
  loginData = {
    username: '',
    loginPassword: '',
  };

  errorMessage = '';

  constructor(private authService: AuthService, private router: Router) {}

  ngOnInit() {
    this.usernameInput.nativeElement.focus();
  }

  onLogin() {
    this.authService
      .login(this.loginData.username, this.loginData.loginPassword)
      .subscribe({
        next: (res) => {
          console.log('Login successful:', res);
          this.errorMessage = '';
          this.router.navigate(['/profile']);
        },
        error: (err) => {
          console.error('Login failed:', err);
          this.errorMessage = 'Invalid Username or Password <br> Try Again';
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
