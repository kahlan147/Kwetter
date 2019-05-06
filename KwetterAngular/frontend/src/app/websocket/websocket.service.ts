import { Injectable } from '@angular/core';
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})

export class WebsocketService {

  webSocket: WebSocket;
  socketIsOpen = 1;

  constructor() {
  }

  createObservableSocket(url:string):Observable<any>{
    this.webSocket = new WebSocket(url);

    return new Observable(
      observer => {
        this.webSocket.onmessage = (event) => observer.next(event.data);
        this.webSocket.onerror = (event) => observer.error(event);
        this.webSocket.onclose = (event) => observer.complete();

        return () => this.webSocket.close(1000, "The user disconnected");
      }
    );
  }

  sendMessage(message: string): string{
    if(this.webSocket.readyState === this.socketIsOpen){
      this.webSocket.send(message);
      return 'Sent to server ${message}';
    }
    else{
      return 'Message was not sent - the socket is closed';
    }
  }


}
