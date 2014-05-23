package org.whut.inspectManagement.business.menu.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.whut.inspectManagement.business.menu.entity.Menu;
import org.whut.inspectManagement.business.menu.service.MenuService;
import org.whut.platform.fundamental.logger.PlatformLogger;
import org.whut.platform.fundamental.util.json.JsonMapper;
import org.whut.platform.fundamental.util.json.JsonResultUtils;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Date;

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

    @Autowired
    private MenuService menuService;
    @Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/add")
    @POST
    public String add(@FormParam("name") String name,@FormParam("level") long level,@FormParam("url") String url,@FormParam("parentname") String parentname)
    {
        if(name==null ||name.trim().equals("")){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "参数不能为空!");
        }
        Menu menu=new Menu();
        menu.setName(name);
        menu.setLevel(level);
        menu.setUrl(url);
        menu.setParentname(parentname);
        menuService.add(menu);
        return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
    }
    @Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/delete")
    @POST
    public String delete(@FormParam("jsonString") String jsonString)
    {
        Menu menu = JsonMapper.buildNonDefaultMapper().fromJson(jsonString,Menu.class);
        int result = menuService.delete(menu);
        if(result>0){
            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
        }else{
            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.ERROR);
        }
    }
    @Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/update")
    @POST
    public String update(@FormParam("jsonString") String jsonString){
        Menu menu = JsonMapper.buildNonDefaultMapper().fromJson(jsonString,Menu.class);

        int result = menuService.update(menu);
        if(result>0){
            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
        }else{
            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.ERROR);
        }
    }

}
