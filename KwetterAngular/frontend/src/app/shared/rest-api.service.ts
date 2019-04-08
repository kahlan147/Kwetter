import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';
import { User } from '../shared/user';

@Injectable({
  providedIn: 'root'
})
export class RestApiService {

  apiURL = '//localhost:8080/Kwetter/api/users';

  constructor(private http: HttpClient) { }

  httpOption ={
    headers: new HttpHeaders({
      'Content-Type':'application/json'
    })
  }

  createUser(user): Observable<any> {
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
  getUser(id): Observable<User> {
    return this.http.get<User>(this.apiURL + '/' + id)
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
