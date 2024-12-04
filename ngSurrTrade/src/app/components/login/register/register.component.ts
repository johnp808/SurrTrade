import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { userDTO } from 'src/app/models/userDTO';
import { RegisterData } from 'src/app/models/register-data';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrl: './register.component.css',
})
export class RegisterComponent {
  registerData: RegisterData = {
    username: '',
    email: '',
    password: '',
  };
  confirmPassword: string = '';
  errorMessage: string = '';

  constructor(private authService: AuthService, private router: Router) {}

  onUsernameBlur(username: string) {
    if (username.trim()) {
      this.authService.checkRegister(username).subscribe(
        (exists) => {
          this.errorMessage = exists ? 'This Username Is Already In Use' : '';
        },
        (err) => {
          console.error('Error checking Username: ', err);
          this.errorMessage = 'Error Validating Username. Please Try Again';
        }
      );
    }
  }

  onRegister() {
    this.errorMessage = '';

    if (!this.isValidEmail(this.registerData.email)) {
      this.errorMessage = 'Please Enter A Valid Email Address';
    }

    if (this.registerData.password !== this.confirmPassword) {
      this.errorMessage = 'Passwords Do Not Match';
      return;
    }

    this.authService.register(this.registerData).subscribe(
      (res) => {
        console.log('User Registered Successfully:', res);
        this.router.navigate(['/profile']);
      },
      (err) => {
        console.log(err);
        this.errorMessage = 'Registration Failed. Please Try Again.';
      }
    );
  }

  isValidEmail(email: string): boolean {
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]{2,}$/;
    return emailRegex.test(email);
  }
}
