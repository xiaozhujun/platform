package org.whut.monitor.business.communication.web;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.whut.monitor.business.communication.service.SensorDataService;
import org.whut.platform.fundamental.redis.connector.RedisConnector;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@Path("/sensors")
public class SensorDataWeb {

    @Autowired
    private SensorDataService sensorDataService;
    private RedisConnector redisConnector = new RedisConnector();

    private String getJsonp(Object obj,String prefix){
        ObjectMapper mapper = new ObjectMapper();
        String result = prefix + "(";
        try {
            result += mapper.writeValueAsString(obj);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return result + ")";
    }

    @Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/sensor/data/{id}.htm")
    @GET
    public String getSensorDataArray(@Context HttpServletRequest request,@PathParam("id") String id){
        ArrayList dataArray = sensorDataService.getCurrentSensorDataArray(id);
        HashMap map = new HashMap();
        map.put("data",dataArray);
        return getJsonp(map,request.getParameter("callback"));
    }

    @Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/sensor/warnCondition/{id}.html")
    @GET
    public String getWarnCondition(@Context HttpServletRequest request,@PathParam("id")String id) {
        String curWarnValue = redisConnector.get("sensor:{"+id+"}:value");
        String warnCount = redisConnector.get("sensor:{"+id+"}:warnCount");
        String lastDate = redisConnector.get("sensor:{"+id+"}:lastDate");
        Map map = new HashMap();
        map.put("curWarnValue",curWarnValue);
        map.put("warnCount",warnCount);
        map.put("lastDate",lastDate);
        return getJsonp(map,request.getParameter("callback"));
    }
    @Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/sensor/collectorStatus/{collectorNum}.html")
    @GET
    public String collectorStatus(@Context HttpServletRequest request,@PathParam("collectorNum")String collectorNum) {
        String lastDate=redisConnector.get("sensor:{"+collectorNum+"}:collectorTime");
        System.out.print("lastDate测试结果："+lastDate);
        Map map = new HashMap();
        if (lastDate!= null) {
            map.put("lastDate", lastDate);
        }
        else {
            map.put("lastDate","暂无数据");
        }
//        System.out.println("redis"+map.get("LastData"));
        return getJsonp(map,request.getParameter("callback"));
    }

    //将数组列表转换为数据对象
    private float[] getDataArray(ArrayList dataArray){
        if(dataArray==null){
            return new float[0];
        }
        float[] result = new float[dataArray.size()];
        for(int i=0;i<dataArray.size();i++){
            result[i] = ((Double)dataArray.get(i)).floatValue();
        }
        return result;
    }

}
