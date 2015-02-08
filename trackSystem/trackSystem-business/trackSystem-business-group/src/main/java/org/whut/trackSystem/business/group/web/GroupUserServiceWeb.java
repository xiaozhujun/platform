package org.whut.trackSystem.business.group.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.whut.platform.business.user.security.UserContext;
import org.whut.platform.fundamental.logger.PlatformLogger;
import org.whut.platform.fundamental.util.json.JsonResultUtils;
import org.whut.trackSystem.business.group.service.GroupUserService;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: yangyang
 * Date: 15-2-3
 * Time: 下午4:21
 * To change this template use File | Settings | File Templates.
 */
@Component
@Path("/groupUser")
public class GroupUserServiceWeb {
    @Autowired
    private GroupUserService groupUserService;

    private static final PlatformLogger logger = PlatformLogger.getLogger(GroupUserServiceWeb.class);

    @Path("/currentGroupDeviceList")
    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @POST
    public String currentGroupList(@FormParam("userId")Long userId) {
        Long appId = UserContext.currentUserAppId();
        Long groupId = groupUserService.getGroupIdByUserId(userId,appId);
        List<Map<String,String>> list = groupUserService.findByCondition(groupId,appId);
        logger.info("deviceNumberList: " + list);
        return JsonResultUtils.getObjectResultByStringAsDefault(list, JsonResultUtils.Code.SUCCESS);
    }

    @Path("/getUserByGroupId")
    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @POST
    public String getUserByGroupId(@FormParam("groupId")Long groupId) {
        Long appId = UserContext.currentUserAppId();
        List<Map<String,String>> list = groupUserService.getUserByGroupId(groupId,appId);
        return JsonResultUtils.getObjectResultByStringAsDefault(list, JsonResultUtils.Code.SUCCESS);
    }

    @Path("/getListByCondition")
    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @POST
    public String getListByCondition(@FormParam("groupId")Long groupId,@FormParam("userId")Long userId) {
        Long appId = UserContext.currentUserAppId();
        List<Map<String,String>> list = groupUserService.getListByCondition(groupId,userId,appId);
        return JsonResultUtils.getObjectResultByStringAsDefault(list, JsonResultUtils.Code.SUCCESS);
    }
}
