import { Component } from '@angular/core';
import { FormBuilder, FormGroup, MaxLengthValidator, ReactiveFormsModule, Validators } from '@angular/forms';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-message',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './message.component.html',
  styleUrl: './message.component.css'
    
})
export class MessageComponent {
messageForm: FormGroup;
  private apiUrl = 'http://localhost:8080/admin/sendEmail';

  constructor(private fb: FormBuilder, private http: HttpClient) {
    this.messageForm = this.fb.group({
      sub: ['', Validators.required],
      msg: ['', Validators.required],
      role: ['', Validators.required]
    });
  }

  onSubmit(): void {
    if (this.messageForm.valid) {
      const formData = this.messageForm.value;
      this.http.post(this.apiUrl , formData, { responseType: 'text' }).subscribe({
        next: (response) => {
          console.log('Mail sent successfully!', response);
          alert('Mail sent successfully!');
          this.messageForm.reset();
        },
        error: (error) => {
          console.error('Error sending mail:', error);
          alert('Failed to send mail. Check console for details.');
        }
      });
    } else {
      alert('Please fill out all required fields correctly.');
    }
  }
}
