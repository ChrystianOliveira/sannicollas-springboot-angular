import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { HeaderComponent } from "../header/header.component";
import { SidebarComponent } from "../sidebar/sidebar.component";

@Component({
  selector: 'app-app-layout',
  standalone: true,
  templateUrl: './app-layout.component.html',
  styleUrl: './app-layout.component.css',
  imports: [HeaderComponent, SidebarComponent, RouterOutlet]
})
export class AppLayoutComponent {

}
