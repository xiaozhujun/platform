package org.whut.monitor.business.communication.message;

import org.apache.activemq.command.ActiveMQTextMessage;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.whut.monitor.business.algorithm.service.AlgorithmService;
import org.whut.monitor.business.communication.service.CollectorStatusService;
import org.whut.monitor.business.communication.service.SensorDataService;
import org.whut.monitor.business.monitor.service.CollectorService;
import org.whut.monitor.business.monitor.service.SensorService;
import org.whut.platform.business.user.security.UserContext;
import org.whut.platform.fundamental.communication.api.MessageDispatcher;
import org.whut.platform.fundamental.communication.api.WsMessageDispatcher;
import org.whut.platform.fundamental.config.FundamentalConfigProvider;
import org.whut.platform.fundamental.logger.PlatformLogger;
import org.whut.platform.fundamental.message.impl.PlatformMessageListenerBase;
import org.whut.platform.fundamental.redis.connector.RedisConnector;
import org.whut.platform.fundamental.util.json.JsonResultUtils;
import org.whut.platform.fundamental.websocket.WebsocketMessageDispatcher;
import org.whut.platform.fundamental.websocket.handler.WebsocketEndPoint;

import javax.jms.JMSException;
import javax.jms.Message;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
    private SensorService sensorService;



    @Autowired
    private AlgorithmService algorithmService;

    @Autowired
    private CollectorService collectorService;

    @Autowired
    private WsMessageDispatcher wsMessageDispatcher;

    @Autowired
    private CollectorStatusService collectorStatusService;

    private RedisConnector redisConnector = new RedisConnector();

    @Override
    public String getMessageName() {
        return Constants.SENSOR_QUEUE_DESTINATION;
    }


    @Override
    public void onMessage(Message message) {
        ArrayList arrayList=new ArrayList();
        int keyExpireTime = Integer.parseInt(FundamentalConfigProvider.get("redis.key.expire"));
        String number = "";
        String lastDate = "";
        String sensorData="";
        String sensorNum="";

        if (message instanceof ActiveMQTextMessage){
            try {
                String messageText = ((ActiveMQTextMessage) message).getText();
                logger.info("onMessage data: "+messageText);
                sensorDataService.saveMessage(messageText);
                try{
                    JSONObject dataJson=new JSONObject(messageText);
                    JSONArray data= dataJson.getJSONArray("sensors");
                    JSONObject info=data.getJSONObject(0);
                    number=info.getString("sensorNum");
                    collectorStatusService.add(number);
                    JSONArray originalData=info.getJSONArray("data");
                    for(int i=0;i<originalData.length();i++){
                        arrayList.add(originalData.get(i));
                    }
                    double meanVariance= algorithmService.meanVariance(arrayList);
                    double MaxValue= algorithmService.MaxValue(arrayList);
                    double MinValue= algorithmService.MinValue(arrayList);
                    String warnCount = redisConnector.get("sensor:{"+number+"}:warnCount");
                    String lastCommunicateTime = redisConnector.get("sensor:{"+number+"}:lastDate");
                    String collectorNum=sensorService.getCNumBySNum(number) ;
                    String s="id:1,"+"meanVariance:"+meanVariance+","+"MaxValue:"+MaxValue+"," +"MinValue:"+MinValue+"," +"warnCount:"+warnCount+"," +"collectorNum:" +"'"+collectorNum+"'"+"," +"lastCommunicateTime:"+"'"+lastCommunicateTime+"',"+"isConnected:"+"'"+"true"+"'";
                    int endIndex = messageText.indexOf("}]}");
                    String s2= messageText.substring(0,endIndex)+","+s+"}]}";
                    wsMessageDispatcher.dispatchMessage(s2);
                    redisConnector.set("sensorNum",number);
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
            redisConnector.set("sensor:{"+redisConnector.get("sensor:{"+number+"}:collector")+"}:collectorTime",lastDate);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                Date date = sdf.parse(lastDate);
                Date now=new Date();
                long dif = (now.getTime()- date.getTime())/(1000);


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

    public static void main(String[] args) {
//       NIOServer server = new NIOServer();
//       server.listen();
        String s1=" {sensors:[{sensorNum:'2100000000010000',dataType:'Route',time:'2014-09-04 15:58:18',data:[1,150,4360,225,131]   }]} ";
        String s="id:1,"+"meanVariance:"+1+","+"MaxValue:"+2+"," +"MinValue:"+3;

        int endIndex = s1.indexOf("}]}");
        System.out.println(s1.substring(0,endIndex));
        String s2= s1.substring(0,endIndex)+","+s+"}]}";
        System.out.println(s2);
    }
}
