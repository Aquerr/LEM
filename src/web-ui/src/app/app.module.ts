import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { SideMenuComponent } from './side-menu/side-menu.component';
import {ListboxModule} from "primeng/listbox";
import { ProcessesComponent } from './processes/processes.component';
import { SettingsComponent } from './settings/settings.component';
import { LogsComponent } from './logs/logs.component';
import { HeaderComponent } from './header/header.component';
import {HeaderLoginComponent} from "./header/header-login/header-login.component";
import {HeaderTimeComponent} from "./header/header-time/header-time.component";
import {CommonModule, DatePipe} from "@angular/common";
import {LemHttpInterceptor} from "./interceptor/lem-http-interceptor.service";

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomeComponent,
    SideMenuComponent,
    ProcessesComponent,
    SettingsComponent,
    LogsComponent,
    HeaderComponent,
    HeaderLoginComponent,
    HeaderTimeComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ListboxModule,
    CommonModule
  ],
  providers: [DatePipe, LemHttpInterceptor],
  bootstrap: [AppComponent]
})
export class AppModule { }
