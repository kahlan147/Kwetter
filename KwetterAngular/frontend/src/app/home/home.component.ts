import { Component, OnInit } from '@angular/core';
import { UserService} from "../shared/user.service";
import { User} from "../shared/user";
import {CookieService} from "ngx-cookie-service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  public allDataFetched: boolean = false;
  public followers: any = [];
  public following: any = [];
  public loggedInUser: User;

  constructor(public userService: UserService, private cookieService: CookieService, private router: Router) {
    if(this.cookieService.get("LoggedInUser") == ''){
      this.router.navigateByUrl("/login")
    }
  }

  ngOnInit() {
    this.loadUser();
  }

  public loadUser(){
      this.loggedInUser = JSON.parse(this.cookieService.get("LoggedInUser"));
      this.loadFollowers(this.loggedInUser.id);
  }

  public loadFollowers(id : bigint){
    this.userService.getFollowers(id).subscribe(data => {
      this.followers = data;
      this.loadFollowing(id);
    })
  }

  public loadFollowing(id: bigint){
    this.userService.getFollowings(id).subscribe(data => {
      this.following = data;
      this.allDataFetched = true;
    })
  }

}
