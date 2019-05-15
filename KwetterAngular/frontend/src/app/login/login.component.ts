import { Component, OnInit } from '@angular/core';
import {CookieService} from "ngx-cookie-service";
import { UserService} from "../shared/user.service";
import { Router } from "@angular/router";
import {User} from "../shared/user";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  public username: String;
  public password: String;
  public loginButton: boolean = false;
  public registerButton: boolean = false;

  constructor(public userService: UserService, private cookieService: CookieService, private router: Router) {
    if(this.cookieService.get("LoggedInUser") != ''){
      this.router.navigateByUrl("/home")
    }
  }

  ngOnInit() {
  }

  ButtonClicked(){
    if(this.loginButton){
      this.loginButton = false;
      this.login();
    }
    else if(this.registerButton){
      this.registerButton = false;
      this.register();
    }
  }

  private login() {
    //this.userService.loginAuth(this.username, this.password).toPromise().then(
     // res => {
        this.userService.login(this.username, this.password).toPromise().then(
          res => {
            this.cookieService.set("LoggedInUser", JSON.stringify(res));
            this.router.navigateByUrl("/home")
          }
        );
      //}
    //)
  }

  private register(){
    new Promise(()=>{
      this.userService.createUser(this.username, this.password).toPromise().then(
        res => {
          this.login();
        }
      )
    })
  }

}
