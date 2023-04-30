import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor, HttpErrorResponse
} from '@angular/common/http';
import {Observable, tap} from 'rxjs';
import {AuthService} from "../service/auth.service";
import {Router} from "@angular/router";
import {MaskService} from "../service/mask.service";
import {NotificationService} from "../service/notification.service";

@Injectable()
export class LemHttpInterceptor implements HttpInterceptor {

  constructor(private router: Router,
              private authService: AuthService,
              private maskService: MaskService,
              private notificationService: NotificationService) {}

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    if (sessionStorage.getItem('username') && sessionStorage.getItem('auth-token')) {
      request = request.clone({
        headers: request.headers.set('Authorization', 'Bearer ' + sessionStorage.getItem('auth-token'))
      });
    }
    return next.handle(request).pipe(tap({
      // next: (event: HttpEvent<any>) => {},
      error: (error) => {
        if (error instanceof HttpErrorResponse) {
          if (error.status === 401) {
            this.authService.logout();
            this.router.navigate(['login']);
            this.maskService.hide();
            this.notificationService.warningNotification("Unauthorized", "You are not authorized.");
          } else if (error.status === 404) {
            this.router.navigate(['']);
            this.maskService.hide();
            this.notificationService.errorNotification("Not found", "Resource has not been found.");
          } else if (error.status === 500) {
            this.router.navigate(['']);
            this.maskService.hide();
            this.notificationService.errorNotification("Server error", "An error occurred on the server.");
          } else {
            console.warn(error.message);
          }
        }
      }
    }
    ))
  }
}
