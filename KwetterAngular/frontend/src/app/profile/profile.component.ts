import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, NavigationEnd} from "@angular/router";
import {User} from "../shared/user";
import {UserService} from "../shared/user.service";
import {CookieService} from "ngx-cookie-service";
import {Router} from "@angular/router";
import {Post} from "../shared/post";
import {PostService} from "../shared/post.service";
import {catchError, retry} from "rxjs/operators";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  finishedLoading: boolean = false;
  myPage: boolean = false;
  userOrPost: boolean = false;
  isFollowing: boolean = true;

  profileUser: User;
  loggedInUser: User;

  profilePosts: Post;
  profileFollowers: User;
  profileFollowing: User;

  shownList: any;

  amountFollowers;
  amountFollowing;
  amountKweets;

  navigationSubscription;

  constructor(private route: ActivatedRoute, private userService: UserService,
              private cookieService: CookieService, private router: Router,
              private postService: PostService) {


    if(this.cookieService.get("LoggedInUser") == ''){
      this.router.navigateByUrl("/login");
    }

    this.navigationSubscription = this.router.events.subscribe((e: any) => {
      if (e instanceof NavigationEnd) {
        this.ngOnInit();
      }
    })
  }

  ngOnInit() {
      var id;
      this.route.paramMap.subscribe( params => id = params.get('id'));
      try {
        this.loggedInUser = JSON.parse(this.cookieService.get("LoggedInUser"));
      }
      catch(e){

      }
      if(id == '' || id == undefined){
        this.router.navigateByUrl("/home");
      }
      this.getUser(id);

  }

  getUser(id){
    this.userService.getUser(id).subscribe(data => {
      this.profileUser = data;
      if(this.loggedInUser.id == this.profileUser.id){
        this.myPage = true;
      }
      this.getPosts(id);
    })
  }

  getPosts(id){
    this.postService.getAllPosts(id).subscribe(data =>{
      this.profilePosts = data;
      this.amountKweets = (<Array<Post>><unknown>this.profilePosts).length;
      this.getFollowers(id);
    })
  }

  getFollowers(id : bigint){
    this.userService.getFollowers(id).subscribe(data => {
      this.profileFollowers = data;
      this.amountFollowers = (<Array<User>><unknown>this.profileFollowers).length;
      try{(<Array<User>><unknown>this.profileFollowers).find(x => x.id == this.loggedInUser.id)}
      catch(e){
        this.isFollowing = false;
      }
      this.getFollowing(id);
    })
  }

  getFollowing(id: bigint){
    this.userService.getFollowings(id).subscribe(data => {
      this.profileFollowing = data;
      this.amountFollowing = (<Array<User>><unknown>this.profileFollowing).length
      this.finishedLoading = true;
    })
  }

  showData(data: number){
    switch(data){
      case 1:
        this.shownList = this.profileFollowers;
        this.userOrPost = true;
        break;
      case 2:
        this.shownList = this.profileFollowing;
        this.userOrPost = true;
        break;
      case 3:
        this.shownList = this.profilePosts;
        this.userOrPost = false;
        break;
    }
  }

  updateUser(){
    new Promise(()=>{
      this.userService.updateUser(this.profileUser).toPromise().then(
        res => {
          console.log(res);
        }
      )
    })
    ;
  }

  follow(){
    new Promise(()=>{
      this.userService.follow(this.loggedInUser, this.profileUser.id).toPromise().then(
        res => {
          if(res){
            window.location.reload();
          }
        }
      )
    });
  }

  unfollow(){
    new Promise(()=>{
      this.userService.unFollow(this.loggedInUser, this.profileUser.id).toPromise().then(
        res => {
          if(res){
            window.location.reload();
          }
        }
      )
    });
  }
}
