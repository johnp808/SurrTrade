import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { userDTO } from '../models/userDTO';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  private baseUrl = environment.baseUrl;

  constructor(private http: HttpClient) {}

  private getHttpOptions() {
    const credentials = localStorage.getItem('credentials');
    if (!credentials) {
      throw new Error('User Is Not Logged In');
    }
    return {
      headers: new HttpHeaders({
        Authorization: `Basic ${credentials}`,
      }),
    };
  }

  getUserProfile(): Observable<userDTO> {
    return this.http.get<userDTO>(
      `${this.baseUrl}users/profile`,
      this.getHttpOptions()
    );
  }

  updatePassword(
    userId: number,
    passInfo: { oldPass: string; newPass: string }
  ): Observable<boolean> {
    return this.http.put<boolean>(
      `${this.baseUrl}users/profile/update/password/${userId}`,
      passInfo,
      this.getHttpOptions()
    );
  }
}
