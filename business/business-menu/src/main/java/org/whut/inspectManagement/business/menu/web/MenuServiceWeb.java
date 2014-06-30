package org.whut.inspectManagement.business.menu.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.whut.inspectManagement.business.menu.entity.Menu;
import org.whut.inspectManagement.business.menu.service.MenuService;
import org.whut.platform.fundamental.logger.PlatformLogger;
import org.whut.platform.fundamental.util.json.JsonResultUtils;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sunhui
 * Date: 14-5-23
 * Time: 上午1:51
 * To change this template use File | Settings | File Templates.
 */
@Component
@Path("/menu")
public class MenuServiceWeb {
    private static PlatformLogger logger = PlatformLogger.getLogger(MenuServiceWeb.class);
    private final long PARENTLEVEL=1;
    private final String PARENTNAME="菜单";
    private final long PID=0;

    @Autowired
    private MenuService menuService;
    @Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/addParentMenu")
    @POST
    public String add(@FormParam("name") String name,@FormParam("url") String url)
    {
        if(name==null ||name.trim().equals("")){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "参数不能为空!");
        }
        Menu menu=new Menu();
        menu.setName(name);
        menu.setLevel(PARENTLEVEL);
        menu.setUrl(url);
        menu.setParentname(PARENTNAME);
        menu.setPid(PID);
        menuService.add(menu);
        return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
    }

    @Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/addSonMenu")
    @POST
    public String addSonMenu(@FormParam("name") String name,@FormParam("url") String url,@FormParam("pid") String pid,@FormParam("level") String level,@FormParam("parentname") String parentname)
    {
        if(name==null ||name.trim().equals("")){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "参数不能为空!");
        }
        Menu menu=new Menu();
        menu.setName(name);
        menu.setLevel(Long.parseLong(level));
        menu.setUrl(url);
        menu.setParentname(parentname);
        menu.setPid(Long.parseLong(pid));
        menuService.add(menu);
        return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
    }

    @Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/delete")
    @POST
    public String delete(@FormParam("Ids") String Ids)
    {
        Menu menu;
        int count=0;
        int result=0;
        String idString=Ids;
        String[] idStringArray=idString.split(";");

        for(int i=0;i<idStringArray.length;i++) {
            long id=Long.parseLong(idStringArray[i]);
            menu = menuService.get(id);
            result = menuService.delete(menu);
            count+=result;
        }

        if(count==idStringArray.length){
            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
        }else{
            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.ERROR);
        }
    }
    @Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/update")
    @POST
    public String update(@FormParam("name") String name,@FormParam("url") String url,@FormParam("id") String id){
        long thisId=Long.parseLong(id);
        Menu menu=menuService.get(thisId);
        menu.setName(name);
        menu.setUrl(url);
        int result = menuService.update(menu);
        if(result>0){
            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
        }else{
            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.ERROR);
        }
    }
    @Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/fsupdate")
    @POST
    public String fsupdate(@FormParam("name") String name,@FormParam("url") String url,@FormParam("id") String id){
        long thisId=Long.parseLong(id);
        Menu menu=menuService.get(thisId);
        menu.setName(name);
        menu.setUrl(url);
        int result = menuService.fsupdate(menu);
        if(result>0){
            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
        }else{
            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.ERROR);
        }
    }


    @Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
     @Path("/list")
     @GET
     public String list(){

        List<Menu> list=menuService.list();

        return JsonResultUtils.getObjectResultByStringAsDefault(list,JsonResultUtils.Code.SUCCESS);
    }
    @Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/getParentInfoById")
    @POST
    public String  getParentInfoById(@FormParam("Sid") String Sid){
        long id= Long.parseLong(Sid);
        Menu menu=menuService.get(id);

        return JsonResultUtils.getObjectResultByStringAsDefault(menu,JsonResultUtils.Code.SUCCESS);
    }
    @Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/getMenuByUserId")
    @POST
    public String getMenuByUserId(@FormParam("userid")  long userid)
    {
        List<Menu> list=menuService.getMenuByUserId(userid) ;
        return JsonResultUtils.getObjectResultByStringAsDefault(list,JsonResultUtils.Code.SUCCESS);
    }

}
