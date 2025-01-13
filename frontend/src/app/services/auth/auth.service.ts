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

  getCurrentToken(): string | null {
    return this.uToken;
  }

  setCurrentToken(tk: string) {
    this.uToken = tk;
  }
}
