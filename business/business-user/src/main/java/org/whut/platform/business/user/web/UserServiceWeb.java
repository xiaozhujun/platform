package org.whut.platform.business.user.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.whut.platform.business.app.service.AppService;
import org.whut.platform.business.user.entity.SubUser;
import org.whut.platform.business.user.entity.User;
import org.whut.platform.business.user.entity.UserAuthority;
import org.whut.platform.business.user.security.UserContext;
import org.whut.platform.business.user.service.AuthorityService;
import org.whut.platform.business.user.service.UserAuthorityService;
import org.whut.platform.business.user.service.UserService;
import org.whut.platform.fundamental.config.FundamentalConfigProvider;
import org.whut.platform.fundamental.logger.PlatformLogger;
import org.whut.platform.fundamental.util.json.JsonMapper;
import org.whut.platform.fundamental.util.json.JsonResultUtils;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.io.File;
import java.io.IOException;
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
    @Path("/currentUser")
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

    @Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/currentUserName")
    @GET
    public String  currentUserName(){
        String userName = UserContext.currentUserName();
        return JsonResultUtils.getObjectResultByStringAsDefault(userName,JsonResultUtils.Code.SUCCESS);
    }

    @Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/currentUserId")
    @GET
    public String  currentUserId(){
        long userId = UserContext.currentUserId();
        return JsonResultUtils.getObjectResultByStringAsDefault(userId,JsonResultUtils.Code.SUCCESS);
    }

    @Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/currentUserAppId")
    @GET
    public String  currentUserAppId(){
        long userAppId = UserContext.currentUserAppId();
        return JsonResultUtils.getObjectResultByStringAsDefault(userAppId,JsonResultUtils.Code.SUCCESS);
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

    //上传用户图片
    @Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/selfUploadImage")
    @POST
    public String selfUploadImage(@Context HttpServletRequest request){
        if(request==null){
            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.ERROR);
        }
        MultipartResolver resolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        MultipartHttpServletRequest multipartRequest = resolver.resolveMultipart(request);
        MultipartFile file = multipartRequest.getFile("filename");
        String filename = file.getOriginalFilename();
        String[] temp = filename.split("\\.");
        String suffix = temp[temp.length-1];

        //获得用户图片路径
        String userImgRootPath =  FundamentalConfigProvider.get("user.img.root.path") ;
        String userImgRelativePath =  FundamentalConfigProvider.get("user.img.relative.path") ;
        String userName = UserContext.currentUserName();
        long appId = UserContext.currentUserAppId();
        String userImagePath =  userImgRootPath + userImgRelativePath+"/"+appId+"/"+userName+"."+suffix;
        String userImageWebPath = userImgRelativePath+"/"+appId+"/"+userName+"."+suffix;

        User currentUser = userService.getById(UserContext.currentUserId());

        //如果文件存在则删除
        File userImageFile = new File(userImagePath);
        String oldImagePath = currentUser.getImage();
        if(oldImagePath!=null){
            File oldImage = new File(userImgRootPath+oldImagePath);
            if(oldImage.exists()){
                oldImage.delete();
            }
        }
        if(userImageFile.exists()){
            userImageFile.delete();
        }else{
            File imageDir = new File(userImgRootPath+"/"+userImgRelativePath+"/"+appId);
            if(!imageDir.exists()){
                imageDir.mkdirs();
            }
        }

        //写用户图片文件到指定路径
        try {
            file.transferTo(userImageFile);
            User user = new User();
            user.setId(UserContext.currentUserId());
            user.setImage(userImageWebPath);
            userService.updateUserImage(user);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        // 新增操作时，返回操作状态和状态码给客户端，数据区是为空的
        return JsonResultUtils.getObjectResultByStringAsDefault(userImageWebPath,JsonResultUtils.Code.SUCCESS);
    }

    //上传用户图片
    @Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/uploadImage")
    @POST
    public String uploadImage(@Context HttpServletRequest request){
        if(request==null){
            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.ERROR);
        }
        MultipartResolver resolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        MultipartHttpServletRequest multipartRequest = resolver.resolveMultipart(request);
        MultipartFile file = multipartRequest.getFile("filename");
        String filename = file.getOriginalFilename();
        String[] temp = filename.split("\\.");
        String suffix = temp[temp.length-1];

        //获得用户图片路径
        String userImgRootPath =  FundamentalConfigProvider.get("user.img.root.path") ;
        String userImgRelativePath =  FundamentalConfigProvider.get("user.img.relative.path") ;
        String userId = multipartRequest.getParameter("userId");
        if(userId==null||userId.equals("")){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"用户编号不能为空！");
        }

        User user = userService.getById(Long.parseLong(userId));
        String userName = user.getName();
        long appId = UserContext.currentUserAppId();
        String userImagePath =  userImgRootPath + userImgRelativePath+"/"+appId+"/"+userName+"."+suffix;
        String userImageWebPath = userImgRelativePath+"/"+appId+"/"+userName+"."+suffix;

        User currentUser = userService.getById(UserContext.currentUserId());

        //如果文件存在则删除
        File userImageFile = new File(userImagePath);
        String oldImagePath = user.getImage();
        if(oldImagePath!=null){
            File oldImage = new File(userImgRootPath+oldImagePath);
            if(oldImage.exists()){
                oldImage.delete();
            }
        }
        if(userImageFile.exists()){
            userImageFile.delete();
        }else{
            File imageDir = new File(userImgRootPath+"/"+userImgRelativePath+"/"+appId);
            if(!imageDir.exists()){
                imageDir.mkdirs();
            }
        }

        //写用户图片文件到指定路径
        try {
            file.transferTo(userImageFile);
            User user1 = new User();
            user1.setId(user.getId());
            user1.setImage(userImageWebPath);
            userService.updateUserImage(user1);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        // 新增操作时，返回操作状态和状态码给客户端，数据区是为空的
        return JsonResultUtils.getObjectResultByStringAsDefault(userImageWebPath,JsonResultUtils.Code.SUCCESS);
    }


    @Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/keepAlive")
    @POST
    public String keepAlive(){
        return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
    }
}
