import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AuthService } from '../../services/auth.service';
import { Router, RouterLink, RouterLinkActive } from '@angular/router';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [CommonModule, RouterLink, RouterLinkActive],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent {
  isLoggedIn$: Observable<boolean>;
  showDropdown = false;

  constructor(private authService: AuthService, public router: Router) {
    this.isLoggedIn$ = this.authService.isAuthenticated$;
  }

  onLogout() {
    this.authService.logout();
    this.router.navigate(['/login']);
  }
}
