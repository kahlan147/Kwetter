import { Component, OnInit } from '@angular/core';
import { UserService} from "../shared/user.service";
import { User} from "../shared/user";
import {Router} from "@angular/router";
import {Observable} from "rxjs";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  public allDataFetched: boolean = false;
  public users: any = [];
  public user: User;

  constructor(public userService: UserService) {
    }


  ngOnInit() {
    this.loadUser();
  }

  public loadUser(){
      this.userService.getUser(1).subscribe(data => {
      this.user = data;
      this.loadFollowers(this.user.id);
    })
  }

  public loadAllUsers(){
    this.userService.getListUsers().subscribe(data => {
      this.users = data;
    })
  }

  public loadFollowers(id : bigint){
    this.userService.getFollowers(id).subscribe(data => {
      this.users = data;
      this.allDataFetched = true;
    })
  }

  public loadFollowing(id: bigint){
    this.userService.getFollowings(id).subscribe(data => {
      this.users = data;
      this.allDataFetched = true;
    })
  }

}
