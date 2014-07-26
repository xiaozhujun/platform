package org.whut.monitor.business.algorithm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.whut.monitor.business.communication.service.SensorDataService;
import org.whut.platform.fundamental.util.json.JsonResultUtils;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import static java.lang.Math.*;

/**
 * Created with IntelliJ IDEA.
 * User: yangyang
 * Date: 14-7-24
 * Time: 上午8:59
 * To change this template use File | Settings | File Templates.
 */
@Component
@Path("/algorithm")
public class AlgorithmServiceWeb {
    @Autowired
    private SensorDataService sensorDataService;
    @Path("/meanVarianceValue/{id}")
    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @GET
    public String meanVarianceValue(@PathParam("id")String id) {
        ArrayList sensorDataArray = sensorDataService.getCurrentSensorDataArray(id);
        if(sensorDataArray == null){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "无数据");
        }
        Double[] sensorData = new Double[sensorDataArray.size()];
        for(int i=0;i<sensorDataArray.size();i++){
            sensorData[i] = Double.parseDouble(sensorDataArray.get(i).toString());
        }
        double sum = 0;
        for (int i=0;i<sensorDataArray.size();i++) {
            sum += sensorData[i];
        }
        double average = sum/(sensorDataArray.size());
        double temp = 0;
        for (int i=0;i<sensorDataArray.size();i++) {
            temp += pow((sensorData[i]-average),2);
        }
        temp /= sensorDataArray.size();
        return JsonResultUtils.getObjectResultByStringAsDefault(sqrt(temp), JsonResultUtils.Code.SUCCESS);
    }
}
