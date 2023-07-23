import {Component, OnInit} from '@angular/core';
import {LogsService} from "../../service/logs.service";
import {LogType} from "../../model/log.model";

@Component({
  selector: 'app-logs',
  templateUrl: './logs.component.html',
  styleUrls: ['./logs.component.css']
})
export class LogsComponent implements OnInit {

  availableLogTypes: LogType[] = [];

  constructor(private logsService: LogsService) {}

  ngOnInit(): void {
    this.logsService.getAvailableLogTypes().subscribe(response => {
      this.availableLogTypes = response.availableLogTypes;
    });
  }

  showLogs(logType: LogType) {

  }
}
