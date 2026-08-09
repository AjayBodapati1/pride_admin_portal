import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface User {
  userId: number;
  name: string;
  email: string;
}

export interface AttendancePayload {
  userId: number;
  date?: string;
  status: 'PRESENT' | 'ABSENT';
}

@Injectable({
  providedIn: 'root',
})
export class AttendanceService {
  private adminUrl = 'http://localhost:8080/admin';

  constructor(private http: HttpClient) {}

  getUsers(): Observable<User[]> {
    return this.http.get<User[]>(`${this.adminUrl}/getEmployees`);
  }

  markAttendance(payload: AttendancePayload): Observable<any> {
    return this.http.post(`${this.adminUrl}/attendance`, payload, {
      responseType: 'text',
    });
  }
}
