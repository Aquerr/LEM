import {Injectable} from '@angular/core';
import {map, Observable} from "rxjs";
import {CanActivateFn, Router} from "@angular/router";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {JwtTokenResponse} from "../model/jwt.model";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  // canActivateAuthenticated: CanActivateFn = (route, state) => {
  //   return inject(AuthService).canActivate();
  // }

  constructor(private router: Router,
              private httpClient: HttpClient) { }

  authenticate(signInRequest: { password: string; username: string }): Observable<any> {
    console.log(signInRequest);
    console.log(environment.apiBaseUrl + '/auth');
    return this.httpClient.post<JwtTokenResponse>(environment.apiBaseUrl + '/auth', signInRequest, {
      observe: 'response'
    })
      .pipe(map(userData => {
        console.log(userData);
        sessionStorage.setItem('username', userData.body?.username ? userData.body.username : '');
        sessionStorage.setItem('auth-token', (userData.body?.jwt ? userData.body?.jwt : ''))
        return userData;
      }));
  }

  isAuthenticated(): boolean {
    let user = sessionStorage.getItem('username');
    let token = sessionStorage.getItem('auth-token');
    return !(user === null) && !(token === null);
  }

  logout(): void {
    //TODO: Contact the backend to invalidate old auth-token.
    sessionStorage.removeItem('username');
    sessionStorage.removeItem('auth-token');
  }

  canActivate(): CanActivateFn {
    return (route, state) => {
      if (!this.isAuthenticated()) {
        this.router.navigate(['login'])
        return false;
      }
      return true;
    };
  }

  getCurrentUser(): string | null {
    return sessionStorage.getItem('username');
  }
}
