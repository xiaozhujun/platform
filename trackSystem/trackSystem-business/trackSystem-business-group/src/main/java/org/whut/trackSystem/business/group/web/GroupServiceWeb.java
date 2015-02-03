package org.whut.trackSystem.business.group.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.whut.platform.business.user.security.UserContext;
import org.whut.platform.fundamental.logger.PlatformLogger;
import org.whut.platform.fundamental.util.json.JsonMapper;
import org.whut.platform.fundamental.util.json.JsonResultUtils;
import org.whut.trackSystem.business.group.entity.Group;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: yangyang
 * Date: 15-1-31
 * Time: 下午4:17
 * To change this template use File | Settings | File Templates.
 */
@Component
@Path("/group")
public class GroupServiceWeb {
    private static final PlatformLogger logger = PlatformLogger.getLogger(GroupServiceWeb.class);
    @Autowired
    private org.whut.trackSystem.business.group.service.GroupService groupService;

    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @POST
    public String add(@FormParam("jsonString")String jsonString) {
        logger.info("add!!!");
        if (jsonString == null || jsonString.trim().equals("")) {
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"参数不能为空!");
        } else {
            Group group = JsonMapper.buildNonDefaultMapper().fromJson(jsonString,Group.class);
            if (group.getName() == null || group.getName().trim().equals("")
                    || group.getNumber() == null || group.getNumber().trim().equals("")) {
                return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"参数不能为空!");
            }
            Long appId = UserContext.currentUserAppId();
            Long id;
            try {
                id = groupService.getIdByNumber(group.getNumber(),appId);
            } catch (Exception e) {
                logger.error(e.getMessage());
                id = null;
            }
            if (id == null) {
                group.setAppId(appId);
                groupService.add(group);
                return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
            } else {
                return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"设备组已存在!");
            }
        }
    }

    @Path("/update")
    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @POST
    public String update(@FormParam("jsonString")String jsonString) {
        logger.info("update");
        if (jsonString == null || jsonString.trim().equals("")) {
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"参数不能为空!");
        } else {
            Group group = JsonMapper.buildNonDefaultMapper().fromJson(jsonString,Group.class);
            if (group == null || group.getId() == null) {
                return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"参数不能为空!");
            }
            Long appId = UserContext.currentUserAppId();
            Long id;
            try {
                id = groupService.getIdByNumber(group.getNumber(),appId);
            } catch (Exception e) {
                logger.info(e.getMessage());
                id = null;
            }
            if (id != null && id != group.getId()) {
                return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"设备组已存在!");
            }
            group.setAppId(appId);
            groupService.update(group);
            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
        }
    }

    @Path("/delete")
    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @POST
    public String delete(@FormParam("jsonString")String jsonString) {
        logger.info("delete");
        Group group = JsonMapper.buildNonDefaultMapper().fromJson(jsonString,Group.class);
        Integer code = groupService.delete(group);
        if(code > 0){
            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
        }
        else {
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"删除失败!");
        }
    }

    @Path("list")
    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @POST
    public String list() {
        logger.info("list");
        Long appId = UserContext.currentUserAppId();
        List<Group> list = groupService.getListByAppId(appId);
        return JsonResultUtils.getObjectResultByStringAsDefault(list, JsonResultUtils.Code.SUCCESS);
    }
}
