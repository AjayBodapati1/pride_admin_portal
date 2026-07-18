import { HttpErrorResponse, HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { catchError, throwError } from 'rxjs';
import Swal from 'sweetalert2';

// Make sure it says exactly: export const jwtInterceptor
export const jwtInterceptor: HttpInterceptorFn = (req, next) => {
  const authService = inject(AuthService);
  const token = authService.getToken();

  if (token) {
    req = req.clone({
      setHeaders: {
        Authorization: `Bearer ${token}`
      }
    });
  }

  return next(req).pipe(
    catchError((error: HttpErrorResponse) => {
      if (error.status === 403) {

        Swal.fire({
          icon: 'error',
          title: 'Access Denied',
          text: 'Session Timedout / Access Denied',
          showCancelButton: true,
          confirmButtonText: 'Log Out',
          cancelButtonText: 'Close',
          confirmButtonColor: '#d33', 
          cancelButtonColor: '#3085d6',
          allowOutsideClick: false
        }).then((result) => {
          if (result.isConfirmed) {
            authService.logout();
          }
        });
      }
      return throwError(() => error);
    })
  );
};