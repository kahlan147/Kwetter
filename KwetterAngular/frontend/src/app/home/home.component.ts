import { Component, OnInit } from '@angular/core';
import { UserService} from "../shared/user.service";
import { User} from "../shared/user";
import {CookieService} from "ngx-cookie-service";
import {Router} from "@angular/router";
import {PostService} from "../shared/post.service";
import {Post} from "../shared/post";
import {WebsocketService} from "../websocket/websocket.service";
import {Observable, Subscription} from "rxjs";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})

export class HomeComponent implements OnInit {

  public allDataFetched: boolean = false;
  public last10Kweets: any = [];
  public allKweets: any = [];
  public followers: any = [];
  public following: any = [];
  public loggedInUser: User;

  public newKweetMessage: String;

  public lastPostedKweet: Post;

  private wsSubscription: Subscription;

  constructor(public userService: UserService, public postService: PostService,
              private cookieService: CookieService, private router: Router, private websocketService: WebsocketService) {
    if(this.cookieService.get("LoggedInUser") == ''){
      this.router.navigateByUrl("/login")
    }

    this.wsSubscription = websocketService.createObservableSocket("ws://localhost:8080/Kwetter/api/ServerEndPoint")
      .subscribe(
        data => this.newKweet(data),
        err => console.log('err'),
        () => console.log('The observable stream is complete')
      );
  }

  ngOnInit() {
    this.loadUser();
  }

  public newKweet(newKweet: string){
      this.lastPostedKweet = <Post>JSON.parse(newKweet);
      this.allKweets.push(<Post>JSON.parse(newKweet));
      console.log(<Post>JSON.parse(newKweet));
  }

  public loadUser(){
      this.loggedInUser = JSON.parse(this.cookieService.get("LoggedInUser"));
      this.loadFollowers(this.loggedInUser.userId);
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
      this.loadAllKweets();
    })
  }

  public loadAllKweets(){
    new Promise(()=>{
      this.postService.getAllPosts(this.loggedInUser.userId).toPromise().then(
        res => {
          this.allKweets = res;
          this.loadLast10Kweets()
        }
      )
    });
  }

  public loadLast10Kweets(){
    new Promise(()=>{
      this.postService.getLast10Posts(this.loggedInUser.userId).toPromise().then(
        res => {
          this.last10Kweets = res;
          this.allDataFetched = true;
        }
      )
    });
  }

  public createKweet(){
    this.websocketService.sendMessage(JSON.stringify(this.newKweetMessage));
    new Promise(()=>{
      this.postService.createPost(this.newKweetMessage, this.loggedInUser).toPromise().then(
        res => {
          let post= <Post>res;
          new Promise(()=>{
            this.postService.bindPost(post.id, this.loggedInUser.userId).toPromise().then(
              res2 => {
                //window.location.reload();
              }
            )
          })
        }
      )
    });
  }

}
