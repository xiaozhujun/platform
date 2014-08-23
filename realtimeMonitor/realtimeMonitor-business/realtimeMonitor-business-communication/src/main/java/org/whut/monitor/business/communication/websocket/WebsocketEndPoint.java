package org.whut.monitor.business.communication.websocket;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: tgq
 * Date: 14-8-19
 * Time: 下午8:07
 * To change this template use File | Settings | File Templates.
 */
public class WebsocketEndPoint extends TextWebSocketHandler {
    private static WebSocketSession tempSession;
    private static String  tempMessage;
    @Override
    protected void handleTextMessage(WebSocketSession session,
                                     TextMessage message) throws Exception {
        tempSession = session;
        tempMessage = message.getPayload();
        super.handleTextMessage(session, message);
    }
    public void sendMessage(String  request) throws IOException {
        TextMessage returnMessage = new TextMessage(request);
        tempSession.sendMessage(returnMessage);
    }
    public String getTempMessage(){
        return tempMessage;
    }

}