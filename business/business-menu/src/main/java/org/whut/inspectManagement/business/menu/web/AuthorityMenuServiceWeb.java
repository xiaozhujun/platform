package org.whut.inspectManagement.business.menu.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.whut.inspectManagement.business.menu.entity.AuthorityMenu;
import org.whut.inspectManagement.business.menu.service.AuthorityMenuService;
import org.whut.inspectManagement.business.menu.service.MenuService;
import org.whut.platform.business.user.service.AuthorityService;
import org.whut.platform.fundamental.logger.PlatformLogger;
import org.whut.platform.fundamental.util.json.JsonResultUtils;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: LJXia
 * Date: 14-5-27
 * Time: 上午10:33
 * To change this template use File | Settings | File Templates.
 */
@Component
@Path("/authoritymenu")
public class AuthorityMenuServiceWeb {
    private static PlatformLogger logger = PlatformLogger.getLogger(AuthorityMenuServiceWeb.class);

    @Autowired
    private AuthorityMenuService authorityMenuService;
    @Autowired
    private AuthorityService authorityService;
    @Autowired
    private MenuService menuService;

    @Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/add")
    @POST
    public String add(@FormParam("MenuIds") String MenuIds,@FormParam("authorityIds") String authorityIds)
    {
        List<AuthorityMenu> list=new LinkedList<AuthorityMenu>();
        String[] MenuIdArray=MenuIds.split(";");
        String[] authorityIdArray=authorityIds.split(",");
        for(int i=0;i<authorityIdArray.length;i++){

            long authorityId=Long.parseLong(authorityIdArray[i]);
            String authorityName=authorityService.getNameById(authorityId);

            for(int j=0;j<MenuIdArray.length;j++){
                AuthorityMenu authorityMenu=new AuthorityMenu();
                long menuId=Long.parseLong(MenuIdArray[j]);
                String menuName=menuService.getNameById(menuId);
                 authorityMenu.setAuthorityId(authorityId);
                 authorityMenu.setAuthorityName(authorityName);
                 authorityMenu.setMenuId(menuId);
                 authorityMenu.setMenuName(menuName);
                 list.add(authorityMenu);
            }

        }

          if(list.size()>0){
              authorityMenuService.addList(list);
          }
          return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
    }

    @Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/list")
    @GET
    public String list(){

        List<AuthorityMenu> list=authorityMenuService.list();

        return JsonResultUtils.getObjectResultByStringAsDefault(list, JsonResultUtils.Code.SUCCESS);
    }

    @Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/delete")
    @POST
    public String delete(@FormParam("authorityIds") String authorityIds)
    {
        int  result=0;
        String idString=authorityIds;
        String[] idStringArray=idString.split(",");

        for(int i=0;i<idStringArray.length;i++) {
            long authorityId=Long.parseLong(idStringArray[i]);
            result = authorityMenuService.deleteByAuthorityId(authorityId);
         }

        if(result>=0){
            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
        }else{
            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.ERROR);
        }
    }
}
