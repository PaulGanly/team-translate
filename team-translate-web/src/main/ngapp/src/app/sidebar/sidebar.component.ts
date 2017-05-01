import { Component, OnInit } from '@angular/core';
import {MaterializeDirective} from 'angular2-materialize';


@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit {
  userName = 'Paul Ganly';
  userEmail = 'paulganly85@gmail.com';

  constructor() {
  }

  ngOnInit() {
  }

}
