import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

export interface Notification {
  type: 'success' | 'error';
  message: string;
}

@Injectable({
  providedIn: 'root',
})
export class NotificationService {
  private notificationSubject = new BehaviorSubject<Notification | null>(null);
  public notification$ = this.notificationSubject.asObservable();

  showNotification(type: 'success' | 'error', message: string) {
    this.notificationSubject.next({ type, message });
    setTimeout(() => this.clearNotification(), 5000); // Remove a notificação após 5 segundos
  }

  clearNotification() {
    this.notificationSubject.next(null);
  }
}

