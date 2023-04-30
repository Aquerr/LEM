import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {LoginComponent} from "./login/login.component";
import {HomeComponent} from "./home/home.component";
import {ProcessesComponent} from "./processes/processes.component";
import {SettingsComponent} from "./settings/settings.component";
import {LogsComponent} from "./logs/logs.component";

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
