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
  usernameExists: boolean = false;
  isPasswordValid: boolean = true;
  private usernameCheckTimeout: any;

  constructor(private authService: AuthService, private router: Router) {}

  onUsernameInput(username: string) {
    clearTimeout(this.usernameCheckTimeout);
    if (username.trim()) {
      this.usernameCheckTimeout = setTimeout(() => {
        this.authService.checkRegister(username).subscribe(
          (exists) => {
            this.usernameExists = exists;
          },
          (err) => {
            console.error('Error checking Username: ', err);
            this.usernameExists = false;
          }
        );
      }, 200);
    } else {
      this.usernameExists = false;
    }
  }

  onPasswordInput(password: string) {
    this.isPasswordValid = this.isValidPassword(password);
  }

  isValidPassword(password: string): boolean {
    const passwordRegex = /^[^\s]{8,}$/;
    return passwordRegex.test(password);
  }

  isValidEmail(email: string): boolean {
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]{2,}$/;
    return emailRegex.test(email);
  }

  onRegister() {
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
}
