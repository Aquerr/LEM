import { Injectable } from '@angular/core';
import {MessageService} from "primeng/api";

@Injectable({
  providedIn: 'root'
})
export class NotificationService {

  constructor(private messageSerivce: MessageService) { }

  warningNotification(title: string, message: string) {
    this.messageSerivce.add({
      severity: 'warn',
      summary: title,
      detail: message
    });
  }

  errorNotification(title: string, message: string) {
    this.messageSerivce.add({
      severity: 'error',
      summary: title,
      detail: message
    });
  }
}
