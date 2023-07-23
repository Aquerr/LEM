import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './tabs/login/login.component';
import { HomeComponent } from './tabs/home/home.component';
import { SideMenuComponent } from './side-menu/side-menu.component';
import {ListboxModule} from "primeng/listbox";
import { ProcessesComponent } from './tabs/processes/processes.component';
import { SettingsComponent } from './tabs/settings/settings.component';
import { LogsComponent } from './tabs/logs/logs.component';
import { HeaderComponent } from './header/header.component';
import {HeaderLoginComponent} from "./header/header-login/header-login.component";
import {HeaderTimeComponent} from "./header/header-time/header-time.component";
import {CommonModule, DatePipe} from "@angular/common";
import {LemHttpInterceptor} from "./interceptor/lem-http-interceptor.service";
import {InputTextModule} from "primeng/inputtext";
import {PasswordModule} from "primeng/password";
import {ReactiveFormsModule} from "@angular/forms";
import {ButtonModule} from "primeng/button";
import {RippleModule} from "primeng/ripple";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {ToastModule} from "primeng/toast";
import {MessageService} from "primeng/api";
import { HeaderLogoutComponent } from './header/header-logout/header-logout.component';
import { LogsViewComponent } from './tabs/logs/logs-view/logs-view.component';

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
    HeaderTimeComponent,
    HeaderLogoutComponent,
    LogsViewComponent
  ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        ListboxModule,
        CommonModule,
        InputTextModule,
        PasswordModule,
        ReactiveFormsModule,
        BrowserAnimationsModule,
        HttpClientModule,
        ButtonModule,
        RippleModule,
        ToastModule
    ],
  providers: [DatePipe,
    {provide: HTTP_INTERCEPTORS, useClass: LemHttpInterceptor, multi: true},
    MessageService],
  bootstrap: [AppComponent]
})
export class AppModule { }
