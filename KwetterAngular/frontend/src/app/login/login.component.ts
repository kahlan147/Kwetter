import { Component, OnInit } from '@angular/core';
import {CookieService} from "ngx-cookie-service";
import { UserService} from "../shared/user.service";
import { Router } from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  private username: String;
  private password: String;

  constructor(public userService: UserService, private cookieService: CookieService, private router: Router) {
    if(this.cookieService.get("LoggedInUser") != ''){
      this.router.navigateByUrl("/home")
    }
  }

  ngOnInit() {
  }

  OnClick_LogIn(){
    new Promise(() => {
      this.userService.login(this.username, this.password).toPromise().then(
        res => {
          this.cookieService.set("LoggedInUser", JSON.stringify(res));
          this.router.navigateByUrl("/home")
        }
      )
    });
  }

  OnClick_Register(){
    console.log("data: ");
    console.log(this.username);
    console.log(this.password);
  }

}
