import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'lem-header-time',
  templateUrl: './header-time.component.html',
  styleUrls: ['./header-time.component.css']
})
export class HeaderTimeComponent implements OnInit {
  currentTime: Date = new Date();

  ngOnInit(): void {
    setInterval(() => {
      this.currentTime = new Date();
    }, 1000);
  }

}
