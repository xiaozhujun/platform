package org.whut.monitor.business.communication.message;

import org.apache.activemq.command.ActiveMQTextMessage;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.whut.monitor.business.communication.service.SensorDataService;
import org.whut.monitor.business.communication.websocket.WebsocketEndPoint;
import org.whut.monitor.business.monitor.service.CollectorService;
import org.whut.platform.fundamental.config.FundamentalConfigProvider;
import org.whut.platform.fundamental.logger.PlatformLogger;
import org.whut.platform.fundamental.message.impl.PlatformMessageListenerBase;
import org.whut.platform.fundamental.redis.connector.RedisConnector;

import javax.jms.JMSException;
import javax.jms.Message;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * Created with IntelliJ IDEA.
 * User: xiaozhujun
 * Date: 14-6-5
 * Time: 下午8:49
 * To change this template use File | Settings | File Templates.
 */
public class SensorMessageListener extends PlatformMessageListenerBase{

    public static final PlatformLogger logger = PlatformLogger.getLogger(SensorMessageListener.class);

    @Autowired
    private SensorDataService sensorDataService;
    @Autowired
    private CollectorService collectorService;
    private RedisConnector redisConnector = new RedisConnector();

    @Override
    public String getMessageName() {
        return Constants.SENSOR_QUEUE_DESTINATION;
    }

    @Override
    public void onMessage(Message message) {
        int keyExpireTime = Integer.parseInt(FundamentalConfigProvider.get("redis.key.expire"));
        String number = "";
        String lastDate = "";
        String sensorData="";
        String sensorNum="";
        //String collectorNum="";
        if (message instanceof ActiveMQTextMessage){
            try {
                String messageText = ((ActiveMQTextMessage) message).getText();
                logger.info("onMessage data: "+messageText);
                try {
                    WebsocketEndPoint webSocket = new WebsocketEndPoint();
                    String sNum=webSocket.getTempMessage();
                    webSocket.sendMessage(messageText);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                sensorDataService.saveMessage(messageText);
                try{
                    JSONObject dataJson=new JSONObject(messageText);
                    JSONArray data= dataJson.getJSONArray("sensors");
                    JSONObject info=data.getJSONObject(0);
                    number=info.getString("sensorNum");
                    redisConnector.set("sensorNum",number);
                    System.out.println("number:"+number);
                    lastDate = redisConnector.get("sensor:{"+number+"}:lastDate");
                    switch (isNormal(number,lastDate)){
                        case 0:collectorService.updateStatusByNumber(redisConnector.get("sensor:{"+number+"}:collector"),"在线正常工作");
                            break;
                        case 1:collectorService.updateStatusByNumber(redisConnector.get("sensor:{"+number+"}:collector"),"离线或异常");
                            break;
                        case 2:collectorService.updateStatusByNumber(redisConnector.get("sensor:{"+number+"}:collector"),"暂无数据");
                            break;
                    }
                }
                catch (JSONException e){
                    collectorService.updateTimeByNumber(redisConnector.get("sensor:{"+number+"}:collector"),lastDate);
                    e.printStackTrace();
                }
            } catch (JMSException e) {
                collectorService.updateTimeByNumber(redisConnector.get("sensor:{"+number+"}:collector"),lastDate);
                logger.error(e.getMessage());
            }
        }else{
            logger.error("message not text,but "+message.getClass().getName());
        }
    }
    public int isNormal(String number,String lastDate){
//        String lastDate = redisConnector.get("sensor:{"+number+"}:lastDate");
        int flag=0;
        String lastMessageTime = lastDate;
        if (redisConnector.get("sensor:{"+number+"}:collector") == null) {
            String collectorNum = collectorService.getCollectNumberBySensorNumber(number);
            redisConnector.set("sensor:{"+number+"}:collector",collectorNum);
        }
        if (lastDate!=null){
            System.out.println("lastDate "+lastDate);
            redisConnector.set("sensor:{"+redisConnector.get("sensor:{"+number+"}:collector")+"}:collectorTime",lastDate);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                Date date = sdf.parse(lastDate);
                Date now=new Date();
                long dif = (now.getTime()- date.getTime())/(1000);
                System.out.println("dif:"+dif);

                if (dif >60 || (redisConnector.get("sensor:{"+number+"}:collector").equals("") || redisConnector.get("sensor:{"+number+"}:collector") == null)) {
                    flag=1;
                    //collectorService.updateStatusByNumber(redisConnector.get("sensor:{"+number+"}:collector"),"离线或异常");
                }
                else {
                    flag = 0;
                    //collectorService.updateStatusByNumber(redisConnector.get("sensor:{"+number+"}:collector"),"在线正常工作");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else{
            flag = 2;
            //collectorService.updateStatusByNumber(redisConnector.get("sensor:{"+number+"}collector"),"暂无数据");
        }
        return flag;
    }
}
