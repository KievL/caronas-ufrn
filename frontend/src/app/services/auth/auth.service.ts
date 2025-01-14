import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { BehaviorSubject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private apiUrl = 'http://0.0.0.0:8080/api';

  private uToken: string | null = null;

  constructor(private http: HttpClient) { }

  createUser(userData: { name: string, email: string, passwordHash: string, course: string, enrollmentNumber: string }): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/user`, userData);
  }

  loginUser(credentials: { email: string, password: string }): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/login`, credentials);
  }

  logout(): void {
    this.uToken = null;
    localStorage.removeItem('authToken');
    sessionStorage.removeItem('authToken');
  }

  getCurrentToken(): string | null {
    return this.uToken;
  }

  setCurrentToken(tk: string) {
    this.uToken = tk;
  }
}
