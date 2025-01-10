import { Routes } from '@angular/router';
import { TripAvailableComponent } from './trip-available/trip-available.component';
import { TripOngoingComponent } from './trip-ongoing/trip-ongoing.component';
import { ProfileComponent } from './profile/profile.component';
import { ChatMessengerComponent } from './chat-messenger/chat-messenger.component';
import { TripHistoryComponent } from './trip-history/trip-history.component';

export const routes: Routes = [
    {
        path: '',
        pathMatch: 'full',
        redirectTo: 'trip-available'
    },
    {
        path:'trip-available',
        component: TripAvailableComponent
    },
    {
        path:'trip-ongoing',
        component: TripOngoingComponent
    },
    {
        path:'trip-history',
        component: TripHistoryComponent
    },
    {
        path:'chat-messenger',
        component: ChatMessengerComponent
    },
    {
        path:'profile',
        component: ProfileComponent
    }

];
