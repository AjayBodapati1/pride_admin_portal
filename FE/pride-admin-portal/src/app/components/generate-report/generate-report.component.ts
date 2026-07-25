import { Component } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { NgIf } from '@angular/common';

@Component({
  selector: 'app-generate-report',
  imports: [ReactiveFormsModule, NgIf],
  templateUrl: './generate-report.component.html',
  styleUrl: './generate-report.component.css',
})
export class GenerateReportComponent {
  reportForm: FormGroup;
  private apiUrl = 'http://localhost:8080/admin/generate';
  isLoading = false;

  constructor(
    private fb: FormBuilder,
    private http: HttpClient,
  ) {
    this.reportForm = this.fb.group({
      name: ['', Validators.required],
      age: [null, [Validators.required, Validators.min(0)]],
      gender: ['', Validators.required],
      diagnosis: ['', Validators.required],
      socialInteraction: ['', Validators.required],
      respectiveLanguage: ['', Validators.required],
      expressiveLanguage: ['', Validators.required],
      summary: ['', Validators.required],
      schoolPerformance: ['', Validators.required],
      recommendations: ['', Validators.required],
    });
  }

  onSubmit(): void {
    if (this.reportForm.valid) {
      this.isLoading = true;
      const formData = this.reportForm.value;

      this.http
        .post(this.apiUrl, formData, { responseType: 'blob' as 'json' })
        .subscribe({
          next: (response: unknown) => {
            const blobResponse = response as Blob;
            this.isLoading = false;

            const fileUrl = URL.createObjectURL(blobResponse);

            const anchor = document.createElement('a');
            anchor.href = fileUrl;
            anchor.download = `${this.reportForm.value.name}_Report.pdf`;
            anchor.click();

            URL.revokeObjectURL(fileUrl);
          },
          error: (error) => {
            console.error('Error downloading report PDF:', error);
            alert('Failed to generate report PDF.');
            this.isLoading = false;
          },
        });
    } else {
      alert('Please fill in all required fields.');
    }
  }
}
