import { Component } from '@angular/core';
import {LogType} from "../../../model/log.model";
import {ActivatedRoute} from "@angular/router";
import {LogsService} from "../../../service/logs.service";

@Component({
  selector: 'app-logs-view',
  templateUrl: './logs-view.component.html',
  styleUrls: ['./logs-view.component.css']
})
export class LogsViewComponent {
  logType!: LogType;
  logs: string[] = ["ERROR: Test test test", "WARN: dasdamsidoamsodiamsd", "waeasoimasoimdasoidms"];
  startLine = 0;
  totalLines = 0;

  constructor(private activatedRoute: ActivatedRoute, private logsService: LogsService) {
    this.logType = this.activatedRoute.snapshot.params['logType'];
    this.logsService.getLogs({logType: this.logType, startLine: this.startLine, lines: 10}).subscribe(response => {
      this.logs = [...this.logs, ...response.lines];
      this.startLine = response.startLine;
      this.totalLines = response.totalLines;
    });
  }

  loadMoreLogs() {
    const moreToLoad = 10;
    this.startLine += 10;
    this.logsService.getLogs({logType: this.logType, startLine: this.startLine, lines: moreToLoad}).subscribe(response => {
      this.logs = [...this.logs, ...response.lines];
      this.startLine = response.startLine;
      this.totalLines = response.totalLines;
    });
  }
}
