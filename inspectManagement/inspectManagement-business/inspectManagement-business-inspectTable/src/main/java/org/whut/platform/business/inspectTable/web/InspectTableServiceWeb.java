package org.whut.platform.business.inspectTable.web;

import com.sun.research.ws.wadl.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.whut.platform.business.inspectTable.entity.InspectTable;
import org.whut.platform.business.inspectTable.service.InspectTableService;
import org.whut.platform.fundamental.util.json.JsonMapper;
import org.whut.platform.fundamental.util.json.JsonResultUtils;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
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
    public String add(@FormParam("inspectTableName") String name,@FormParam("description") String description,@FormParam("appId") long appId){

        if(name==null||appId==0||name.equals("")){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"参数不能为空");
        }
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
        List<InspectTable> list;
        list=inspectTableService.getList();
        return JsonResultUtils.getObjectResultByStringAsDefault(list,JsonResultUtils.Code.SUCCESS);
    }
    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/update")
    @POST
    public String update(@FormParam("jsonString") String jsonString){
        InspectTable inspectTable = JsonMapper.buildNonDefaultMapper().fromJson(jsonString,InspectTable.class);
        if(inspectTable.getName()==null||inspectTable.getAppId()==0||inspectTable.getName().equals("")){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"参数不能为空");
        }
        Date date=new Date();
        inspectTable.setCreatetime(date);
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
