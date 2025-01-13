import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatCheckbox } from '@angular/material/checkbox';
import { FormBuilder, FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { BehaviorSubject } from 'rxjs';
import { AuthService } from '../services/auth/auth.service';
import { Notification, NotificationService } from '../services/notifications/notification.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatIconModule,
    MatCheckbox,
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  notification: Notification | null = null;

  loggingIn: boolean = false;
  loginUser!: FormGroup;
  recoverBtnHtml: boolean = false;
  recoverBtn = new BehaviorSubject<boolean>(false);
  showPassword: boolean = false;

  constructor(
    private router: Router,
    private _formBuilder: FormBuilder,
    private notificationService: NotificationService
  ) { }

  ngOnInit(): void {
    this.recoverBtn.next(false);
    this.loginUser = this._formBuilder.group({
      email: new FormControl('', Validators.required),
      password: new FormControl('', Validators.required),
      rememberMe: new FormControl(false)
    });

    this.notificationService.notification$.subscribe((notification) => {
      this.notification = notification;
    });
  }

  goToMain(): void {
    this.router.navigate(['/']);
  }

  goToRegiser(): void {
    this.router.navigate(['register']);
  }

  togglePasswordVisibility() {
    this.showPassword = !this.showPassword; // Alterna entre true e false
  }
}
