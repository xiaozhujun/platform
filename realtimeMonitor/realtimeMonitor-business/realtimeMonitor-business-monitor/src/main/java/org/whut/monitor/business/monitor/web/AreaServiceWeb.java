package org.whut.monitor.business.monitor.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.whut.monitor.business.monitor.entity.Area;
import org.whut.monitor.business.monitor.service.AreaService;
import org.whut.platform.business.user.security.UserContext;
import org.whut.platform.fundamental.util.json.JsonResultUtils;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-7-15
 * Time: 下午3:23
 * To change this template use File | Settings | File Templates.
 */
@Component
@Path("/area")
public class AreaServiceWeb {
    @Autowired
    AreaService areaService;

    @Produces(MediaType.APPLICATION_JSON +";charset=UTF-8")
    @Path("/list")
    @POST
    public String list(){
        //long appId= UserContext.currentUserAppId();
        long appId=1;
        List<Area> list=areaService.getAreaListByAppId(appId);
        return JsonResultUtils.getObjectResultByStringAsDefault(list,JsonResultUtils.Code.SUCCESS);
    }
}
