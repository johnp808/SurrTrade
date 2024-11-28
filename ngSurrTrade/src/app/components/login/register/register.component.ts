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

  onRegister() {
    if (this.registerData.password !== this.confirmPassword) {
      this.errorMessage = 'Passwords Do Not Match.';
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
}
