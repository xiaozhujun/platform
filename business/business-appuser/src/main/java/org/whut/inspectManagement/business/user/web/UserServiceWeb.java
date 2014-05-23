package org.whut.inspectManagement.business.user.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.whut.inspectManagement.business.app.service.AppService;
import org.whut.inspectManagement.business.user.entity.SubUser;
import org.whut.inspectManagement.business.user.entity.User;
import org.whut.inspectManagement.business.user.entity.UserAuthority;
import org.whut.inspectManagement.business.user.service.AuthorityService;
import org.whut.inspectManagement.business.user.service.UserAuthorityService;
import org.whut.inspectManagement.business.user.service.UserService;
import org.whut.platform.fundamental.logger.PlatformLogger;
import org.whut.platform.fundamental.util.json.JsonMapper;
import org.whut.platform.fundamental.util.json.JsonResultUtils;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: xiaozhujun
 * Date: 14-1-26
 * Time: 上午11:53
 * To change this template use File | Settings | File Templates.
 */
@Component
@Path("/appuser")
public class UserServiceWeb {

    private static PlatformLogger logger = PlatformLogger.getLogger(UserServiceWeb.class);

    final String STATUS="启用";
    @Autowired
    private UserService userService;
    @Autowired
    private AuthorityService authorityService;
    @Autowired
    private AppService appService;
    @Autowired
    private UserAuthorityService userAuthorityService;

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
        return JsonResultUtils.getObjectResultByStringAsDefault(id, JsonResultUtils.Code.SUCCESS);
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
        return JsonResultUtils.getObjectResultByStringAsDefault(user, JsonResultUtils.Code.SUCCESS);
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
        return JsonResultUtils.getObjectResultByStringAsDefault(user, JsonResultUtils.Code.SUCCESS);
    }

    @Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/add")
    @POST
    public String add(@FormParam("name") String name,@FormParam("password") String password,@FormParam("sex") String sex,@FormParam("role") String role,/*@FormParam("status") String status,*/@FormParam("appName") String appName){
        if(name==null ||name.trim().equals("")|| password==null|| password.trim().equals("") ||sex==null|| sex.trim().equals("")||role==null|| role.trim().equals("")||appName==null||appName.trim().equals("")){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "参数不能为空!");
        }

        long id;
        try{
            id = userService.getIdByName(name);
        }
        catch (Exception ex){
            id=0;
        }
        if(id==0){

                long appId=appService.getIdByName(appName);
                User user = new User();
                user.setName(name);
                user.setPassword(password);
                user.setSex(sex);
                user.setRole(role);
                user.setStatus(STATUS);
                user.setAppId(appId);
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
                    userAuthority.setAppId(appId);
                    userAuthorityService.add(userAuthority);
            }


            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
        }
        else{
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "用户名已存在!");
        }
    }

    @Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/list")
    @GET
    public String list(){

        List<User> list=userService.list();
        for( int i=0;i<list.size();i++){
           long appId=list.get(i).getAppId();
           String appName=appService.getNameById(appId);
           list.get(i).setAppName(appName);
        }

        return JsonResultUtils.getObjectResultByStringAsDefault(list, JsonResultUtils.Code.SUCCESS);
    }

    @Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/delete")
    @POST
    public String delete(@FormParam("jsonString") String jsonString){
        User user = JsonMapper.buildNonDefaultMapper().fromJson(jsonString,User.class);
        String userName = user.getName();
        //删除用户角色表数据
        int userAuthorityDeleted = userAuthorityService.deleteByUserName(userName);

        //删除用户表数据
        int userDeleted = userService.delete(user);
        if((userAuthorityDeleted>0)&&(userDeleted>=0)){
            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
        }
        else{
            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.ERROR);
        }
    }

    @Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/update")
    @POST
    public String update(@FormParam("jsonString") String jsonString){
        User user = JsonMapper.buildNonDefaultMapper().fromJson(jsonString,User.class);
        SubUser subUser = JsonMapper.buildNonDefaultMapper().fromJson(jsonString,SubUser.class);
        long appId=appService.getIdByName(user.getAppName());
        user.setAppId(appId);
        subUser.setAppId(appId);

        String userName = user.getName();
        int userAuthorityDeleted = userAuthorityService.deleteByUserName(userName);

        if(userAuthorityDeleted>=0){
            long userId = userService.getIdByName(userName);
            //更新用户角色表
            String role = user.getRole();
            String[] roleArray = role.split(";");
            int length= roleArray.length;
            for(int i=0;i<length;i++){
                UserAuthority userAuthority = new UserAuthority();
                long authorityId= authorityService.getIdByName(roleArray[i]);
                userAuthority.setAppId(appId);
                userAuthority.setUserId(userId);
                userAuthority.setAuthorityId(authorityId);
                userAuthority.setUserName(userName);
                userAuthority.setAuthorityName(roleArray[i]);
                userAuthorityService.add(userAuthority);
            }

            //更新用户表
            int  result = userService.update(user);
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
}
