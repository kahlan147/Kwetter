import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';
import {Post} from "./post";
import {User} from "./user";

@Injectable({
  providedIn: 'root'
})
export class PostService {

  apiURL = '//localhost:8080/Kwetter/api/posts';

  httpOption ={
    headers: new HttpHeaders({
      'Content-Type':'application/json'
    })
  };

  constructor(private http: HttpClient) {
  }

  getAllPosts(id): Observable<Post>{
    return this.http.get<Post>(this.apiURL + '/' + id + '/allposts')
      .pipe(
        retry(1),
        catchError(this.handleError)
      )
  }

  createPost(message: String, user: User){
    let post = new Post();
    post.message = message;
    post.date = new Date();
    post.poster = user;
    return this.http.post(this.apiURL, JSON.stringify(post), this.httpOption).pipe(
      retry(1),
      catchError(this.handleError)
    )
  }

  getLast10Posts(id){
    return this.http.get(this.apiURL + '/'+ id + '/last10posts').pipe(
      retry(1),
      catchError(this.handleError)
    )
  }

  bindPost(postId, userId){
    return this.http.put(this.apiURL + '/' + postId + '/add/' + userId, '').pipe(
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
