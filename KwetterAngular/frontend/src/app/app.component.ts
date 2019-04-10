import { Component } from '@angular/core';
import { UserService } from "./shared/user.service";
import { CookieService } from 'ngx-cookie-service';
import {Router} from "@angular/router";


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent {
  title = 'Kwetter!';
  searching = false;

  loggedInUser;

  foundUsers: any = [];

  constructor(public userService: UserService, private cookieService: CookieService, private router : Router){
    this.router.events.subscribe(() => this.routerChanged());
  }

  private routerChanged(){
    try {
      this.loggedInUser = JSON.parse(this.cookieService.get("LoggedInUser"));
    }
    catch(e){

    }
  }

  public logout(){
    this.loggedInUser = null;
    this.cookieService.delete("LoggedInUser");
    this.router.navigateByUrl("/login");
  }

  private getAllUsersByName(value: String){
    this.searching = false;
    if(value.length > 0) {
      new Promise(() => {
        this.userService.getUsersByName(value).toPromise().then(
          res => {
            this.foundUsers = res;
            this.searching = true;
          }
        )
      });
    }
  }

}
