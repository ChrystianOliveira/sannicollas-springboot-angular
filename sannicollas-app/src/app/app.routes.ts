import { Routes } from '@angular/router';

export const routes: Routes = [
  {
    path: '',
    loadComponent: () => import('./layout/app-layout/app-layout.component').then(m => m.AppLayoutComponent)
  }
];
