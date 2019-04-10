import { Component } from '@angular/core';
import { UserService} from "./shared/user.service";


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent {
  title = 'Kwetter!';
  searching = false;

  users: any = [];

  constructor(public userService: UserService){
    this.getAllUsers();
  }

  private getAllUsers(){
    this.users = this.userService.getListUsers();
  }
}
