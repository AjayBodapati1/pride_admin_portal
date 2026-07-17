import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { BehaviorSubject, Observable, tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private http = inject(HttpClient);
  private router = inject(Router);
  
  private apiUrl = 'http://localhost:8080/public/login';
  private isAuthenticatedSubject = new BehaviorSubject<boolean>(this.isLoggedIn());

  isAuthenticated$ = this.isAuthenticatedSubject.asObservable();

  isLoggedIn(): boolean {
    return !!localStorage.getItem('jwt_token'); 
  }

  login(credentials: { email: string; password: string }): Observable<string> {
    return this.http.post<string>(this.apiUrl, credentials, { responseType: 'text' as 'json' }).pipe(
      tap(token => {
        if (token) {
          localStorage.setItem('jwt_token', token);
          this.isAuthenticatedSubject.next(true);
        }
      })
    );
  }

  getToken(): string | null {
    return localStorage.getItem('jwt_token');
  }

  logout(): void {
    localStorage.removeItem('jwt_token');
    this.isAuthenticatedSubject.next(false);
    this.router.navigate(['/login']);
  }
}