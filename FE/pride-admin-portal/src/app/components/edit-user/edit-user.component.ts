import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { AgGridModule } from 'ag-grid-angular';
import { ColDef } from 'ag-grid-community';
import { EditButtonRendererComponent } from '../edit-button-renderer/edit-button-renderer.component';

@Component({
  selector: 'app-edit-user',
  imports: [CommonModule, AgGridModule, ReactiveFormsModule, EditButtonRendererComponent],
  templateUrl: './edit-user.component.html',
  styleUrl: './edit-user.component.css'
})
export class EditUserComponent {
  public rowData: any[] = [];
  public columnDefs: ColDef[] = [];

  public isModalOpen = false;
  public editForm!: FormGroup;
  private selectedUserEmail: string = '';

  constructor(private http: HttpClient, private fb: FormBuilder) {
    this.initForm();
    this.initGridColumns();
  }

  ngOnInit(): void {
    this.loadUsers();
  }

  private initForm() {
    this.editForm = this.fb.group({
      name: ['', Validators.required],
      dateOfBirth: ['', Validators.required],
      fatherName: ['', Validators.required],
      mobileNumber: ['', [Validators.required, Validators.pattern('^[0-9]{10}$')]],
      email: [{ value: '', disabled: true }, [Validators.required, Validators.email]], // Email usually acts as an immutable identifier
      dateOfJoining: ['', Validators.required],
      password: ['', Validators.required],
      role: ['', Validators.required],
      active: [true]
    });
  }

  private initGridColumns() {
    this.columnDefs = [
      { field: 'name', headerName: 'Name', sortable: true, filter: true },
      { field: 'email', headerName: 'Email' },
      { field: 'mobileNumber', headerName: 'Mobile' },
      { field: 'role', headerName: 'Role' },
      { field: 'active', headerName: 'Status', valueFormatter: p => p.value ? 'Active' : 'Inactive' },
      {
        headerName: 'Actions',
        cellRenderer: EditButtonRendererComponent,
        cellRendererParams: {
          onClick: (userData: any) => this.openEditModal(userData)
        },
        pinned: 'right',
        width: 100
      }
    ];
  }

  loadUsers() {
    this.http.get<any[]>('http://localhost:8080/admin/getAll')
      .subscribe({
        next: (data) => this.rowData = data,
        error: (err) => console.error('Failed to fetch users', err)
      });
  }

  openEditModal(user: any) {
    this.selectedUserEmail = user.email;
    this.isModalOpen = true;
    this.editForm.patchValue(user);
  }

  closeModal() {
    this.isModalOpen = false;
    this.editForm.reset();
  }

  onSubmitUpdate() {
    if (this.editForm.invalid) return;
    const updatedUserData = this.editForm.getRawValue();

    this.http.put(`http://localhost:8080/admin/editUser/${this.selectedUserEmail}`, updatedUserData)
      .subscribe({
        next: () => {
          alert('User updated successfully!');
          this.closeModal();
          this.loadUsers();
        },
        error: (err) => console.error('Error updating user', err)
      });
  }
}
