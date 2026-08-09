import { Component, inject, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AgGridModule } from 'ag-grid-angular';
import { ColDef } from 'ag-grid-community';
import Swal from 'sweetalert2';
import { AttendanceService, User } from '../../services/attendance.service';

@Component({
  selector: 'app-attendance',
  standalone: true,
  imports: [CommonModule, AgGridModule],
  templateUrl: './attendance.component.html',
  styleUrls: ['./attendance.component.css'],
})
export class AttendanceComponent implements OnInit {
  private attendanceService = inject(AttendanceService);

  users: User[] = [];
  errorMessage: string = '';

  defaultColDef: ColDef = {
    flex: 1,
    minWidth: 100,
    filter: true,
    sortable: true,
    resizable: true,
  };

  columnDefs: ColDef[] = [
    { field: 'id', headerName: 'ID', maxWidth: 100 },
    { field: 'name', headerName: 'Employee Name' },
    { field: 'email', headerName: 'Email' },
    {
      headerName: 'Action',
      field: 'action',
      sortable: false,
      filter: false,
      cellRenderer: (params: any) => {
        const button = document.createElement('button');
        button.className = 'btn-action';
        button.innerText = 'Mark Attendance';
        button.addEventListener('click', () =>
          this.openAttendanceDialog(params.data),
        );
        return button;
      },
    },
  ];

  constructor() {}

  ngOnInit(): void {
    this.loadUsers();
  }

  loadUsers(): void {
    this.attendanceService.getUsers().subscribe({
      next: (data) => (this.users = data),
      error: (err) => (this.errorMessage = 'Failed to load employee records.'),
    });
  }

  openAttendanceDialog(user: User): void {
    Swal.fire({
      title: `Mark Attendance for ${user.name}`,
      text: 'Select status for today:',
      icon: 'question',
      showCancelButton: true,
      showDenyButton: true,
      confirmButtonText: 'Present',
      denyButtonText: 'Absent',
      cancelButtonText: 'Cancel',
      confirmButtonColor: '#198754',
      denyButtonColor: '#dc3545',
    }).then((result) => {
      if (result.isConfirmed) {
        this.submitAttendance(user.userId, 'PRESENT');
      } else if (result.isDenied) {
        this.submitAttendance(user.userId, 'ABSENT');
      }
    });
  }

  private submitAttendance(userId: number, status: 'PRESENT' | 'ABSENT'): void {
    const payload = {
      userId,
      date: new Date().toISOString().split('T')[0],
      status,
    };

    this.attendanceService.markAttendance(payload).subscribe({
      next: (response) => {
        Swal.fire('Success', response, 'success');
      },
      error: (err) => {
        this.errorMessage = err.error || 'Could not record attendance.';
        Swal.fire('Error', this.errorMessage, 'error');
      },
    });
  }
}
