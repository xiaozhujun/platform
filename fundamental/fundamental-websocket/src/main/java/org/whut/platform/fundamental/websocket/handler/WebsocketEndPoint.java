package org.whut.platform.fundamental.websocket.handler;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.whut.platform.business.user.security.MyUserDetail;
import org.springframework.security.core.context.SecurityContext;
import org.whut.platform.fundamental.command.CommandProcessing;
import org.whut.platform.fundamental.config.FundamentalConfigProvider;
import org.whut.platform.fundamental.logger.PlatformLogger;

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
    private static Map<WebSocketSession,String> map=new HashMap<WebSocketSession, String>(); // 一个session对应一个message 维护 session和message的关系
    private static Map<WebSocketSession,Long> sessionAndAppIdMap= new HashMap<WebSocketSession,Long>();  //维护session和appId的关系，

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        //以下三行获得此session对应的租户
        Map<String,Object> sessionAttributesMap= session.getHandshakeAttributes();
        Object credential=((SecurityContext) sessionAttributesMap.values().toArray()[0]).getAuthentication().getPrincipal();
        Long appId=((MyUserDetail) credential).getAppId();
        Long userId=((MyUserDetail) credential).getId();
        logger.info("appId= " + appId);
        logger.info("userId= " + userId);
        //维护起session和appId的关系，一个session可以对应多个appId
        sessionAndAppIdMap.put(session,appId);
        //得到前台发来的消息，里面可能包含多个sNum
        String  ReceivedMessage = message.getPayload();
        map.put(session,ReceivedMessage);
        JSONObject dataJson=new JSONObject(ReceivedMessage);
        //得到sNum的数组
        JSONArray sNum= dataJson.getJSONArray(FundamentalConfigProvider.get("message.nioServer.type"));
        //得到前台发来的命令：取消订阅、订阅
        String command=dataJson.getString("c");
        if (command.equals("cancelSubscribe")){    //取消订阅
            logger.info("取消订阅: "+ReceivedMessage);
            CommandProcessing.cancelSubscribe(ReceivedMessage, appId, userId);
        } else if (command.equals("Subscribe")){    //订阅
            logger.info("订阅: "+ReceivedMessage);
            CommandProcessing.subscribe(appId, userId, sNum, session);
        }else{
            logger.info("无法解析的消息: "+ReceivedMessage);
        }
        super.handleTextMessage(session, message);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        CommandProcessing.cancelSubscribe(map,session,sessionAndAppIdMap);
        map.remove(session);
        sessionAndAppIdMap.remove(session);
        logger.info("连接关闭！");
        super.afterConnectionClosed(session, status);
    }
    public static Map<String,List<WebSocketSession>> getWsImpMap(){
        return CommandProcessing.wsImpMap;
    }
}