package org.whut.trackSystem.business.device.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.whut.platform.business.user.security.UserContext;
import org.whut.platform.fundamental.util.json.JsonResultUtils;
import org.whut.trackSystem.business.device.entity.Device;
import org.whut.trackSystem.business.device.service.DeviceService;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: yangyang
 * Date: 14-12-15
 * Time: 下午4:15
 * To change this template use File | Settings | File Templates.
 */
@Component
@Path("/device")
public class DeviceServiceWeb {
    @Autowired
    private DeviceService deviceService;

    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @POST
    private String add(@FormParam("jsonString")String jsonString) {
        /*待续*/
        return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
    }

    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @POST
    public String list() {
        Long appId = UserContext.currentUserAppId();
        List<Device> list = deviceService.getListByAppId(appId);
        return JsonResultUtils.getObjectResultByStringAsDefault(list, JsonResultUtils.Code.SUCCESS);
    }
}
