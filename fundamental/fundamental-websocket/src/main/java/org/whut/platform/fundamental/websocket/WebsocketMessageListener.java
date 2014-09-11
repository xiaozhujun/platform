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
    private RedisConnector redisConnector = new RedisConnector();
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
                    sendMsg(number, messageText);//向websocket通道发数据
                }
                catch (JSONException e){
                    e.printStackTrace();
                }
            }catch (Exception e){

            }
        }
    }
    public void sendMsg(String number,String messageText){
        Map<WebSocketSession,String> tempMap=webSocket.getTempMessage();
        TextMessage returnMessage = new TextMessage(messageText);
        try {
            for(WebSocketSession key : tempMap.keySet()){
                String s=  tempMap.get(key);
                String s1[]=s.split("\\|");
                String sNum=s1[0];
                String page=s1[1];
                if(page.equals("1")) {
                    if(number.equals(sNum)){
                        key.sendMessage(returnMessage);
                        System.out.println("aaaaaaaaaaaaaaaaa"+messageText);
                    }
                }
                else if(page.equals("2")){
                    key.sendMessage(returnMessage);
                    System.out.println("成功！+“qqqqqqqqqqqqqqqqq”");
                }
            }


        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
