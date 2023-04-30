import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {LoginComponent} from "./tabs/login/login.component";
import {HomeComponent} from "./tabs/home/home.component";
import {ProcessesComponent} from "./tabs/processes/processes.component";
import {SettingsComponent} from "./tabs/settings/settings.component";
import {LogsComponent} from "./tabs/logs/logs.component";

const routes: Routes = [
  {path: '', redirectTo: '/home', pathMatch: 'full'},
  {path: 'home', component: HomeComponent},
  {path: 'processes', component: ProcessesComponent},
  {path: 'settings', component: SettingsComponent},
  {path: 'logs', component: LogsComponent},
  {path: 'login', component: LoginComponent},
  {path: '**', redirectTo: 'home'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
