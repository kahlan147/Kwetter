package Websocket;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;


@ServerEndpoint("/api/ServerEndPoint")
public class ServerEndPoint {
    @OnOpen
    public void onOpen(Session session){
        /*try {
            //session.getBasicRemote().sendText("Client is now connected...");
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    @OnMessage
    public void onMessage(Session session, String message) {
        try{
            for(Session sess: session.getOpenSessions()){
                if(sess.isOpen()){
                    sess.getBasicRemote().sendText(message);
                }
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    @OnClose
    public void onClose(){

    }

    @OnError
    public void onError(Throwable t){
        t.printStackTrace();
    }

}
