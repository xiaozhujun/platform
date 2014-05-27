package org.whut.inspectManagement.business.inspectTable.web;

import com.sun.research.ws.wadl.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.whut.inspectManagement.business.inspectTable.entity.InspectTable;
import org.whut.inspectManagement.business.inspectTable.service.InspectTableService;
import org.whut.platform.business.user.security.UserContext;
import org.whut.platform.fundamental.util.json.JsonMapper;
import org.whut.platform.fundamental.util.json.JsonResultUtils;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import java.text.*;
import java.util.Date;
import java.util.List;
/**
 * Created with IntelliJ IDEA.
 * User: choumiaoer
 * Date: 14-5-10
 * Time: 下午2:24
 * To change this template use File | Settings | File Templates.
 */
@Component
@Path("/inspectTable")
public class InspectTableServiceWeb {
    @Autowired
    InspectTableService inspectTableService;

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/add")
    @POST
    public String add(@FormParam("inspectTableName") String name,@FormParam("description") String description){
        long appId=UserContext.currentUserAppId();
        if(name==null||name.equals("")){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"参数不能为空");
        }
        long id;
        try {
           id=inspectTableService.getIdByName(name,appId);
        }catch (Exception e){
            id=0;
        }
        if (id!=0){ return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"点检表名已存在");}
        Date date=new Date();
        InspectTable inspectTable=new InspectTable();
        inspectTable.setName(name);
        inspectTable.setCreatetime(date);
        inspectTable.setDescription(description);
        inspectTable.setAppId(appId);
        inspectTableService.add(inspectTable);
        return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
    }

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/getList")
    @POST
    public String getList(){
        long appId=UserContext.currentUserAppId();
        List<InspectTable> list=inspectTableService.getListByAppId(appId);
        return JsonResultUtils.getObjectResultByStringAsDefault(list,JsonResultUtils.Code.SUCCESS);
    }
    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/update")
    @POST
    public String update(@FormParam("jsonString") String jsonString){
        long appId=UserContext.currentUserAppId();
        InspectTable inspectTable = JsonMapper.buildNonDefaultMapper().fromJson(jsonString,InspectTable.class);
        if(inspectTable.getName()==null||inspectTable.getName().equals("")){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"参数不能为空");
        }
        Date date=new Date();
        inspectTable.setCreatetime(date);
        long id;
        try {
            id=inspectTableService.getIdByName(inspectTable.getName(),appId);
        }catch (Exception e){
            id=0;
        }
        if(id!=0){
            if(id!=inspectTable.getId()){
                return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"点检表名已存在");
            }
        }
        inspectTableService.update(inspectTable);
        return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
    }

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/delete")
    @POST
    public String delete(@FormParam("jsonString") String jsonString){
        InspectTable inspectTable = JsonMapper.buildNonDefaultMapper().fromJson(jsonString, InspectTable.class);
        inspectTableService.delete(inspectTable);
        return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
    }
}
