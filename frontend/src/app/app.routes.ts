import { Routes } from '@angular/router';
import { TripAvailableComponent } from './trip-available/trip-available.component';
import { TripOngoingComponent } from './trip-ongoing/trip-ongoing.component';
import { ProfileComponent } from './profile/profile.component';

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
        path:'profile',
        component: ProfileComponent
    }

];
