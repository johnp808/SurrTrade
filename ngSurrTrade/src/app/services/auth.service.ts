import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError, tap } from 'rxjs/operators';
import { Observable, throwError } from 'rxjs';
import { userDTO } from '../models/userDTO';
import { RegisterData } from '../models/register-data';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private baseUrl = environment.baseUrl;
  public currentUser: userDTO = new userDTO();

  constructor(private http: HttpClient, private router: Router) {}

  login(username: string, password: string): Observable<userDTO> {
    const credentials = this.generateBasicAuthCredentials(username, password);

    const httpOptions = {
      headers: new HttpHeaders({
        Authorization: `Basic ${credentials}`,
        'X-Requested-With': 'XMLHttpRequest',
      }),
    };
    return this.http
      .get<userDTO>(this.baseUrl + 'authenticate', httpOptions)
      .pipe(
        tap((res) => {
          localStorage.setItem('credentials', credentials);
          this.currentUser = res;
          return res;
        }),
        catchError((err: any) => {
          console.log(err);
          return throwError('AuthService.login(): Error logging in.');
        })
      );
  }

  register(registerData: RegisterData): Observable<userDTO> {
    return this.http
      .post<userDTO>(this.baseUrl + 'register', registerData)
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError(
            'AuthService.register(): error registering userDTO.'
          );
        })
      );
  }

  logout() {
    localStorage.removeItem('credentials');
    this.currentUser = new userDTO();
    this.router.navigate(['/']);
  }

  checkLogin() {
    if (localStorage.getItem('credentials')) {
      return true;
    }
    return false;
  }

  generateBasicAuthCredentials(username: string, password: string) {
    return btoa(`${username}:${password}`);
  }

  getCredentials() {
    return localStorage.getItem('credentials');
  }

  checkRegister(username: string): Observable<boolean> {
    return this.http
      .get<boolean>(this.baseUrl + 'api/users/checkusername/' + username)
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError(
            'Error checking username: AuthService.getUserByUsername()'
          );
        })
      );
  }
}
