package org.whut.monitor.business.monitor.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.whut.monitor.business.monitor.entity.Group;
import org.whut.monitor.business.monitor.service.GroupService;
import org.whut.platform.business.user.security.UserContext;
import org.whut.platform.fundamental.util.json.JsonMapper;
import org.whut.platform.fundamental.util.json.JsonResultUtils;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: yangyang
 * Date: 14-7-15
 * Time: 下午3:08
 * To change this template use File | Settings | File Templates.
 */
@Component
@Path("/group")
public class GroupServiceWeb {
    @Autowired
    private GroupService groupService;

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/add")
    @POST
    public String add(@FormParam("name")String name,@FormParam("description")String description) {
        long appId = UserContext.currentUserAppId();
        Date date = new Date();
        if(name.trim().equals("")) {
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "参数不能为空");
        }

        long id;
        try {
            id = groupService.getIdByNameAndAppId(name,appId);
        } catch (Exception e) {
            id = 0;
        }

        if (id != 0) {
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"组已存在");
        }
        Group group = new Group();
        group.setAppId(appId);
        group.setName(name);
        group.setDescription(description);
        group.setCreatetime(date);
        groupService.add(group);
        return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
    }

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/getList")
    @POST
    public String getList() {
        long appId = UserContext.currentUserAppId();
        List<Group> groupList = groupService.getListByAppId(appId);
        return JsonResultUtils.getObjectResultByStringAsDefault(groupList, JsonResultUtils.Code.SUCCESS);
    }

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/update")
    @POST
    public String update(@FormParam("jsonString")String jsonString) {
        long appId = UserContext.currentUserAppId();
        Group group = JsonMapper.buildNonDefaultMapper().fromJson(jsonString,Group.class);
        String name = group.getName();
        if(name.trim().equals("")) {
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"参数不能为空");
        }

        long id;
        try {
            id = groupService.getIdByNameAndAppId(name,appId);
        } catch (Exception e) {
            id = 0;
        }

        if (id != 0) {
            if(id != group.getId())
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"组已存在");
        }

        groupService.update(group);
        return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
    }

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/delete")
    @POST
      public String delete(@FormParam("jsonString")String jsonString) {
        Group group = JsonMapper.buildNonDefaultMapper().fromJson(jsonString,Group.class);
        groupService.delete(group);
        return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
    }

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/getIdByGroupName")
    @POST
    public String getIdByNameAndAppId(@FormParam("groupName")String groupName) {
        long appId = UserContext.currentUserAppId();
        long groupId = groupService.getIdByNameAndAppId(groupName,appId);
        return JsonResultUtils.getObjectResultByStringAsDefault(groupId,JsonResultUtils.Code.SUCCESS);
    }
}
