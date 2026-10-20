import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-employee-attendance',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './employee-attendance.component.html',
  styleUrl: './employee-attendance.component.css',
})
export class EmployeeAttendanceComponent {
  private http = inject(HttpClient);

  id: number | null = null;
  fromDate: string = '';
  toDate: string = '';

  attendanceList: any[] = [];
  errorMessage = '';

  onSubmit(): void {
    if (!this.id) {
      this.errorMessage = 'Please enter Employee ID';
      return;
    }

    const currentYear = new Date().getFullYear();

    if (this.fromDate == '') {
      this.fromDate = `${currentYear}-01-01`;
    }

    if (this.toDate == '') {
      this.toDate = `${currentYear}-12-31`;
    }

    const requestBody = {
      id: this.id,
      fromDate: this.fromDate,
      toDate: this.toDate,
    };

    console.log(requestBody);

    this.http
      .request<any[]>('POST', 'http://localhost:8080/admin/getAttendance', {
        body: requestBody,
      })
      .subscribe({
        next: (response) => {
          this.attendanceList = response;
          this.errorMessage = '';
        },
        error: (error) => {
          console.error('API Error:', error);
          this.errorMessage =
            'Failed to fetch attendance data. Please check your connection or inputs.';
          this.attendanceList = [];
        },
      });
  }
}
