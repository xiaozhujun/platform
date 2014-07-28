package org.whut.inspectManagement.business.inspectTable.web;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.whut.inspectManagement.business.device.entity.InspectArea;
import org.whut.inspectManagement.business.device.service.InspectAreaService;
import org.whut.inspectManagement.business.inspectTable.entity.InspectItem;
import org.whut.inspectManagement.business.inspectTable.entity.SubInspectItem;

import org.whut.inspectManagement.business.inspectTable.entity.InspectItemChoice;
import org.whut.inspectManagement.business.inspectTable.service.InspectChoiceService;
import org.whut.inspectManagement.business.inspectTable.service.InspectItemChoiceService;
import org.whut.inspectManagement.business.inspectTable.service.InspectItemService;
import org.whut.inspectManagement.business.inspectTable.service.InspectTableService;


import org.whut.platform.business.user.security.UserContext;
import org.whut.platform.fundamental.util.json.JsonMapper;
import org.whut.platform.fundamental.util.json.JsonResultUtils;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
    InspectTableService inspectTableService;
    @Autowired
    InspectItemService inspectItemService;
    @Autowired
    InspectItemChoiceService inspectItemChoiceService;
    @Autowired
    InspectChoiceService inspectChoiceService;
    @Autowired
    InspectAreaService inspectAreaService;

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/add")
    @POST
    public String add(@FormParam("jsonStringList") String jsonStringList){
        long appId=UserContext.currentUserAppId();
        List<InspectItemChoice> inspectItemChoiceList=new ArrayList<InspectItemChoice>();
        List<SubInspectItem> repeatList=new ArrayList<SubInspectItem>();
        List<SubInspectItem> successList=new ArrayList<SubInspectItem>();
        List<SubInspectItem> errorList=new ArrayList<SubInspectItem>();
        Date date=new Date();
        try {
            JSONArray jsonArray=new JSONArray(jsonStringList);
            if(jsonArray.length()==0){
                return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"参数不能为空");
            }
            for(int i=0;i<jsonArray.length();i++){
                String jsonString= jsonArray.get(i).toString();
                SubInspectItem subInspectItem=JsonMapper.buildNonDefaultMapper().fromJson(jsonString,SubInspectItem.class);
                if(subInspectItem.getStatus().equals("已提交")){
                    successList.add(subInspectItem);
                }else{
                    InspectItem inspectItem=new InspectItem();
                    String input=subInspectItem.getInput();
                    String choices=subInspectItem.getChoiceValue();
                    if(input.equals("1")&&(subInspectItem.getName()==null||subInspectItem.getName().equals(""))){
                        subInspectItem.setStatus("信息空缺");
                        errorList.add(subInspectItem);
                    }else if(input.equals("0")&&(choices==null||choices.equals("null")||choices.equals("")||choices.contains("null")||subInspectItem.getName()==null||subInspectItem.getName().equals(""))){
                        subInspectItem.setStatus("信息空缺");
                        errorList.add(subInspectItem);
                    }else{
                        inspectItem.setName(subInspectItem.getName());
                        inspectItem.setNumber(subInspectItem.getNumber());
                        inspectItem.setCreatetime(date);
                        inspectItem.setInput(Integer.parseInt(subInspectItem.getInput()));
                        long tableId=inspectTableService.getIdByName(subInspectItem.getInspectTable(),appId);
                        inspectItem.setInspectTableId(tableId);
                        long areaId=inspectAreaService.getInspectAreaIdByNames(subInspectItem.getInspectArea(),subInspectItem.getDeviceType(),appId);
                        inspectItem.setInspectAreaId(areaId);
                        inspectItem.setDescription(subInspectItem.getDescription());
                        inspectItem.setAppId(appId);
                        long id;
                        try {
                            id=inspectItemService.getInspectItemId(subInspectItem.getName(), subInspectItem.getNumber(), tableId, areaId, appId);
                        }catch (Exception e){
                            id=0;
                        }
                        if(id==0){
                            inspectItemService.add(inspectItem);
                            subInspectItem.setStatus("已提交");
                            successList.add(subInspectItem);
                            long inspectId=inspectItem.getId();
                            if(input.equals("0")){
                                String [] choiceList=choices.split(";");
                                for(String choice:choiceList){
                                    InspectItemChoice inspectItemChoice=new InspectItemChoice();
                                    inspectItemChoice.setInspectItemId(inspectId);
                                    inspectItemChoice.setAppId(appId);
                                    inspectItemChoice.setInspectChoiceId(inspectChoiceService.getIdByChoiceValueAndAppId(choice,appId));
                                    inspectItemChoiceList.add(inspectItemChoice);
                                }
                            }
                        }else{
                            subInspectItem.setStatus("重复提交");
                            repeatList.add(subInspectItem);
                        }
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if(inspectItemChoiceList.size()!=0){
            inspectItemChoiceService.addList(inspectItemChoiceList);
        }
        if (errorList.size()!=0){
            errorList.addAll(repeatList);
            errorList.addAll(successList);
            return JsonResultUtils.getObjectResultByStringAsDefault(errorList,JsonResultUtils.Code.ERROR);
        }else if(repeatList.size()!=0){
            repeatList.addAll(successList);
            return JsonResultUtils.getObjectResultByStringAsDefault(repeatList,JsonResultUtils.Code.DUPLICATE);
        }else {
            return JsonResultUtils.getObjectResultByStringAsDefault(successList,JsonResultUtils.Code.SUCCESS);
        }
    }

    @Produces(MediaType.APPLICATION_JSON +";charset=UTF-8")
    @Path("/list")
    @POST
    public String list(){
        long appId=UserContext.currentUserAppId();
        List<Map<String,String>> list=inspectItemService.getInspectItemList(appId);
        return JsonResultUtils.getObjectResultByStringAsDefault(list, JsonResultUtils.Code.SUCCESS);
    }
    @Produces(MediaType.APPLICATION_JSON +";charset=UTF-8")
    @Path("/update")
    @POST
    public String update(@FormParam("jsonString") String jsonString) throws JSONException, ParseException {
        long appId=UserContext.currentUserAppId();
        SubInspectItem subInspectItem=JsonMapper.buildNonDefaultMapper().fromJson(jsonString,SubInspectItem.class);
        long areaId;
        try{
            areaId=inspectAreaService.getInspectAreaIdByNames(subInspectItem.getInspectArea(),subInspectItem.getDeviceType(),appId);
        }
        catch (Exception e){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"该设备类型不存在此点检区域");
        }
        long tableId=inspectTableService.getIdByName(subInspectItem.getInspectTable(),appId);
        if(subInspectItem.getName()==null||subInspectItem.getInspectTable()==null||subInspectItem.getInspectTable().equals("")||subInspectItem.getNumber()==null||subInspectItem.getName().equals("")||subInspectItem.getNumber().equals("")){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"参数不能为空!");
        }
        long id;
        try {
            id=inspectItemService.getInspectItemId(subInspectItem.getName(),subInspectItem.getNumber(),tableId,areaId,appId);
        }catch (Exception e){
            id=0;
        }
        if (id!=0){
            if(id!=subInspectItem.getId()) {
                return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"点检项已存在");
            }
        }
        //更新inspectItem表
        int isInput=Integer.parseInt(subInspectItem.getInput());
        if(isInput==0&&subInspectItem.getChoiceValue().equals("")){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"请选择点检选值");
        }
        if(isInput==1&&!subInspectItem.getChoiceValue().equals("")){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"输入模式下不能选择点检选值");
        }
        InspectItem inspectItem=new InspectItem();
        inspectItem.setId(subInspectItem.getId());
        inspectItem.setName(subInspectItem.getName());
        inspectItem.setDescription(subInspectItem.getDescription());
        DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        inspectItem.setCreatetime(format.parse(subInspectItem.getCreatetime()));
        inspectItem.setInspectAreaId(areaId);
        inspectItem.setNumber(subInspectItem.getNumber());
        inspectItem.setInput(isInput);
        inspectItem.setInspectTableId(inspectTableService.getIdByName(subInspectItem.getInspectTable(),appId));
        inspectItem.setAppId(appId);

        inspectItemService.update(inspectItem);

        //更新inspectItem_choice表
     if(isInput==1){
          inspectItemChoiceService.deleteByInspectItemIdAndAppId(subInspectItem.getId(),appId);
     }else {
            List<InspectItemChoice> inspectItemChoicesList=new ArrayList<InspectItemChoice>();
            String[] choiceValueArray=subInspectItem.getChoiceValue().split(";");
            inspectItemChoiceService.deleteByInspectItemIdAndAppId(subInspectItem.getId(),appId);
            for(String choice:choiceValueArray){
                InspectItemChoice inspectItemChoice=new InspectItemChoice();
                inspectItemChoice.setInspectItemId(subInspectItem.getId());
                inspectItemChoice.setInspectChoiceId(inspectChoiceService.getIdByChoiceValueAndAppId(choice,appId));
                inspectItemChoice.setAppId(appId);
                inspectItemChoicesList.add(inspectItemChoice);
            }
            if(!inspectItemChoicesList.isEmpty()) {
                inspectItemChoiceService.addList(inspectItemChoicesList);
            }
        }

        return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
    }
    @Produces(MediaType.APPLICATION_JSON +";charset=UTF-8")
    @Path("/delete")
    @POST
    public String delete(@FormParam("jsonString") String jsonString) throws JSONException, ParseException{
        long appId=UserContext.currentUserAppId();
        //SubInspectItem subInspectItem = JsonMapper.buildNonDefaultMapper().fromJson(jsonString,SubInspectItem.class);
        JSONObject jsonObject=new JSONObject(jsonString);

        String itemId=jsonObject.getString("id");
        inspectItemChoiceService.deleteByInspectItemIdAndAppId(Integer.parseInt(itemId),appId);
        InspectItem inspectItem=new InspectItem();
        inspectItem.setId(Integer.parseInt(itemId));
        int result=inspectItemService.delete(inspectItem);
        if(result>=0){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.SUCCESS.getCode(),"操作成功");
        }
        else
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.SUCCESS.getCode(),"操作失败");
    }
    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/getInspectAreaIdByNames")
    @POST
    public String getInspectAreaIdByNames(@FormParam("inspectTable") String inspectTable,@FormParam("inspectAreaName") String inspectAreaName,@FormParam("deviceTypeName") String deviceTypeName,@FormParam("choices")String choices){
        if (inspectTable==null||inspectTable.equals("")){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"所属点检表名不能为空！");
        }else if(choices==null||choices.equals("")){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"请先添加点检选值！");
        }
        else{
            long appId=UserContext.currentUserAppId();
            long areaId;
            try {
                areaId=inspectAreaService.getInspectAreaIdByNames(inspectAreaName,deviceTypeName,appId);
            }catch (Exception e){
                return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"参数不能为空或设备类型不包含点检区域！");
            }
            return JsonResultUtils.getObjectResultByStringAsDefault(areaId, JsonResultUtils.Code.SUCCESS);
        }

    }
    @Produces(MediaType.APPLICATION_JSON+";charset=UFT-8")
    @Path("/getInspectAreaNameByDeviceTypeNameAndAppId")
    @POST
    public String getInspectAreaByDeviceTypeNameAndAppId(@FormParam("deviceType") long deviceType){
        List<InspectArea> list=inspectAreaService.getInspectAreaByDeviceTypeId(deviceType);
        return JsonResultUtils.getObjectResultByStringAsDefault(list,JsonResultUtils.Code.SUCCESS);
    }
}
