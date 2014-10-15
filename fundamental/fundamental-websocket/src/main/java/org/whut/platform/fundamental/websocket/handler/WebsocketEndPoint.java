package org.whut.platform.fundamental.websocket.handler;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
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

    private static String ReceivedMessage;    //收到的消息
    private static List<WebSocketSession> wssList;   // ReceivedMessage对应的 WebSocketSession列表
    private static Map<WebSocketSession,String> map=new HashMap<WebSocketSession, String>(); // 一个session对应一个message 维护 session和message的关系
    private static Map<String,List<WebSocketSession>> wsImpMap=new HashMap<String, List<WebSocketSession>>(); //一个message对应多个session，供通过websocket向前台发送消息使用

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        ReceivedMessage = message.getPayload();
        map.put(session,ReceivedMessage);
        wssList=wsImpMap.get(ReceivedMessage);
        if(wssList==null){
            wssList=new ArrayList<WebSocketSession>();

        }
         wssList.add(session);
        try{
            System.out.println(ReceivedMessage);
            JSONObject dataJson=new JSONObject(ReceivedMessage);
            JSONArray sNum= dataJson.getJSONArray("sensors");
            System.out.println("sNum为，"+sNum+"长度为"+sNum.length());
            String info=dataJson.getString("c");
            System.out.println("sNum为，"+info);
            if (info.equals("Subscribe")) {    //订阅
                for(int i=0;i<sNum.length();i++){
                    wsImpMap.put(sNum.get(i).toString(),wssList);
                }
            }
            else{
                System.out.println("进行取消订阅");
            }
        }
        catch (JSONException e){
            e.printStackTrace();
        }
        wssList=null;
        super.handleTextMessage(session, message);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
       String msg= map.get(session);
       List<WebSocketSession> webSocketSessionList=wsImpMap.get(msg);
       webSocketSessionList.remove(session);
        wsImpMap.remove(msg);
        wsImpMap.put(msg,webSocketSessionList);
       map.remove(session);
       System.out.println("连接关闭！");
       super.afterConnectionClosed(session, status);
    }

    public static Map<String,List<WebSocketSession>> getTempMessage(){
        return wsImpMap;
    }
}