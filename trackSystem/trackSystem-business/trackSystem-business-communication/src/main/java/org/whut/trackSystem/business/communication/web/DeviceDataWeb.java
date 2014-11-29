package org.whut.trackSystem.business.communication.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Component;
import org.whut.platform.fundamental.util.json.JsonResultUtils;
import org.whut.trackSystem.business.communication.service.DeviceDataService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: yangyang
 * Date: 14-11-28
 * Time: 下午3:42
 * To change this template use File | Settings | File Templates.
 */
@Component
@Path("/deviceData")
public class DeviceDataWeb {
    @Autowired
    private DeviceDataService deviceDataService;

    @Path("/getDeviceData/{id}.html")
    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @GET
    public String getDeviceData(@Context HttpRequest request,@PathParam("id")String id) {
        Map<String,String> map = deviceDataService.getCurDeviceDataMap(id);
        if (map == null) {
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"没有获取到数据！");
        }
        return JsonResultUtils.getObjectResultByStringAsDefault(map, JsonResultUtils.Code.SUCCESS);
    }
}
