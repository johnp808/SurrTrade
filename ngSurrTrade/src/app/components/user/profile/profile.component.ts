import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';
import { userDTO } from 'src/app/models/userDTO';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.css',
})
export class ProfileComponent implements OnInit {
  user: userDTO | null = null;
  passwordChangeData = {
    currentPassword: '',
    newPassword: '',
    confirmPassword: '',
  };
  isLoading: boolean = true;
  successMessage: string = '';
  errorMessage: string = '';

  constructor(
    private userService: UserService,
    private authService: AuthService
  ) {}

  ngOnInit(): void {
    this.loadUserProfile();
  }

  loadUserProfile() {
    this.isLoading = true;
    this.userService.getUserProfile().subscribe(
      (user) => {
        this.user = user;
        this.isLoading = false;
      },
      (err) => {
        console.error('Unable To Load User Profile');
        this.errorMessage = 'Unable To Load Profile. Please Try Again';
        this.isLoading = false;
      }
    );
  }

  onChangePassword(): void {
    if (
      this.passwordChangeData.newPassword !==
      this.passwordChangeData.confirmPassword
    ) {
      this.errorMessage = 'New Passwords Do Not Match';
      return;
    }

    if (!this.user) {
      this.errorMessage = 'User Not Found';
      return;
    }

    const passInfo = {
      oldPass: this.passwordChangeData.currentPassword,
      newPass: this.passwordChangeData.newPassword,
    };

    this.userService.updatePassword(this.user.id, passInfo).subscribe(
      (passChanged) => {
        this.successMessage = 'Password Was Successfully Changed';
      },
      (err) => {
        console.error('Error Changing Password', err);
        this.errorMessage = 'Failed To Change Password. Please Try Again';
        this.successMessage = '';
      }
    );
  }
}
