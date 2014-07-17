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
        List<SubInspectItem> list=new ArrayList<SubInspectItem>();
        List<SubInspectItem> itemSuccessList=new ArrayList<SubInspectItem>();
        List<SubInspectItem> itemErrorList=new ArrayList<SubInspectItem>();
        Date date=new Date();
        try {
            JSONArray jsonArray=new JSONArray(jsonStringList);
            if(jsonArray.length()==0){
                return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"参数不能为空");
            }
            for(int i=0;i<jsonArray.length();i++){
                String jsonString= jsonArray.get(i).toString();
                SubInspectItem subInspectItem=JsonMapper.buildNonDefaultMapper().fromJson(jsonString,SubInspectItem.class);
                InspectItem inspectItem=new InspectItem();
                String input=subInspectItem.getInput();
                String choices=subInspectItem.getChoiceValue();
               long key=subInspectItem.getId();
                subInspectItem.setId(key);
                if(input.equals("1")&&(subInspectItem.getName()==null||subInspectItem.getName().equals("")||subInspectItem.getNumber()==null||subInspectItem.getNumber().equals(""))){
                    subInspectItem.setStatus("信息空缺");
                    itemErrorList.add(subInspectItem);
                }else if(input.equals("0")&&(choices==null||choices.equals("null")||choices.equals("")||choices.contains("null")||subInspectItem.getName()==null||subInspectItem.getName().equals("")||subInspectItem.getNumber()==null||subInspectItem.getNumber().equals(""))){
                    subInspectItem.setStatus("信息空缺");
                    itemErrorList.add(subInspectItem);
              }else{
                    inspectItem.setName(subInspectItem.getName());
                    inspectItem.setNumber(subInspectItem.getNumber());
                    inspectItem.setCreatetime(date);
              /*  JSONObject jsonObject= (JSONObject) jsonArray.get(i);
                String input=jsonObject.getString("isInput");*/
                    inspectItem.setInput(Integer.parseInt(subInspectItem.getInput()));
                    inspectItem.setInspectTableId(inspectTableService.getIdByName(subInspectItem.getInspectTable(),appId));
                    inspectItem.setInspectAreaId(inspectAreaService.getInspectAreaIdByNames(subInspectItem.getInspectArea(),subInspectItem.getDeviceType(),appId));
                    inspectItem.setDescription(subInspectItem.getDescription());
                    inspectItem.setAppId(appId);

                    if (subInspectItem.getStatus().equals("已提交")){

                       itemSuccessList.add(subInspectItem);

                    }else{

                    long note;
                    try {
                        note=inspectItemService.getInspectItemIdByNameAndNumberAndAppId(subInspectItem.getName(),subInspectItem.getNumber(),appId);
                    }catch (Exception e){
                        note=0;
                    }
                    if(note==0){
                        inspectItemService.add(inspectItem);
                        subInspectItem.setStatus("已提交");

                        itemSuccessList.add(subInspectItem);
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


                        list.add(subInspectItem);
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
        if (itemErrorList.size()!=0){
            itemErrorList.addAll(list);
            itemErrorList.addAll(itemSuccessList);
            return JsonResultUtils.getObjectResultByStringAsDefault(itemErrorList,JsonResultUtils.Code.ERROR);
        }else if(list.size()!=0){

            list.addAll(itemSuccessList);
            return JsonResultUtils.getObjectResultByStringAsDefault(list,JsonResultUtils.Code.DUPLICATE);
        }else {
            return JsonResultUtils.getObjectResultByStringAsDefault(itemSuccessList,JsonResultUtils.Code.SUCCESS);
            //return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.SUCCESS.getCode(),"操作成功");
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
        JSONObject jsonObject=new JSONObject(jsonString);
        SubInspectItem subInspectItem=new SubInspectItem();
        String itemId=jsonObject.getString("id");
        subInspectItem.setId(Integer.parseInt(itemId));
        subInspectItem.setName(jsonObject.getString("name"));
        subInspectItem.setNumber(jsonObject.getString("number"));
        subInspectItem.setChoiceValue(jsonObject.getString("choiceValue"));
        subInspectItem.setInspectArea(jsonObject.getString("inspectArea"));
        DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        subInspectItem.setCreatetime(format.parse(jsonObject.getString("createtime")));
        subInspectItem.setDeviceType(jsonObject.getString("deviceType"));
        subInspectItem.setDescription(jsonObject.getString("description"));
        subInspectItem.setInput(jsonObject.getString("input"));
        subInspectItem.setInspectTable(jsonObject.getString("inspectTable"));

        //SubInspectItem subInspectItem = JsonMapper.buildNonDefaultMapper().fromJson(jsonString,SubInspectItem.class);
        if(subInspectItem.getName()==null||subInspectItem.getInspectTable()==null||subInspectItem.getInspectTable().equals("")||subInspectItem.getNumber()==null||subInspectItem.getName().equals("")||subInspectItem.getNumber().equals("")){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"参数不能为空!");
        }
        long id;
        try {
            id=inspectItemService.getInspectItemIdByNameAndNumberAndAppId(subInspectItem.getName(),subInspectItem.getNumber(),appId);
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
        long inspectAreaId;
        try{
            inspectAreaId=inspectAreaService.getInspectAreaIdByNames(subInspectItem.getInspectArea(),subInspectItem.getDeviceType(),appId);
        }
        catch (Exception e){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"该设备类型不存在此点检区域");
        }
        if(isInput==0&&subInspectItem.getChoiceValue().equals("")){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"请选择点检选值");
        }
//        if(isInput==1&&subInspectItem.getChoiceValue()!=null||!subInspectItem.getChoiceValue().equals("null")||!subInspectItem.getChoiceValue().equals("")||!subInspectItem.getChoiceValue().contains("null")){
//            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"输入模式下不能选择点检选值");
//        }
        if(isInput==1&&!subInspectItem.getChoiceValue().equals("")){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"输入模式下不能选择点检选值");
        }
        InspectItem inspectItem=new InspectItem();
        inspectItem.setId(subInspectItem.getId());
        inspectItem.setName(subInspectItem.getName());
        inspectItem.setDescription(subInspectItem.getDescription());
        inspectItem.setCreatetime(subInspectItem.getCreatetime());
        inspectItem.setInspectAreaId(inspectAreaId);
        inspectItem.setNumber(subInspectItem.getNumber());
        inspectItem.setInput(isInput);
        inspectItem.setInspectTableId(inspectTableService.getIdByName(subInspectItem.getInspectTable(),appId));
        inspectItem.setAppId(appId);


        inspectItemService.update(inspectItem);

        //更新inspectItem_choice表
     if(isInput==1){
          inspectItemChoiceService.deleteByInspectItemIdAndAppId(subInspectItem.getId(),appId);
        }

//        if(isInput==1&&(subInspectItem.getChoiceValue()==null||subInspectItem.getChoiceValue().equals("null")||subInspectItem.getChoiceValue().equals("")||subInspectItem.getChoiceValue().contains("null") )){
//            inspectItemChoiceService.deleteByInspectItemIdAndAppId(subInspectItem.getId(),appId);
//        }
//        else
//        if (isInput==1&&(subInspectItem.getChoiceValue()!=null||!subInspectItem.getChoiceValue().equals("null")||!subInspectItem.getChoiceValue().equals("")||!subInspectItem.getChoiceValue().contains("null"))){
//            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"该设备类型不存在此点检区域");

//        }
        else {
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
