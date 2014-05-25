package org.whut.platform.business.datarule.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.whut.platform.business.datarule.entity.UserDataRole;
import org.whut.platform.business.datarule.service.DataRoleService;
import org.whut.platform.business.datarule.service.UserDataRoleService;
import org.whut.platform.business.user.entity.SubUser;
import org.whut.platform.business.user.entity.User;
import org.whut.platform.business.user.entity.UserAuthority;
import org.whut.platform.business.user.service.AuthorityService;
import org.whut.platform.business.user.service.UserAuthorityService;
import org.whut.platform.business.user.service.UserService;
import org.whut.platform.fundamental.logger.PlatformLogger;
import org.whut.platform.fundamental.util.json.JsonMapper;
import org.whut.platform.fundamental.util.json.JsonResultUtils;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ThinkPad
 * Date: 14-4-23
 * Time: 下午4:35
 * To change this template use File | Settings | File Templates.
 */
@Component
@Path("/userDataRole")
public class UserDataRoleServiceWeb {

    private static PlatformLogger logger = PlatformLogger.getLogger(UserDataRoleServiceWeb.class);

    @Autowired
    private UserService userService;
    @Autowired
    private UserAuthorityService userAuthorityService;
    @Autowired
    private AuthorityService authorityService;
    @Autowired
    private DataRoleService dataRoleService;
    @Autowired
    private UserDataRoleService userDataRoleService;
    @Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/add")
    @POST
    public String add(@FormParam("name") String name,@FormParam("password") String password,@FormParam("sex") String sex,@FormParam("role") String role,@FormParam("dataRole") String dataRole){
        if(name==null ||name.trim().equals("")|| password==null|| password.trim().equals("") ||sex==null|| sex.trim().equals("")||role==null|| role.trim().equals("")||dataRole==null||dataRole.trim().equals("")){
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
            User user = new User();
            user.setName(name);
            user.setPassword(password);
            user.setSex(sex);
            user.setRole(role);
            user.setStatus("启用");
            user.setAppId(0);
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
            String[] dataRoleArray = dataRole.split(";");
            for(int i=0;i<dataRoleArray.length;i++){
                long dRoleId = dataRoleService.getIdByName(dataRoleArray[i]);
                UserDataRole userDataRole = new UserDataRole();
                userDataRole.setUserId(userId);
                userDataRole.setDRoleId(dRoleId);
                userDataRole.setUserName(name);
                userDataRole.setDRoleName(dataRoleArray[i]);
                userDataRoleService.add(userDataRole);
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
        List<SubUser> subUserList = new ArrayList<SubUser>();
        for(User u:list){
           SubUser subUser = new SubUser();
           String userName = u.getName();
           subUser.setId(u.getId());
           subUser.setName(userName);
           //subUser.setPassword(u.getPassword());
           subUser.setSex(u.getSex());
           subUser.setRole(u.getRole());
           List<String> dataRoleList = userDataRoleService.findDataRoleByUserName(userName);
           String dataRoles="";
           for(String s:dataRoleList){
              dataRoles+=s;
              dataRoles+=";";
           }
           String drs;
           if(dataRoles.equals("")){
              drs="";
           }
           else{
               drs=dataRoles.substring(0,dataRoles.length()-1);
           }
           subUser.setDataRole(drs);
           subUserList.add(subUser);
        }
        return JsonResultUtils.getObjectResultByStringAsDefault(subUserList,JsonResultUtils.Code.SUCCESS);
    }

    @Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/update")
    @POST
    public String update(@FormParam("jsonString") String jsonString){
        User user = JsonMapper.buildNonDefaultMapper().fromJson(jsonString,User.class);
        SubUser subUser = JsonMapper.buildNonDefaultMapper().fromJson(jsonString,SubUser.class);
        String userName = user.getName();
        int userAuthorityDeleted = userAuthorityService.deleteByUserName(userName);
        int userDataRoleDeleted = userDataRoleService.deleteByUserName(userName);
        if(userAuthorityDeleted>=0&&userDataRoleDeleted>=0){
            long userId = userService.getIdByName(userName);
            //更新用户角色表
            String role = user.getRole();
            String[] roleArray = role.split(";");
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
            //更新用户数据权限表
            String dataRole = subUser.getDataRole();
            String[] dataRoleArray = dataRole.split(";");
            int dLength  = dataRoleArray.length;
            for(int j=0;j<dLength;j++){
               UserDataRole userDataRole = new UserDataRole();
               Long dataRoleId = dataRoleService.getIdByName(dataRoleArray[j]);
               userDataRole.setUserId(userId);
               userDataRole.setUserName(userName);
               userDataRole.setDRoleId(dataRoleId);
               userDataRole.setDRoleName(dataRoleArray[j]);
               userDataRoleService.add(userDataRole);
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

    @Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/delete")
    @POST
    public String delete(@FormParam("jsonString") String jsonString){
        User user = JsonMapper.buildNonDefaultMapper().fromJson(jsonString,User.class);
        String userName = user.getName();
        //删除用户角色表数据
        int userAuthorityDeleted = userAuthorityService.deleteByUserName(userName);
        //删除用户数据角色表数据
        int userDataRoleDeleted = userDataRoleService.deleteByUserName(userName);
        //删除用户表数据
        int userDeleted = userService.delete(user);
        if((userAuthorityDeleted>0)&&(userDataRoleDeleted>=0)&&(userDeleted>=0)){
            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
        }
        else{
            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.ERROR);
        }
    }
}
