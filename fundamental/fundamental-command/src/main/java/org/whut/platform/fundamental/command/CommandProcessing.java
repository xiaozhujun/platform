package org.whut.platform.fundamental.command;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.web.socket.WebSocketSession;
import org.whut.platform.fundamental.config.FundamentalConfigProvider;
import org.whut.platform.fundamental.logger.PlatformLogger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: tgq
 * Date: 14-11-10
 * Time: 上午11:00
 * To change this template use File | Settings | File Templates.
 */
public class CommandProcessing {
  private static final PlatformLogger logger = PlatformLogger.getLogger(CommandProcessing.class);
  public static Map<String,WebSocketSession> wsuImpMap=new HashMap<String,WebSocketSession>();
  public static Map<String,List<WebSocketSession>> wsImpMap=new HashMap<String, List<WebSocketSession>>();

  public static void subscribe(long appId,long userId,JSONArray sNum,WebSocketSession session) throws JSONException {
    for(int i=0;i<sNum.length();i++){
        List<WebSocketSession> wssList=wsImpMap.get(appId+":"+sNum.get(i).toString());
        if(wssList==null){
            wssList=new ArrayList<WebSocketSession>();
        }
        wssList.add(session);
        wsImpMap.put(appId+":"+sNum.get(i).toString(),wssList);
        wsuImpMap.put(appId+":"+userId+":"+sNum.get(i).toString(),session);
     }

  }
    public static void cancelSubscribe(String message,long appId,long userId) throws JSONException {
        JSONObject dataJson=new JSONObject(message);
        JSONArray sNum= dataJson.getJSONArray(FundamentalConfigProvider.get("message.nioServer.type"));
        for(int i=0;i<sNum.length();i++){
            WebSocketSession webSocketSession=wsuImpMap.get(appId+":"+userId+":"+sNum.get(i).toString());
            List<WebSocketSession> webSocketSessionList=wsImpMap.get(appId+":"+sNum.get(i).toString());
            webSocketSessionList.remove(webSocketSession);
            if(webSocketSessionList.isEmpty()){
                wsImpMap.remove(appId+":"+sNum.get(i).toString());
            }
            wsuImpMap.remove(appId+":"+userId+":"+sNum.get(i).toString());
        }
    }
    public static void cancelSubscribe(Map<WebSocketSession,String> map,WebSocketSession session,Map<WebSocketSession,Long> sessionAndAppIdMap) throws JSONException {
        String msg= map.get(session);
        logger.info("msg:"+msg);
        Long appId=sessionAndAppIdMap.get(session);
        JSONObject dataJson=new JSONObject(msg);
        JSONArray sNum= dataJson.getJSONArray(FundamentalConfigProvider.get("message.nioServer.type"));
        logger.info("wsImpMap前"+wsImpMap);
        for(int i=0;i<sNum.length();i++){
            List<WebSocketSession> webSocketSessionList=wsImpMap.get(appId+":"+sNum.get(i).toString());
            webSocketSessionList.remove(session);
            if(webSocketSessionList.isEmpty()){
                wsImpMap.remove(appId+":"+sNum.get(i).toString());
            }
        }
        logger.info("wsImpMap后" + wsImpMap);
    }
}
