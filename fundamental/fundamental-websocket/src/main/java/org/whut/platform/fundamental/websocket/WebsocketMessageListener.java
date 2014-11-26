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
import org.whut.platform.fundamental.websocket.handler.WebsocketEndPoint;

import javax.jms.Message;
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
                    Long appId=Long.parseLong(info.getString("appId"));
                    Long wsNumber = Long.parseLong(number);
                    logger.info("number1:" + wsNumber);
                    sendMsg(wsNumber.toString(),appId, messageText);//向websocket通道发数据
                }
                catch (JSONException e){
                    logger.error(e.getMessage());
                    e.printStackTrace();
                }
            }catch (Exception e){
                logger.error(e.getMessage());
                e.printStackTrace();
            }
        }
    }
    public void sendMsg(String number,Long appId,String messageText){
        Map<String,List<WebSocketSession>> wsImpMap=WebsocketEndPoint.getWsImpMap();
        logger.info("wsImpMap中有："+wsImpMap);
        TextMessage wsMessage = new TextMessage(messageText);
        try {
                logger.info("number+appId："+number+appId);
                List<WebSocketSession> wSSList=wsImpMap.get(appId+":"+number);
                if (wSSList!=null){
                    for(int i=0;i<wSSList.size();i++){
                        wSSList.get(i).sendMessage(wsMessage);
                    }
            }
        } catch (Exception exception) {
            logger.error(exception.getMessage());
            exception.printStackTrace();
        }
    }
}
