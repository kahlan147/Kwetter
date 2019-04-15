import { Component, OnInit } from '@angular/core';
import { UserService} from "../shared/user.service";
import { User} from "../shared/user";
import {CookieService} from "ngx-cookie-service";
import {Router} from "@angular/router";
import {PostService} from "../shared/post.service";
import {Post} from "../shared/post";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  public allDataFetched: boolean = false;
  public last10Kweets: any = [];
  public followers: any = [];
  public following: any = [];
  public loggedInUser: User;

  public newKweetMessage: String;

  constructor(public userService: UserService, public postService: PostService,
              private cookieService: CookieService, private router: Router) {
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
      this.loadLast10Kweets();
    })
  }

  public loadLast10Kweets(){
    new Promise(()=>{
      this.postService.getLast10Posts(this.loggedInUser.id).toPromise().then(
        res => {
          this.last10Kweets = res;
          this.allDataFetched = true;
        }
      )
    });
  }

  public createKweet(){
    new Promise(()=>{
      this.postService.createPost(this.newKweetMessage, this.loggedInUser).toPromise().then(
        res => {
          let post= <Post>res;
          new Promise(()=>{
            this.postService.bindPost(post.id, this.loggedInUser.id).toPromise().then(
              res2 => {
                window.location.reload();
              }
            )
          })
        }
      )
    });
  }

}
