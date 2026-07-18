import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-new-user',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './new-user.component.html',
  styleUrl: './new-user.component.css'
})
export class NewUserComponent {
  userForm: FormGroup;
  private apiUrl = 'http://localhost:8080/admin/addUser';

  constructor(private fb: FormBuilder, private http: HttpClient) {
    this.userForm = this.fb.group({
      name: ['', Validators.required],
      dateOfBirth: ['', Validators.required],
      fatherName: ['', Validators.required],
      mobileNumber: ['', [Validators.required, Validators.pattern('^[0-9]{10}$')]],
      email: ['', [Validators.required, Validators.email]],
      dateOfJoining: ['', Validators.required],
      role: ['', Validators.required],
      active: [true]
    });
  }

  onSubmit(): void {
    if (this.userForm.valid) {
      const formData = this.userForm.value;

      this.http.post(this.apiUrl, formData).subscribe({
        next: (response) => {
          console.log('User added successfully!', response);
          alert('User created successfully!');
          this.userForm.reset({ role: '', active: true });
        },
        error: (error) => {
          console.error('Error adding user:', error);
          alert('Failed to add user. Check console for details.');
        }
      });
    } else {
      alert('Please fill out all required fields correctly.');
    }
  }
}
