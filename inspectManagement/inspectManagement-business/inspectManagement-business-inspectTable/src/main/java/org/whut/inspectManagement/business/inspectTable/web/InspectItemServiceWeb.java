package org.whut.inspectManagement.business.inspectTable.web;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
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
    @Path("/addList")
    @POST
    public String addList(@FormParam("jsonStringList") String jsonStringList){
        long appId=UserContext.currentUserAppId();
        List<InspectItem> inspectItemList=new ArrayList<InspectItem>();
        List<InspectItemChoice> inspectItemChoiceList=new ArrayList<InspectItemChoice>();
        Date date=new Date();
        try {
            JSONArray jsonArray=new JSONArray(jsonStringList);
            for(int i=0;i<jsonArray.length();i++){
                String jsonString= jsonArray.get(i).toString();
                SubInspectItem subInspectItem=JsonMapper.buildNonDefaultMapper().fromJson(jsonString,SubInspectItem.class);
                InspectItem inspectItem=new InspectItem();
                if(subInspectItem.getName()==null||subInspectItem.getName().equals("")||subInspectItem.getNumber()==null||subInspectItem.getNumber().equals("")){
                    return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"参数不能为空!");
                }
                inspectItem.setName(subInspectItem.getName());
                inspectItem.setNumber(subInspectItem.getNumber());
                inspectItem.setCreatetime(date);
              /*  JSONObject jsonObject= (JSONObject) jsonArray.get(i);
                String input=jsonObject.getString("isInput");*/
                inspectItem.setInput(Integer.parseInt(subInspectItem.getInput()));
                inspectItem.setInspectTableId(inspectTableService.getIdByName(subInspectItem.getInspectTable(),appId));
                long areaId;
                try{
                    areaId=inspectAreaService.getInspectAreaIdByNames(subInspectItem.getInspectArea(),subInspectItem.getDeviceType(),appId);
                }catch (Exception e){
                    return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"设备类型不包含点检区域！");
                }
                inspectItem.setInspectAreaId(areaId);
                inspectItem.setDescription(subInspectItem.getDescription());
                inspectItem.setAppId(appId);
                inspectItemList.add(inspectItem);
            }
            inspectItemService.addList(inspectItemList);
            for(int j=0;j<jsonArray.length();j++){
                String jsonString= jsonArray.get(j).toString();
                SubInspectItem subInspectItem=JsonMapper.buildNonDefaultMapper().fromJson(jsonString,SubInspectItem.class);
                /*JSONObject jsonObject= (JSONObject) jsonArray.get(j);
                String input=jsonObject.getString("isInput");*/
                String input=subInspectItem.getInput();
                if(input.equals("0")){
                    String choices=subInspectItem.getChoiceValue();
                    String [] choicesList=choices.split(";");
                    for (String choice:choicesList){
                        InspectItemChoice inspectItemChoice=new InspectItemChoice();
                        inspectItemChoice.setInspectChoiceId(inspectChoiceService.getIdByChoiceValueAndAppId(choice,appId));
                        inspectItemChoice.setInspectItemId(inspectItemService.getInspectItemIdByNameAndNumberAndAppId(subInspectItem.getName(),subInspectItem.getNumber(),appId));
                        inspectItemChoice.setAppId(appId);
                        inspectItemChoiceList.add(inspectItemChoice);
                    }
                }
            }
            inspectItemChoiceService.addList(inspectItemChoiceList);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.SUCCESS.getCode(),"操作成功");
    }
    @Produces(MediaType.APPLICATION_JSON +";charset=UTF-8")
    @Path("/list")
    @POST
    public String list(){
        List<InspectItem> list=inspectItemService.list();
        List<SubInspectItem> subList=new ArrayList<SubInspectItem>();
        for(InspectItem a:list){
            SubInspectItem subInspectItem=new SubInspectItem();
            subInspectItem.setId(a.getId());
            subInspectItem.setName(a.getName());
            subInspectItem.setNumber(a.getNumber());
            subInspectItem.setCreatetime(a.getCreatetime());
            subInspectItem.setDescription(a.getDescription());
            subInspectItem.setInspectTable(inspectTableService.getNameById(a.getInspectTableId()));
            subInspectItem.setInspectArea(inspectAreaService.getAreaById(a.getInspectAreaId()));
            subInspectItem.setDeviceType(inspectAreaService.getDeviceTypeById(a.getInspectAreaId()));
            if(a.getInput()==0){
                subInspectItem.setInput("否");
            }
            else{
                  subInspectItem.setInput("是");
                }
            subInspectItem.setChoiceValue(inspectItemChoiceService.getChoiceValueByItemId(a.getId()));
            subList.add(subInspectItem);
        }
        return JsonResultUtils.getObjectResultByStringAsDefault(subList, JsonResultUtils.Code.SUCCESS);
    }
    @Produces(MediaType.APPLICATION_JSON +";charset=UTF-8")
    @Path("/update")
    @POST
    public String update(@FormParam("jsonString") String jsonString){
        long appId=UserContext.currentUserAppId();
        SubInspectItem subInspectItem = JsonMapper.buildNonDefaultMapper().fromJson(jsonString,SubInspectItem.class);
        if(subInspectItem.getName()==null||subInspectItem.getInspectArea()==null||subInspectItem.getInspectTable()==null||subInspectItem.getNumber()==null||subInspectItem.getName().equals("")){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"参数不能为空!");
        }
     //更新inspectItem表
       int isInput;
        if(subInspectItem.getInput().equals("否")){
            isInput=0;
        }
        else {
            isInput=1;
        }
         long inspectAreaId=inspectAreaService.getInspectAreaIdByAreaNameAndDeviceTypeName(subInspectItem.getInspectArea(),subInspectItem.getDeviceType());

            InspectItem inspectItem=new InspectItem();
            inspectItem.setId(subInspectItem.getId());
            inspectItem.setName(subInspectItem.getName());
            inspectItem.setDescription(subInspectItem.getDescription());
            inspectItem.setCreatetime(subInspectItem.getCreatetime());
            inspectItem.setInspectAreaId(inspectAreaId);
            inspectItem.setNumber(subInspectItem.getNumber());
            inspectItem.setInput(isInput);
            inspectItem.setInspectTableId(inspectTableService.getIdByName(subInspectItem.getInspectTable(),appId));


        inspectItemService.update(inspectItem);

       //更新inspectItem_choice表

        List<InspectItemChoice> inspectItemChoicesList=new ArrayList<InspectItemChoice>();
        String[] choiceValueArray=subInspectItem.getChoiceValue().split(";");
        inspectItemChoiceService.deleteByInspectItemId(subInspectItem.getId());
            for(String choice:choiceValueArray){
                InspectItemChoice inspectItemChoice=new InspectItemChoice();
                inspectItemChoice.setInspectItemId(subInspectItem.getId());
                inspectItemChoice.setInspectChoiceId(inspectChoiceService.getIdByChoiceValueAndAppId(choice,appId));
                inspectItemChoicesList.add(inspectItemChoice);
            }

        if(!inspectItemChoicesList.isEmpty()) {
        inspectItemChoiceService.addList(inspectItemChoicesList);
        }

        return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
    }
    @Produces(MediaType.APPLICATION_JSON +";charset=UTF-8")
    @Path("/delete")
    @POST
    public String delete(@FormParam("jsonString") String jsonString){
        SubInspectItem subInspectItem = JsonMapper.buildNonDefaultMapper().fromJson(jsonString,SubInspectItem.class);
        inspectItemChoiceService.deleteByInspectItemId(subInspectItem.getId());
        InspectItem inspectItem=new InspectItem();
        inspectItem.setId(subInspectItem.getId());
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
    public String getInspectAreaIdByNames(@FormParam("inspectTable") String inspectTable,@FormParam("inspectAreaName") String inspectAreaName,@FormParam("deviceTypeName") String deviceTypeName){
        if (inspectTable==null||inspectTable.equals("")){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"所属点检表名不能为空！");
        }else{
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
  }
