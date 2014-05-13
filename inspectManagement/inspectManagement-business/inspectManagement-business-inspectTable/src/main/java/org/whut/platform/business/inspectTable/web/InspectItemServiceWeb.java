package org.whut.platform.business.inspectTable.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.whut.platform.business.inspectTable.entity.InspectItem;
import org.whut.platform.business.inspectTable.service.InspectItemService;
import org.whut.platform.fundamental.util.json.JsonResultUtils;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
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
    public String add(@FormParam("name") String name,@FormParam("description") String description,@FormParam("createtime")Date createtime,@FormParam("inspectTableId") long inspectTableId,
    @FormParam("inspectAreaId") long inspectAreaId,@FormParam("number") String number,@FormParam("isInput") int isInput){
        if(name==null||createtime==null||inspectAreaId==0||inspectTableId==0||number==null||name.equals("")||createtime.equals("")){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"参数不能为空!");
        }
        InspectItem inspectItem=new InspectItem();
        inspectItem.setName(name);
        inspectItem.setDescription(description);
        inspectItem.setCreatetime(createtime);
        inspectItem.setInspectAreaId(inspectAreaId);
        inspectItem.setInspectTableId(inspectTableId);
        inspectItem.setInput(isInput);
        inspectItemService.add(inspectItem);
        return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.SUCCESS.getCode(),"操作成功");
    }
    @Produces(MediaType.APPLICATION_JSON +";charset=UTF-8")
    @Path("/list")
    @POST
    public String list(){
        List<InspectItem> list=inspectItemService.list();
        return JsonResultUtils.getObjectResultByStringAsDefault(list, JsonResultUtils.Code.SUCCESS);
    }
}
