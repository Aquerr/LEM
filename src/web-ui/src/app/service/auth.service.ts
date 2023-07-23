import {Injectable} from '@angular/core';
import {map, Observable} from "rxjs";
import {Router} from "@angular/router";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {JwtTokenResponse} from "../model/jwt.model";
import {Authority} from "../model/authority.model";
import {NotificationService} from "./notification.service";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private readonly API_AUTH_URL = `${environment.apiBaseUrl}/auth`;
  private readonly API_MYSELF_URL = `${environment.apiBaseUrl}/auth/myself`;
  private readonly API_INVALIDATE_URL = `${environment.apiBaseUrl}/auth/invalidate`;

  constructor(private router: Router,
              private httpClient: HttpClient,
              private notificationService: NotificationService) { }

  authenticate(signInRequest: { password: string; username: string }): Observable<any> {
    return this.httpClient.post<JwtTokenResponse>(this.API_AUTH_URL, signInRequest, {
      observe: 'response'
    })
      .pipe(map(userData => {
        console.log(userData);
        const jwtTokenResponse = userData.body;
        sessionStorage.setItem('user', JSON.stringify(jwtTokenResponse));
        return userData;
      }));
  }

  isAuthenticated(): boolean {
    return sessionStorage.getItem('user') !== null;
  }

  logout(): void {
    this.httpClient.post(this.API_INVALIDATE_URL, {}).subscribe();
    sessionStorage.removeItem('user');
    this.router.navigate(['home']);
  }

  canActivate(): boolean {
    if (!this.isAuthenticated()) {
      this.router.navigate(['login'])
      return false;
    }
    return true;
  }

  getCurrentUser(): string | null {
    const user = sessionStorage.getItem('user');
    if (user === null)
      return null;
    return (JSON.parse(user) as JwtTokenResponse).username;
  }

  getAuthToken(): string | null {
    const user = sessionStorage.getItem('user');
    if (user === null)
      return null;
    return (JSON.parse(user) as JwtTokenResponse).jwt;
  }

  hasAuthority(authority: Authority): boolean {
    return this.getUserAuthorities().includes(authority);
  }

  checkAuthorityAndDisplayToast(authority: Authority) {
    const hasAuthority = this.hasAuthority(authority);
    if (!hasAuthority) {
      this.notificationService.errorNotification("Access Denied", "You don't have enough permissions.");
    }
  }

  getUserAuthorities(): Authority[] {
    const user = sessionStorage.getItem('user');
    if (user === null)
      return [];
    return (JSON.parse(user) as JwtTokenResponse).authorities;
  }
}
