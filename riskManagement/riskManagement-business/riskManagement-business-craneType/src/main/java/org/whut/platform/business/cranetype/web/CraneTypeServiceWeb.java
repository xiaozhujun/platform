package org.whut.platform.business.cranetype.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.whut.platform.business.cranetype.entity.CraneType;
import org.whut.platform.business.cranetype.service.CraneTypeService;
import org.whut.platform.fundamental.util.json.JsonMapper;
import org.whut.platform.fundamental.util.json.JsonResultUtils;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: hadoop
 * Date: 14-12-30
 * Time: 下午4:13
 * To change this template use File | Settings | File Templates.
 */
@Component
@Path("/craneType")
public class CraneTypeServiceWeb {
    @Autowired
    private CraneTypeService craneTypeService;

    @Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/add")
    @POST
    public String add(@FormParam("name") String name,@FormParam("craneTypeModel") String craneTypeModel,@FormParam("description") String description){
        if(name==null || craneTypeModel=="" || name.trim().equals("") || craneTypeModel.trim().equals("")){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"参数不能为空!");
        }
        List<CraneType> craneTypeList = craneTypeService.findByName(name);
        if(craneTypeList.size()>0){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"类型已存在！");
        }
        else{
            String craneTypeModelId=craneTypeService.findIdByModelName(craneTypeModel);
            CraneType craneType = new CraneType();
            craneType.setName(name);
            craneType.setRiskmodelId(Long.parseLong(craneTypeModelId));
            craneType.setDescription(description);
            craneTypeService.add(craneType);
            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
        }
    }

    @Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/list")
    @GET
    public String list(){
        List<Map<String,String>> list = craneTypeService.list();
        return JsonResultUtils.getObjectResultByStringAsDefault(list,JsonResultUtils.Code.SUCCESS);
    }

    @Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/update")
    @POST
    public String update(@FormParam("jsonString") String jsonString){
        CraneType craneType = JsonMapper.buildNonDefaultMapper().fromJson(jsonString,CraneType.class);
        Long modelId=Long.parseLong(craneTypeService.findIdByModelName(craneType.getModel()));
        craneType.setRiskmodelId(modelId);
        int result = craneTypeService.update(craneType);
        if(result>0){
            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
        }else{
            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.ERROR);
        }
    }

    @Produces( MediaType.APPLICATION_JSON+ ";charset=UTF-8")
    @Path("/delete")
    @POST
    public String delete(@FormParam("jsonString") String jsonString){
        CraneType craneType = JsonMapper.buildNonDefaultMapper().fromJson(jsonString,CraneType.class);
        Long craneTypeId = craneType.getId();
        int deleted = craneTypeService.deleteByCraneId(craneTypeId);
        int result = craneTypeService.delete(craneType);
        if(result>0&&deleted>=0){
            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
        }else{
            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.ERROR);
        }
    }
    @Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/listModel")
    @GET
    public String listModel(){
        List<Map<String,String>> list = craneTypeService.listModel();
        return JsonResultUtils.getObjectResultByStringAsDefault(list,JsonResultUtils.Code.SUCCESS);
    }
    @Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/addEquipmentVarAndCraneType")
    @POST
    public String addEquipmentVarAndCraneType(@FormParam("equipmentVariety") String equipmentVariety,@FormParam("craneType") String craneType){
        if(equipmentVariety==null || craneType=="" || equipmentVariety.trim().equals("") || craneType.trim().equals("")){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"参数不能为空!");
        }
        List<Map<String,String>> craneTypeList = craneTypeService.findEquipmentAndCraneType(equipmentVariety,craneType);
        if(craneTypeList.size()>0){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"类型已存在！");
        }
        else{
            String craneTypeId=craneTypeService.findCraneTypeIdByName(craneType);
            Map<String,String> map=new HashMap<String, String>();
            map.put("equipmentVariety",equipmentVariety);
            map.put("craneTypeId",craneTypeId);
            map.put("craneTypeName",craneType);
            craneTypeService.addCraneInspectReportCraneType(map);
            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
        }
    }
    @Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/listCraneInspectAndType")
    @GET
    public String listCraneInspectAndType(){
        List<Map<String,String>> list = craneTypeService.listCraneInspectAndType();
        return JsonResultUtils.getObjectResultByStringAsDefault(list,JsonResultUtils.Code.SUCCESS);
    }
    @Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/updateCraneInspectAndType")
    @POST
    public String updateCraneInspectAndType(@FormParam("jsonString") String jsonString){
        Map<String,String> map = JsonMapper.buildNonDefaultMapper().fromJson(jsonString,Map.class);
        Long craneTypeId=craneTypeService.findIdByName(map.get("crane").toString());
        map.put("craneTypeId",String.valueOf(craneTypeId));
        int result = craneTypeService.updateCraneInspectAndType(map);
        if(result>0){
            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
        }else{
            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.ERROR);
        }
    }
    @Produces( MediaType.APPLICATION_JSON+ ";charset=UTF-8")
    @Path("/deleteCraneInspectAndType")
    @POST
    public String deleteCraneInspectAndType(@FormParam("jsonString") String jsonString){
        Map<String,String> map = JsonMapper.buildNonDefaultMapper().fromJson(jsonString,Map.class);
        int result = craneTypeService.deleteCraneInspectAndType(map);
        if(result>0){
            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
        }else{
            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.ERROR);
        }
    }


}
