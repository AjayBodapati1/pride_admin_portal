import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { AgGridModule } from 'ag-grid-angular'; 
import { ColDef } from 'ag-grid-community';

export interface User {
  id: number;
  name: string;
  dateOfBirth: string;
  fatherName: string;
  mobileNumber: string;
  email: string;
  dateOfJoining: string;
  role: string;
  active: boolean;
}

@Component({
  selector: 'app-students',
  standalone: true,
  imports: [CommonModule, AgGridModule], 
  templateUrl: './students.component.html',
  styleUrls: ['./students.component.css']
})


export class StudentsComponent implements OnInit {
  students: User[] = [];
  errorMessage: string = '';
  isLoading: boolean = true;

  // Define AG Grid structure
  columnDefs: ColDef[] = [
    { field: 'id', headerName: 'ID', width: 80, sortable: true, filter: true },
    { field: 'name', headerName: 'Name', flex: 1, sortable: true, filter: true },
    { field: 'email', headerName: 'Email', flex: 1.2, filter: true },
    { field: 'mobileNumber', headerName: 'Mobile', width: 140 },
    { field: 'fatherName', headerName: 'Father\'s Name', flex: 1 },
    { 
      field: 'dateOfBirth', 
      headerName: 'DOB', 
      width: 130,
      valueFormatter: params => params.value ? new Date(params.value).toLocaleDateString() : '' 
    },
    { 
      field: 'dateOfJoining', 
      headerName: 'Date of Joining', 
      width: 150,
      valueFormatter: params => params.value ? new Date(params.value).toLocaleDateString() : ''
    },
    { 
      field: 'active', 
      headerName: 'Status', 
      width: 110,
      cellRenderer: (params: any) => {
        const status = params.value ? 'Active' : 'Inactive';
        const cssClass = params.value ? 'status-active' : 'status-inactive';
        return `<span class="status-badge ${cssClass}">${status}</span>`;
      }
    }
  ];

  // Default properties applied to all columns
  defaultColDef: ColDef = {
    resizable: true,
  };

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    this.fetchStudents();
  }

  fetchStudents(): void {
    this.http.get<User[]>('http://localhost:8080/admin/getStudents')
      .subscribe({
        next: (data) => {
          this.students = data;
          this.isLoading = false;
        },
        error: (error) => {
          this.errorMessage = 'Failed to load data from backend server.';
          this.isLoading = false;
        }
      });
  }
}