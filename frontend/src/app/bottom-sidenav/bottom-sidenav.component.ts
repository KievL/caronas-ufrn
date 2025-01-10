import { Component, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatListModule } from '@angular/material/list'
import { MatIconModule } from '@angular/material/icon'
import { RouterModule } from '@angular/router';

export type MenuItem = {
  icon: String
  label: String
  route: String
}

@Component({
  selector: 'app-bottom-sidenav',
  standalone: true,
  imports: [CommonModule, MatListModule, MatIconModule, RouterModule],
  templateUrl: './bottom-sidenav.component.html',
  styleUrl: './bottom-sidenav.component.css'
})
export class BottomSidenavComponent {

  menuItems = signal<MenuItem[]>([
    {
      icon: 'search',
      label: 'Buscar',
      route: 'trip-available'
    },
    {
      icon: 'add_circle',
      label: 'Oferecer',
      route: 'trip-ongoing'
    },
    {
      icon: 'check',
      label: 'Suas Viagens',
      route: 'trip-history'
    },
    {
      icon: 'chat_bubble',
      label: 'Mensagens',
      route: 'chat-messenger'
    },
    {
      icon: 'person',
      label: 'Perfil',
      route: 'profile'
    }

  ]);
}
