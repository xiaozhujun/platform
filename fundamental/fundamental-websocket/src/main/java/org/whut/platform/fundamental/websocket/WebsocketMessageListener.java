package org.whut.platform.fundamental.websocket;

import org.apache.activemq.command.ActiveMQTextMessage;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.whut.platform.fundamental.logger.PlatformLogger;
import org.whut.platform.fundamental.message.impl.PlatformMessageListenerBase;
import org.whut.platform.fundamental.redis.connector.RedisConnector;
import org.whut.platform.fundamental.websocket.handler.WebsocketEndPoint;

import javax.jms.Message;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: tgq
 * Date: 14-9-2
 * Time: 下午8:05
 * To change this template use File | Settings | File Templates.
 */
public class WebsocketMessageListener extends PlatformMessageListenerBase {

    public static final PlatformLogger logger=PlatformLogger.getLogger(WebsocketMessageListener.class);

    @Autowired
    private WebsocketEndPoint webSocket;
    @Override
    public String getMessageName() {
        return Constants.WWBSOCKEY_QUEUE_DESTINATION;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onMessage(Message message) {
        String number = "";
        if (message instanceof ActiveMQTextMessage){
            try {
                String messageText = ((ActiveMQTextMessage) message).getText();
                logger.info("onMessage data: "+messageText);
                try{
                    JSONObject dataJson=new JSONObject(messageText);
                    JSONArray data= dataJson.getJSONArray("sensors");
                    JSONObject info=data.getJSONObject(0);
                    number=info.getString("sensorNum");
                    Long wsNumber = Long.parseLong(number);
                    System.out.println("number1:" + wsNumber);
                    sendMsg(wsNumber.toString(), messageText);//向websocket通道发数据
                }
                catch (JSONException e){
                    e.printStackTrace();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    public void sendMsg(String number,String messageText){
        Map<String,List<WebSocketSession>> wsImpMap=webSocket.getTempMessage();
        logger.info("wsImpMap中有："+wsImpMap);
        TextMessage wsMessage = new TextMessage(messageText);
        try {
            List<WebSocketSession> wSSList=wsImpMap.get(number);
            if (wSSList!=null){
                for(int i=0;i<wSSList.size();i++){
                    wSSList.get(i).sendMessage(wsMessage);
                    logger.info("发送num为"+number+"的数据："+messageText);
                }
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
