import { Component, OnInit } from '@angular/core';
import { RestApiService} from "../shared/rest-api.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  users;
  user;

  constructor(restApiService: RestApiService) {
    this.users = restApiService.getListUsers();
    }


  ngOnInit() {
  }

}
