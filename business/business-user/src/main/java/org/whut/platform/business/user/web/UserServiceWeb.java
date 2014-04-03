package org.whut.platform.business.user.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.whut.platform.business.user.entity.User;
import org.whut.platform.business.user.service.UserService;
import org.whut.platform.fundamental.logger.PlatformLogger;
import org.whut.platform.fundamental.util.json.JsonMapper;
import org.whut.platform.fundamental.util.json.JsonResultUtils;
import org.whut.platform.business.user.entity.Authority;
import org.whut.platform.business.user.service.AuthorityService;
import org.whut.platform.business.user.entity.UserAuthority;
import org.whut.platform.business.user.service.UserAuthorityService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Map;
import java.util.List;

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
    @Autowired
    private UserAuthorityService userAuthorityService;
    @Autowired
    private AuthorityService authorityService;

<<<<<<< HEAD
=======
    @Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
>>>>>>> 23dd51744e660700d6196a2d52cb2394d49b9f1c
    @Path("/add")
    @POST
    public String add(@FormParam("name") String name,@FormParam("password") String password,@FormParam("sex") String sex,@FormParam("role") String role){
       if(name==null || password=="" || password.trim().equals("") || sex.trim().equals("") || role.trim().equals("")){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"参数不能为空!");
        }
        long id;
        try{
            id = userService.getIdByName(name);
        }
        catch (Exception ex){
            id=0;
        }
        if(id==0){
            User user = new User();
            user.setName(name);
            user.setPassword(password);
            user.setSex(sex);
            user.setRole(role);
            userService.add(user);
            long userId =  userService.getIdByName(name);
            String[] roleArray = role.split(";");
            for(int i = 0;i<roleArray.length; i++){
                long authorityId = authorityService.getIdByName(roleArray[i]);
                UserAuthority userAuthority = new UserAuthority();
                userAuthority.setUserId(userId);
                userAuthority.setAuthorityId(authorityId);
                userAuthority.setUserName(name);
                userAuthority.setAuthorityName(roleArray[i]);
                userAuthorityService.add(userAuthority);
            }
            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
        }
        else{
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"用户名已存在!");
        }
    }
   /* public Map<String,Object> add(@FormParam("jsonString") String jsonString){
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
            */
    @Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/list")
    @GET
    public String list(){
        List<User> list = userService.list();
        return JsonResultUtils.getObjectResultByStringAsDefault(list,JsonResultUtils.Code.SUCCESS);
    }

    @Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/update")
    @POST
    public String update(@FormParam("jsonString") String jsonString){
         User user = JsonMapper.buildNonDefaultMapper().fromJson(jsonString,User.class);
         String userName = user.getName();
         int deleted = userAuthorityService.deleteByUserName(userName);
         if(deleted>0){
            int  result = userService.update(user);
            long userId = userService.getIdByName(userName);
            String role = user.getRole();
            String[] roleArray = role.split(";");
            int total = 0;
            int length= roleArray.length;
            for(int i=0;i<length;i++){
                UserAuthority userAuthority = new UserAuthority();
                long authorityId= authorityService.getIdByName(roleArray[i]);
                userAuthority.setUserId(userId);
                userAuthority.setAuthorityId(authorityId);
                userAuthority.setUserName(userName);
                userAuthority.setAuthorityName(roleArray[i]);
                userAuthorityService.add(userAuthority);
            }
            if(result>0){
             return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
            }
            else{
             return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.ERROR);
            }
         }
         else{
             return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.ERROR);
         }
    }

    @Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/delete")
    @POST
    public String delete(@FormParam("jsonString") String jsonString){
        User user = JsonMapper.buildNonDefaultMapper().fromJson(jsonString,User.class);
        String userName = user.getName();
        int numDeleted = userAuthorityService.deleteByUserName(userName);
        int result = userService.delete(user);
        if((result>0)&&(numDeleted>=0)){
            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
        }
        else{
            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.ERROR);
        }
    }
    @Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/getIdByName")
    @POST
    public String getIdByName(@FormParam("name") String name){
        if (name == null) {
            return JsonResultUtils
                    .getCodeAndMesByStringAsDefault(JsonResultUtils.Code.ERROR);
        }
        long id;
        try {
            id  = userService.getIdByName(name);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            logger.error(e.getMessage());
            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.ERROR);
        }
        // 新增操作时，返回操作状态和状态码给客户端，数据区是为空的
        return JsonResultUtils.getObjectResultByStringAsDefault(id,JsonResultUtils.Code.SUCCESS);
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
    @Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("currentUser")
    @GET
    public String  currentUser(){
        String username = null;
        Object credential = SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        if(credential instanceof UserDetails){
            UserDetails userDetails = (UserDetails) credential;
            username = userDetails.getUsername();
        }else{
            username = (String)credential;
        }
        logger.info("current user is "+username);
        User user;
        try {
            user = userService.findByName(username);
            user.setPassword("");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.ERROR);
        }
        return JsonResultUtils.getObjectResultByStringAsDefault(user,JsonResultUtils.Code.SUCCESS);
    }
}
