package org.whut.inspectManagement.business.inspectTable.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.whut.inspectManagement.business.inspectTable.entity.InspectItem;

import org.whut.inspectManagement.business.inspectTable.service.InspectItemService;

import org.whut.platform.fundamental.util.json.JsonMapper;
import org.whut.platform.fundamental.util.json.JsonResultUtils;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 * Created with IntelliJ IDEA.
 * User: choumiaoer
 * Date: 14-5-11
 * Time: 下午1:20
 * To change this template use File | Settings | File Templates.
 */
@Component
@Path("/inspectItem")
public class InspectItemServiceWeb {

    @Autowired
    InspectItemService inspectItemService;

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/add")
    @POST
    public String add(@FormParam("name") String name,@FormParam("description") String description,@FormParam("inspectTableId") String inspectTableId,
    @FormParam("inspectAreaId") long inspectAreaId,@FormParam("number") String number,@FormParam("isInput") int isInput){
        if(name==null||inspectAreaId==0||inspectTableId.equals("")||number==null||name.equals("")||inspectTableId.equals("null")){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"参数不能为空!");
        }
        Date date=new Date();
        String[] inspectTableArray=inspectTableId.split(";");
        List<InspectItem> inspectItemList=new ArrayList<InspectItem>();

        for(int i=0;i<inspectTableArray.length;i++){
            InspectItem inspectItem=new InspectItem();
            inspectItem.setName(name);
            inspectItem.setDescription(description);
            inspectItem.setCreatetime(date);
            inspectItem.setInspectAreaId(inspectAreaId);
            inspectItem.setNumber(number);
            inspectItem.setInput(isInput);
        inspectItem.setInspectTableId(Integer.parseInt(inspectTableArray[i]));
        inspectItemList.add(inspectItem);
        }
        inspectItemService.addList(inspectItemList);
        return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.SUCCESS.getCode(),"操作成功");
    }
    @Produces(MediaType.APPLICATION_JSON +";charset=UTF-8")
    @Path("/list")
    @POST
    public String list(){
        List<InspectItem> list=inspectItemService.list();
        return JsonResultUtils.getObjectResultByStringAsDefault(list, JsonResultUtils.Code.SUCCESS);
    }
    @Produces(MediaType.APPLICATION_JSON +";charset=UTF-8")
    @Path("/update")
    @POST
    public String update(@FormParam("jsonString") String jsonString){
        InspectItem inspectItem = JsonMapper.buildNonDefaultMapper().fromJson(jsonString,InspectItem.class);
        if(inspectItem.getName()==null||inspectItem.getInspectAreaId()==0||inspectItem.getInspectTableId()==0||inspectItem.getNumber()==null||inspectItem.getName().equals("")){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"参数不能为空!");
        }

        Date date=new Date();
        inspectItem.setCreatetime(date);
        inspectItemService.update(inspectItem);
        return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
    }
    @Produces(MediaType.APPLICATION_JSON +";charset=UTF-8")
    @Path("/delete")
    @POST
    public String delete(@FormParam("jsonString") String jsonString){
        InspectItem inspectItem = JsonMapper.buildNonDefaultMapper().fromJson(jsonString,InspectItem.class);
        int resault=inspectItemService.delete(inspectItem);
        if(resault>=0){
        return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.SUCCESS.getCode(),"操作成功");
        }
        else
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.SUCCESS.getCode(),"操作失败");
    }
}
