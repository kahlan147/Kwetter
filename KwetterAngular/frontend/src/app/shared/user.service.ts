import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import {retry, catchError, tap} from 'rxjs/operators';
import { User } from '../shared/user';
import {CookieService} from "ngx-cookie-service";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  apiURL = '//localhost:8080/Kwetter/api/users';

  constructor(private http: HttpClient, private cookieService: CookieService) { }

  httpOption ={
    headers: new HttpHeaders({
      'Content-Type':'application/json'
    })
  };

  httpOptionAuth ={
    headers: new HttpHeaders({
      'Content-Type':'application/json',
      'Authorization': this.cookieService.get('access_token')
    })
  };

  loginAuth(username: String, password: String): Observable<any>{
    return this.http.post<{access_token: string}>(this.apiURL + "/loginAuth/" + username + "/" + password, null, this.httpOptionAuth)
      .pipe(tap(res =>{
        this.cookieService.set('access_token', res.access_token);
      }),retry(1),catchError(this.handleError));
  }

  createUserAuth(username: String, password: String): Observable<any> {
    let user = new User();
    user.name = username;
    user.password = password;
    return this.http.post<{access_token}>(this.apiURL, JSON.stringify(user), this.httpOptionAuth)
      .pipe(tap(res =>{
        this.loginAuth(username, password);
      }),retry(1),catchError(this.handleError));
  }

  login(username: String, password: String){
    return this.http.get(this.apiURL + "/login/" + username + "/" + password)
      .pipe(retry(1),catchError(this.handleError))
  }

  createUser(username: String, password: String): Observable<any> {
    let user = new User();
    user.name = username;
    user.password = password;
    return this.http.post<User>(this.apiURL, JSON.stringify(user), this.httpOption)
      .pipe(
        retry(1),
        catchError(this.handleError)
      )
  }

  getListUsers(): Observable<User> {
    return this.http.get<User>(this.apiURL)
      .pipe(
        retry(1),
        catchError(this.handleError)
      )
  }

  // HttpClient API get() method => Fetch employee
  getUser(id) {
    return this.http.get<User>(this.apiURL + '/' + id)
      .pipe(
        retry(1),
        catchError(this.handleError)
      )
  }

  getUserByUrl(url){
    return this.http.get<User>(url)
      .pipe(
        retry(1),
        catchError(this.handleError)
      )
  }

  getUsersByName(name){
    return this.http.get<User>(this.apiURL + '/byName/' + name)
      .pipe(
        retry(1),
        catchError(this.handleError)
      )
  }

  updateUser(user){
    return this.http.put<User>(this.apiURL, JSON.stringify(user), this.httpOption)
      .pipe(
        retry(1),
        catchError(this.handleError)
      )
  }

  follow(user, id) : Observable<User>{
    return this.http.put<User>(this.apiURL + '/' + user.userId + "/follow/" + id, JSON.stringify(user), this.httpOption)
      .pipe(
        retry(1),
        catchError(this.handleError)
      )
  }

  unFollow(user, id) : Observable<User>{
    return this.http.put<User>(this.apiURL + '/' + user.userId + '/unfollow/' + id, JSON.stringify(user), this.httpOption)
      .pipe(
        retry(1),
        catchError(this.handleError)
      )
  }

  getFollowers(id){
    return this.http.get<User>(this.apiURL + '/' + id + '/followers')
      .pipe(
        retry(1),
        catchError(this.handleError)
      )
  }

  getFollowings(id) : Observable<User>{
    return this.http.get<User>(this.apiURL + '/' + id + '/followings')
      .pipe(
        retry(1),
        catchError(this.handleError)
      )
  }

  handleError(error) {
    let errorMessage = '';
    if(error.error instanceof ErrorEvent) {
      // Get client-side error
      errorMessage = error.error.message;
    } else {
      // Get server-side error
      errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
    }
    window.alert(errorMessage);
    return throwError(errorMessage);
  }

}
