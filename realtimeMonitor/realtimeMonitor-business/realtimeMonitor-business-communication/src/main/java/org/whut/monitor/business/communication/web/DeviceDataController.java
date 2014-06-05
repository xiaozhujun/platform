package org.whut.monitor.business.communication.web;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.whut.monitor.business.communication.service.SensorDataService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

@Controller
@RequestMapping(value = "/sensors", method = RequestMethod.GET)
public class DeviceDataController {
    @Autowired
    private SensorDataService sensorDataService;

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

    @RequestMapping(value = "/sensor/data/{id}.htm")
    @ResponseBody
    public String getSensorDataArray(HttpServletRequest request,HttpServletResponse response,@PathVariable("id") String id){
        ArrayList dataArray = sensorDataService.getCurrentSensorDataArray(id);
        HashMap map = new HashMap();
        map.put("data",dataArray);
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
