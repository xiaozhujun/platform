package org.whut.monitor.business.dataTest.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.whut.monitor.business.dataTest.misc.SimulatorEnum;
import org.whut.monitor.business.dataTest.service.SensorSimulatorService;
import org.whut.platform.fundamental.logger.PlatformLogger;
import org.whut.platform.fundamental.util.json.JsonResultUtils;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created with IntelliJ IDEA.
 * User: xiaozhujun
 * Date: 14-12-12
 * Time: 上午10:43
 * To change this template use File | Settings | File Templates.
 */
@Component
@Path("/simulator")
public class SensorSimulatorServiceWeb {
    PlatformLogger logger = PlatformLogger.getLogger(SensorSimulatorServiceWeb.class);

    @Autowired
    SensorSimulatorService sensorSimulatorService;

    @Path("/startSimulator")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public String startSimulator(){
       sensorSimulatorService.doSimulator(SimulatorEnum.START);
       return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
    }

    @Path("/stopSimulator")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public String stopSimulator(){
        sensorSimulatorService.doSimulator(SimulatorEnum.STOP);
        return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
    }

    @Path("/setInterval/{interval}")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public String setInterval(@PathParam("interval") String interval){
        if(interval==null||interval.trim().equals("")){
            return JsonResultUtils.getObjectResultByStringAsDefault("参数不能为空！", JsonResultUtils.Code.ERROR);
        }
        sensorSimulatorService.setInterval(Integer.parseInt(interval));
        return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
    }

    @Path("/setPrefix/{prefix}")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public String setPrefix(@PathParam("prefix") String prefix){
        if(prefix==null||prefix.trim().equals("")){
            return JsonResultUtils.getObjectResultByStringAsDefault("参数不能为空！", JsonResultUtils.Code.ERROR);
        }
        sensorSimulatorService.setPrefix(prefix);
        return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
    }


}
