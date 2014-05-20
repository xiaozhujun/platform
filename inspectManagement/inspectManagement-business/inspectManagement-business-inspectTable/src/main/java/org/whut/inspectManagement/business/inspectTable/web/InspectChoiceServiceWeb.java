package org.whut.inspectManagement.business.inspectTable.web;

import com.sun.research.ws.wadl.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.whut.inspectManagement.business.inspectTable.entity.InspectChoice;
import org.whut.inspectManagement.business.inspectTable.service.InspectChoiceService;
import org.whut.platform.fundamental.util.json.JsonMapper;
import org.whut.platform.fundamental.util.json.JsonResultUtils;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Date;
import java.util.List;
/**
 * Created with IntelliJ IDEA.
 * User: xjie
 * Date: 14-5-15
 * Time: 下午3:21
 * To change this template use File | Settings | File Templates.
 */
@Component
@Path("/inspectChoice")
public class InspectChoiceServiceWeb {
    @Autowired
    private InspectChoiceService inspectChoiceService;

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/add")
    @POST
    public String add(@FormParam("choiceValue") String choiceValue){

        if(choiceValue==null||choiceValue.equals("")){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"参数不能为空");
        }
        InspectChoice inspectChoice=new InspectChoice();
        inspectChoice.setChoiceValue(choiceValue);
        inspectChoiceService.add(inspectChoice);
        return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
    }

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/getList")
    @POST
    public String getList(){
        List<InspectChoice> list;
        list=inspectChoiceService.getList();
        return JsonResultUtils.getObjectResultByStringAsDefault(list,JsonResultUtils.Code.SUCCESS);
    }
    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/update")
    @POST
    public String update(@FormParam("jsonString") String jsonString){
        InspectChoice inspectChoice = JsonMapper.buildNonDefaultMapper().fromJson(jsonString,InspectChoice.class);
        if(inspectChoice.getChoiceValue()==null||inspectChoice.getChoiceValue().equals("")){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"参数不能为空");
        }
       inspectChoiceService.update(inspectChoice);
        return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
    }

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/delete")
    @POST
    public String delete(@FormParam("jsonString") String jsonString){
        InspectChoice inspectChoice= JsonMapper.buildNonDefaultMapper().fromJson(jsonString, InspectChoice.class);
        inspectChoiceService.delete(inspectChoice);
        return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
    }

}
