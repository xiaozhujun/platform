package org.whut.platform.fundamental.websocket.handler;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.whut.platform.business.user.security.MyUserDetail;
import org.whut.platform.fundamental.communication.api.MessageDispatcher;
import org.springframework.security.core.context.SecurityContext;
import org.whut.platform.fundamental.logger.PlatformLogger;

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
    private static final PlatformLogger logger = PlatformLogger.getLogger(WebsocketEndPoint.class);
    private static String ReceivedMessage;    //收到的消息
    private static List<WebSocketSession> wssList;   // ReceivedMessage对应的 WebSocketSession列表
    private static Map<WebSocketSession,String> map=new HashMap<WebSocketSession, String>(); // 一个session对应一个message 维护 session和message的关系
    private static Map<String,List<WebSocketSession>> wsImpMap=new HashMap<String, List<WebSocketSession>>(); //一个message对应多个session，供通过websocket向前台发送消息使用
    private static Map<WebSocketSession,Long> sessionAndAppIdMap= new HashMap<WebSocketSession,Long>();  //维护session和appId的关系，

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        Map<String,Object> sessionAttributesMap= session.getHandshakeAttributes();
        Object credential=((SecurityContext) sessionAttributesMap.values().toArray()[0]).getAuthentication().getPrincipal();
        Long appId=((MyUserDetail) credential).getAppId();
        logger.info("appId= " + appId);
        sessionAndAppIdMap.put(session,appId);
        ReceivedMessage = message.getPayload();
        map.put(session,ReceivedMessage);
        JSONObject dataJson=new JSONObject(ReceivedMessage);
        JSONArray sNum= dataJson.getJSONArray("sensors");
        String command=dataJson.getString("c");
        for(int i=0;i<sNum.length();i++){
            wssList=wsImpMap.get(sNum.get(i).toString()+appId);
            if(wssList==null){
                wssList=new ArrayList<WebSocketSession>();
            }
            wssList.add(session);
            if (command.equals("Subscribe")) {    //订阅
                wsImpMap.put(sNum.get(i).toString()+appId,wssList);
            }
            else{
                logger.info("进行取消订阅！");
            }
            wssList=null;
        }
        super.handleTextMessage(session, message);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        String msg= map.get(session);     //获得接收到的sNum 数组
        Long appId=sessionAndAppIdMap.get(session);
        JSONObject dataJson=new JSONObject(ReceivedMessage);
        JSONArray sNum= dataJson.getJSONArray("sensors");
        for(int i=0;i<sNum.length();i++){
            List<WebSocketSession> webSocketSessionList=wsImpMap.get(sNum.get(i).toString()+appId);
            webSocketSessionList.remove(session);
            wsImpMap.remove(msg);
            wsImpMap.put(msg,webSocketSessionList);
        }
        map.remove(session);
        sessionAndAppIdMap.remove(session);
        logger.info("连接关闭！");
        super.afterConnectionClosed(session, status);
    }

    public static Map<String,List<WebSocketSession>> getWsImpMap(){
        return wsImpMap;
    }
}