package org.whut.platform.business.map.web;
import org.springframework.beans.factory.annotation.Autowired;
import org.whut.platform.business.map.entity.Device;
import org.whut.platform.business.map.service.CompanyService;
import org.whut.platform.business.map.service.DeviceService;
import org.whut.platform.fundamental.logger.PlatformLogger;
import org.whut.platform.fundamental.util.json.JsonResultUtils;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: zhuzhenhua
 * Date: 14-3-24
 * Time: 下午3:59
 * To change this template use File | Settings | File Templates.
 */
@Component
@Path("/device")
public class DeviceServiceWeb {
    private static PlatformLogger logger = PlatformLogger.getLogger(DeviceServiceWeb.class);

    @Autowired
    private DeviceService deviceService;
    @Autowired
    private CompanyService companyService;
    @Path("/showInfo")
    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @POST
    public String showInfo(@FormParam("name") String name){
        if(name==null){
            return JsonResultUtils
                    .getCodeAndMesByStringAsDefault(JsonResultUtils.Code.ERROR);
        }
           Long id=companyService.findIdByName(name);
           List<Device> list=deviceService.findDeviceInfoById(id);
           return  JsonResultUtils.getObjectStrResultByStringAsDefault(list,200,name);
    }


}
