package org.whut.platform.fundamental.websocket.handler;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: tgq
 * Date: 14-8-19
 * Time: 下午8:07
 * To change this template use File | Settings | File Templates.
 */
public class WebsocketEndPoint extends TextWebSocketHandler {


    private static String  tempMessage;
    private static List<WebSocketSession> wsList=new ArrayList<WebSocketSession>();
    private static Map<WebSocketSession,String> map=new HashMap<WebSocketSession, String>();
    private static Map<String,List<WebSocketSession>> map1=new HashMap<String, List<WebSocketSession>>();

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        tempMessage = message.getPayload();
       map.put(session,tempMessage);
        super.handleTextMessage(session, message);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        map.remove(session);
        System.out.println("连接关闭！");
        super.afterConnectionClosed(session, status);
    }

    public static Map<WebSocketSession,String> getTempMessage(){
        return map;
    }
    public static void main(String[] args) throws IOException {
        String s="{sensors:[{202,365]}]}";
        System.out.println(s.length());
        int startIndex = s.indexOf("{sensors:[{");
        System.out.println(startIndex);
        int endIndex = s.indexOf("}]}",startIndex);
        System.out.println(endIndex);
    }

}