package Websocket;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;


@ServerEndpoint("/ServerEndPoint")
public class ServerEndPoint {
    @OnOpen
    public void onOpen(Session session){
        try {
            session.getBasicRemote().sendText("Client is now connected...");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @OnMessage
    public String onMessage(Session session, String message) {
        String reply = "echo " + message;
        return reply;
    }

    @OnClose
    public void onClose(){

    }

    @OnError
    public void onError(Throwable t){
        t.printStackTrace();
    }

}
