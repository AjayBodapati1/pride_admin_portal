import { Routes } from '@angular/router';

import { LoginComponent } from './components/login/login.component';
import { HomeComponent } from './components/home/home.component';
import { authGuard } from './guards/auth.guard';
import { StudentsComponent } from './components/students/students.component';
import { redirectGuard } from './guards/redirect.guard';
import { AttendanceComponent } from './components/attendance/attendance.component';
import { MessageComponent } from './components/message/message.component';
import { NewUserComponent } from './components/new-user/new-user.component';
import { EditUserComponent } from './components/edit-user/edit-user.component';

export const routes: Routes = [
  { path: '', pathMatch: 'full', canActivate: [redirectGuard], component: LoginComponent },
  { path: 'login', component: LoginComponent },
  { path: 'home', component: HomeComponent, canActivate: [authGuard] },  
  { path: 'students', component: StudentsComponent, canActivate: [authGuard] },
  { path: 'attendance', component: AttendanceComponent, canActivate: [authGuard] },
  { path: 'message', component: MessageComponent, canActivate: [authGuard] },
  { path: 'newUser', component: NewUserComponent, canActivate: [authGuard] },
  { path: 'editUser', component: EditUserComponent, canActivate: [authGuard] },
  { path: '**', redirectTo: '' }
];