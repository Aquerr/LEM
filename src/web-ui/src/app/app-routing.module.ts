import {inject, NgModule} from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {LoginComponent} from "./tabs/login/login.component";
import {HomeComponent} from "./tabs/home/home.component";
import {ProcessesComponent} from "./tabs/processes/processes.component";
import {SettingsComponent} from "./tabs/settings/settings.component";
import {LogsComponent} from "./tabs/logs/logs.component";
import {AuthService} from "./service/auth.service";
import {Authority} from "./model/authority.model";
import {LogsViewComponent} from "./tabs/logs/logs-view/logs-view.component";

const routes: Routes = [
  {path: '', redirectTo: '/home', pathMatch: 'full'},
  {path: 'home', component: HomeComponent},
  {path: 'processes', component: ProcessesComponent, canActivate: [() => inject(AuthService).canActivate() && inject(AuthService).checkAuthorityAndDisplayToast(Authority.VIEW_PROCESSES)]},
  {path: 'settings', component: SettingsComponent, canActivate: [() => inject(AuthService).canActivate() && inject(AuthService).checkAuthorityAndDisplayToast(Authority.VIEW_SETTINGS)]},
  {path: 'logs', component: LogsComponent, canActivate: [() => inject(AuthService).canActivate() && inject(AuthService).checkAuthorityAndDisplayToast(Authority.VIEW_LOGS)]},
  {path: 'logs/:logType', component: LogsViewComponent, canActivate: [() => inject(AuthService).canActivate() && inject(AuthService).checkAuthorityAndDisplayToast(Authority.VIEW_LOGS)]},
  {path: 'login', component: LoginComponent, canActivate: [() => !inject(AuthService).isAuthenticated()]},
  {path: '**', redirectTo: 'home'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
