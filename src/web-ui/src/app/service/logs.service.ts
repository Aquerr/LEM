import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {Observable} from "rxjs";
import {AvailableLogTypesResponse, LogsRequest, LogsResponse} from "../model/log.model";

@Injectable({
  providedIn: 'root'
})
export class LogsService {

  private readonly API_LOGS_AVAILABLE_LOG_TYPES = `${environment.apiBaseUrl}/logs/available-log-types`;
  private readonly API_LOGS = `${environment.apiBaseUrl}/logs/logs`;

  constructor(private httpClient: HttpClient) {}

  getAvailableLogTypes(): Observable<AvailableLogTypesResponse> {
    return this.httpClient.get<AvailableLogTypesResponse>(this.API_LOGS_AVAILABLE_LOG_TYPES);
  }

  getLogs(logsRequest: LogsRequest): Observable<LogsResponse> {
    return this.httpClient.get<LogsResponse>(`${this.API_LOGS}?log_type=${logsRequest.logType}&start_line=${logsRequest.startLine}&lines=${logsRequest.lines}`);
  }
}
