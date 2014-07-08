package org.whut.inspectManagement.business.task.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.whut.inspectManagement.business.task.service.InspectTaskService;
import org.whut.platform.business.user.security.UserContext;
import org.whut.platform.fundamental.util.json.JsonResultUtils;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created with IntelliJ IDEA.
 * User: xiaozhujun
 * Date: 14-6-30
 * Time: 下午4:27
 * To change this template use File | Settings | File Templates.
 */
@Component
@Path("/inspectTask")
public class InspectTaskServiceWeb {

    @Autowired
    private InspectTaskService inspectTaskService;

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/lastTaskByDeviceGroup")
    @GET
    public String getLastTaskByDeviceGroup(){
        return JsonResultUtils.getObjectResultByStringAsDefault(inspectTaskService.getLastTaskByDeviceGroup(UserContext.currentUserAppId()), JsonResultUtils.Code.SUCCESS);
    }


}
