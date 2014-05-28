package org.whut.platform.business.user.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.whut.platform.business.user.entity.*;
import org.whut.platform.business.user.service.*;
import org.whut.platform.fundamental.logger.PlatformLogger;
import org.whut.platform.fundamental.util.json.JsonMapper;
import org.whut.platform.fundamental.util.json.JsonResultUtils;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;
/**
 * Created with IntelliJ IDEA.
 * User: John
 * Date: 14-3-25
 * Time: 下午1:42
 * To change this template use File | Settings | File Templates.
 */
@Component
@Path("/authority")
public class AuthorityServiceWeb {

    private static PlatformLogger logger = PlatformLogger.getLogger(AuthorityServiceWeb.class);

    @Autowired
    AuthorityService authorityService;

    @Autowired
    PowerService  powerService;

    @Autowired
    AuthorityPowerService authorityPowerService;

    @Autowired
    UserService userService;

    @Autowired
    UserAuthorityService userAuthorityService;

    @Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/add")
    @POST
    public String add(@FormParam("name") String name,@FormParam("description") String description, @FormParam("status") String status,@FormParam("resource") String resource){
        /*if(name==null || name.trim().equals("") || description==null || description.trim().equals("") || status==null || status.trim().equals("")){
             return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "参数不能为空!");
         }*/
        long existAuthorityId;
        try{
            existAuthorityId = authorityService.getIdByName(name);

        }
        catch(Exception ex){
            existAuthorityId = 0;
        }
        if(existAuthorityId==0){
            Authority authority = new Authority();
            authority.setName(name);
            authority.setDescription(description);
            authority.setStatus(Integer.parseInt(status));
            authorityService.add(authority);
            long currentAuthorityId = authorityService.getIdByName(name);
            String[] resourceArray = resource.split(";");
            for(int i = 0;i<resourceArray.length;i++){
                long powerId = powerService.getIdByResource(resourceArray[i]);
                AuthorityPower authorityPower = new AuthorityPower();
                authorityPower.setAuthorityId(currentAuthorityId);
                authorityPower.setPowerId(powerId);
                authorityPower.setPowerResource(resourceArray[i]);
                authorityPower.setAuthorityName(name);
                authorityPowerService.add(authorityPower);
            }
            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
        }
        else{
            return JsonResultUtils.getObjectResultByStringAsDefault("fail",JsonResultUtils.Code.ERROR);
        }


    }

    @Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/update")
    @POST
    public String update(@FormParam("jsonString") String jsonString){
        SubAuthority subAuthority = JsonMapper.buildNonDefaultMapper().fromJson(jsonString,SubAuthority.class);
        long authorityId= subAuthority.getId();
        String authorityName = subAuthority.getName();
        String authorityDescription = subAuthority.getDescription();
        int  authorityStatus = subAuthority.getStatus();
        String resource = subAuthority.getResource();
        Authority authority = new Authority();
        authority.setId(authorityId);
        authority.setName(authorityName);
        authority.setDescription(authorityDescription);
        authority.setStatus(authorityStatus);
        int deleted = authorityPowerService.deleteByAuthorityName(authorityName);
        if(deleted>=0){
            int result = authorityService.update(authority);
            String[] resourceArray = resource.split(";");
            for(int i=0;i<resourceArray.length;i++){
                Long powId = powerService.getIdByResource(resourceArray[i]);
                AuthorityPower authorityPower = new AuthorityPower();
                authorityPower.setAuthorityId(authorityId);
                authorityPower.setPowerId(powId);
                authorityPower.setPowerResource(resourceArray[i]);
                authorityPower.setAuthorityName(authorityName);
                authorityPowerService.add(authorityPower);
            }
            if(result>0){
                return  JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
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
        Authority authority = JsonMapper.buildNonDefaultMapper().fromJson(jsonString,Authority.class);
        String authorityName = authority.getName();
        int numDeleted = authorityPowerService.deleteByAuthorityName(authorityName);
        int result = authorityService.delete(authority);
        List<UserAuthority> userAuthorityList = userAuthorityService.findByAuthorityName(authorityName);
        if(userAuthorityList.size()>0){
            for(UserAuthority ua:userAuthorityList){
                String userName = ua.getUserName();
                User user = userService.findByName(userName);
                String role = user.getRole();
                String[] roles = role.split(";");
                String[] newRoles = new String[roles.length-1];
                int temp=0;
                for(int i = 0;i<roles.length;i++){
                    if(!roles[i].equals(authorityName)){
                        newRoles[temp]=roles[i]+";";
                        temp++;
                    }
                }
                String roles2 = "";
                String nr;
                for(int i=0;i<newRoles.length;i++){
                    roles2+=newRoles[i];
                }
                if(roles2.equals("")){
                    nr="";
                }
                else{
                    nr = roles2.substring(0,roles2.length()-1);
                }
                user.setRole(nr);
                userService.update(user);
                userAuthorityService.delete(ua);
            }
        }
        if((result>0)&&(numDeleted>=0)){
            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
        }
        else{
            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.ERROR);
        }
    }

    @Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/list")
    @GET
    public String list(){
        List<Authority> list = authorityService.list();
        List<SubAuthority> listNew =new ArrayList<SubAuthority>();
        //List<AuthorityPower> authorityPowerList = authorityPowerService.getAuthorityPowerList();
        for(Authority a:list){
            SubAuthority subAuthority = new SubAuthority();
            String  authorityName = a.getName();
            subAuthority.setId(a.getId());
            subAuthority.setName(authorityName);
            subAuthority.setDescription(a.getDescription());
            subAuthority.setStatus(a.getStatus());
            List<String> resourceList = authorityPowerService.getResourcesByAuthorityName(authorityName);
            String resources = "";
            for(String s:resourceList){
                String s2 = s+";";
                resources+=s2;
                System.out.println(">>>>>>>>"+s);
            }
            System.out.println("<<<<<<<<"+resources);
            String r1;
            if(resources.equals("")){
                r1="";
            }
            else{
                r1 = resources.substring(0,resources.length()-1);
            }
            System.out.println(">>>>>>>>"+r1);
            // String r2 = "\'"+r1+"\'";
             /*String resource="";
             int length = authorityPowerList.size();
             for(int i = 0;i<length;i++){
                 String authorityName = authorityPowerList.get(i).getAuthorityName();
                 if(authorityName.equals(a.getName())){
                     String s = "\'"+authorityPowerList.get(i).getPowerResource()+"\'";
                     resource +=s ;
                 }
             } */
            subAuthority.setResource(r1);
            listNew.add(subAuthority);
        }
        return JsonResultUtils.getObjectResultByStringAsDefault(listNew, JsonResultUtils.Code.SUCCESS);
    }
    @Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/show")
    @GET
    public String show(){
        List<Authority> list=authorityService.list();
        return JsonResultUtils.getObjectResultByStringAsDefault(list, JsonResultUtils.Code.SUCCESS);
    }

}
