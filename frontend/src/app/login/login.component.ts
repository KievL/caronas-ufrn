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
import { passwordValidator } from '../validators/password.validator';

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
    private auth: AuthService,
    private notificationService: NotificationService
  ) { }

  ngOnInit(): void {
    this.recoverBtn.next(false);
    this.loginUser = this._formBuilder.group({
      email: new FormControl('', [Validators.required, Validators.maxLength(254), Validators.email]),
      password: new FormControl('', [Validators.required, Validators.minLength(8), Validators.maxLength(128), passwordValidator()]),
      rememberMe: new FormControl(false)
    });

    this.notificationService.notification$.subscribe((notification) => {
      this.notification = notification;
    });
  }

  login(): void {
    if (this.loginUser.valid) {
      const { email, password, rememberMe } = this.loginUser.value;

      localStorage.removeItem('authToken');
      sessionStorage.removeItem('authToken');

      this.loggingIn = true;

      this.auth.loginUser({ email, password }).subscribe({
        next: (response) => {
          console.log('Login bem-sucedido:', response);
          this.auth.setCurrentToken(response.token);

          if (rememberMe) {
            localStorage.setItem('authToken', response.token);
          } else {
            sessionStorage.setItem('authToken', response.token);
          }

          this.notificationService.showNotification('success', 'Login realizado com sucesso!');
          this.router.navigate(['/']);
        },
        error: (err) => {
          console.error('Erro ao fazer login:', err);
          this.notificationService.showNotification('error', 'Erro ao fazer login. Verifique suas credenciais e tente novamente.');
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
