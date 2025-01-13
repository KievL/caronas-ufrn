import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { FormBuilder, FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { BehaviorSubject } from 'rxjs';
import { AuthService } from '../services/auth/auth.service';
import { Notification, NotificationService } from '../services/notifications/notification.service';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatIconModule,
  ],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {
  notification: Notification | null = null;

  loggingIn: boolean = false;
  registerUser!: FormGroup;
  recoverBtnHtml: boolean = false;
  recoverBtn = new BehaviorSubject<boolean>(false);
  showPassword: boolean = false;

  constructor(
    private router: Router,
    private _formBuilder: FormBuilder,
    private auth: AuthService,
    private notificationService: NotificationService
  ) { }

  ngOnInit(): void {
    this.recoverBtn.next(false);
    this.registerUser = this._formBuilder.group({
      name: new FormControl('', Validators.required),
      email: new FormControl('', Validators.required),
      password: new FormControl('', Validators.required),
      course: new FormControl('', Validators.required),
      enrollmentNumber: new FormControl('', Validators.required),
    });

    this.notificationService.notification$.subscribe((notification) => {
      this.notification = notification;
    });
  }

  register() {
    if (this.registerUser.valid) {
      const { name, email, password, course, enrollmentNumber } = this.registerUser.value;

      this.loggingIn = true;

      const userData = {
        name,
        email,
        passwordHash: password,
        course,
        enrollmentNumber
      };

      this.auth.createUser(userData).subscribe({
        next: (response) => {
          console.log('Usuário registrado com sucesso:', response);
          this.notificationService.showNotification('success', 'Usuário registrado com sucesso! Faça login para continuar.');
          this.router.navigate(['/login']);
        },
        error: (err) => {
          console.error('Erro ao registrar usuário:', err);
          this.notificationService.showNotification('error', 'Erro ao registrar usuário. Tente novamente.');
        },
        complete: () => {
          this.loggingIn = false;
          console.log("fim");
        }
      });
    } else {
      console.error('Formulário inválido!');
      this.notificationService.showNotification('error', 'Por favor, preencha todos os campos corretamente.');
    }
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
