package org.whut.platform.fundamental.websocket.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.whut.platform.fundamental.communication.api.MessageDispatcher;

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

    private static String tempMessage;
    private static List<WebSocketSession> wsList;
    private static Map<WebSocketSession,String> map=new HashMap<WebSocketSession, String>();
    private static Map<String,List<WebSocketSession>> map1=new HashMap<String, List<WebSocketSession>>();

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        tempMessage = message.getPayload();
        map.put(session,tempMessage);
        int i=0;
        if(map1.size()==0){
            System.out.println("qwertyuioo");
            wsList=new ArrayList<WebSocketSession>();
            wsList.add(session);
            map1.put(tempMessage,wsList);
            wsList=null;
        }
        else{
            try {
                for(String key : map1.keySet()){
                    if(key.equals(tempMessage)){
                        i=1;
                        wsList=map1.get(key);
                        System.out.println("qwer123456"+wsList);
                        wsList.add(session);
                        System.out.println("qwer1234567890"+wsList);
                        map1.remove(tempMessage);
                        map1.put(tempMessage,wsList);
                        wsList=null;
                    }
                }
                if(i==0){
                    System.out.println("111111111111111");
                    wsList=new ArrayList<WebSocketSession>();
                    wsList.add(session);
                    map1.put(tempMessage,wsList);
                    wsList=null;
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        System.out.println(map1);
        super.handleTextMessage(session, message);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
       String msg= map.get(session);
       List<WebSocketSession> webSocketSessionList=map1.get(msg);
       webSocketSessionList.remove(session);
       map1.remove(msg);
       map1.put(msg,webSocketSessionList);
       map.remove(session);
       System.out.println("连接关闭！");
       super.afterConnectionClosed(session, status);
    }

    public static Map<String,List<WebSocketSession>> getTempMessage(){
        return map1;
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