package org.whut.platform.business.user.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.whut.platform.business.user.entity.User;
import org.whut.platform.business.user.service.UserService;
import org.whut.platform.fundamental.logger.PlatformLogger;
import org.whut.platform.fundamental.util.json.JsonMapper;
import org.whut.platform.fundamental.util.json.JsonResultUtils;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: xiaozhujun
 * Date: 14-1-26
 * Time: 上午11:53
 * To change this template use File | Settings | File Templates.
 */
@Component
@Path("/user")
public class UserServiceWeb {

    private static PlatformLogger logger = PlatformLogger.getLogger(UserServiceWeb.class);

    @Autowired
    private UserService userService;

    @Path("add")
    @POST
    public Map<String,Object> add(@FormParam("jsonString") String jsonString){
        User user = JsonMapper.buildNormalMapper().fromJson(
                jsonString, User.class);
        if (user == null) {
            return JsonResultUtils
                    .getCodeAndMesMapAsDefault(JsonResultUtils.Code.ERROR);
        }
        try {
            userService.add(user);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            logger.error(e.getMessage());
            return JsonResultUtils
                    .getCodeAndMesMapAsDefault(JsonResultUtils.Code.ERROR);
        }
        // 新增操作时，返回操作状态和状态码给客户端，数据区是为空的
        return JsonResultUtils
                .getCodeAndMesMapAsDefault(JsonResultUtils.Code.SUCCESS);
    }

    @Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("findByName/{name}")
    @GET
    public String findByName(@PathParam("name") String name){
        if (name == null) {
            return JsonResultUtils
                    .getCodeAndMesByStringAsDefault(JsonResultUtils.Code.ERROR);
        }
       User user;
        try {
            user = userService.findByName(name);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            logger.error(e.getMessage());
            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.ERROR);
        }
        // 新增操作时，返回操作状态和状态码给客户端，数据区是为空的
        return JsonResultUtils.getObjectResultByStringAsDefault(user,JsonResultUtils.Code.SUCCESS);
    }
}
