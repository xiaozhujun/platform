package org.whut.inspectManagement.business.inspectTable.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.whut.inspectManagement.business.inspectTable.entity.InspectItem;
import org.whut.inspectManagement.business.inspectTable.entity.SubInspectItem;

import org.whut.inspectManagement.business.inspectTable.entity.InspectItemChoice;
import org.whut.inspectManagement.business.inspectTable.service.InspectChoiceService;
import org.whut.inspectManagement.business.inspectTable.service.InspectItemChoiceService;
import org.whut.inspectManagement.business.inspectTable.service.InspectItemService;
import org.whut.inspectManagement.business.inspectTable.service.InspectTableService;

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
    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/add")
    @POST
    public String add(@FormParam("name") String name,@FormParam("description") String description,@FormParam("inspectTableId") String inspectTableId,
    @FormParam("inspectAreaId") long inspectAreaId,@FormParam("number") String number,@FormParam("isInput") int isInput,@FormParam("inspectChoiceId") String inspectChoiceId){
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
        String [] inspectItemChoiceList=inspectChoiceId.split(";");
        List<InspectItemChoice> inspectItemChoices=new ArrayList<InspectItemChoice>();
        if (isInput==0){
            for(String choice:inspectItemChoiceList){
                InspectItemChoice inspectItemChoice=new InspectItemChoice();
                inspectItemChoice.setInspectItemId(inspectItemService.getInspectItemByNameAndNumber(name,number));
                inspectItemChoice.setInspectChoiceId(Integer.parseInt(choice));
                inspectItemChoices.add(inspectItemChoice);
            }
            inspectItemChoiceService.addList(inspectItemChoices);
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
            subInspectItem.setInspectAreaId(a.getInspectAreaId());
            subInspectItem.setInspectTableName(inspectTableService.getNameById(a.getInspectTableId()));
            if(a.getInput()==0){
                subInspectItem.setInputName("否");
            }
            else{
                  subInspectItem.setInputName("是");
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
        SubInspectItem subInspectItem = JsonMapper.buildNonDefaultMapper().fromJson(jsonString,SubInspectItem.class);
        if(subInspectItem.getName()==null||subInspectItem.getInspectAreaId()==0||subInspectItem.getInspectTableName()==null||subInspectItem.getNumber()==null||subInspectItem.getName().equals("")){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"参数不能为空!");
        }
     //更新inspectItem表
       int isInput;
        if(subInspectItem.getInputName().equals("否")){
            isInput=0;
        }
        else {
            isInput=1;
        }

        String[] inspectTableNameArray=subInspectItem.getInspectTableName().split(";");
        List<InspectItem> inspectItemList=new ArrayList<InspectItem>();
        for(String a:inspectTableNameArray){
            InspectItem inspectItem=new InspectItem();
            inspectItem.setName(subInspectItem.getName());
            inspectItem.setDescription(subInspectItem.getDescription());
            inspectItem.setCreatetime(subInspectItem.getCreatetime());
            inspectItem.setInspectAreaId(subInspectItem.getInspectAreaId());
            inspectItem.setNumber(subInspectItem.getNumber());
            inspectItem.setInput(isInput);
            inspectItem.setInspectTableId(inspectTableService.getIdByName(a));
            inspectItemList.add(inspectItem);
        }
        List<Long> inspectItemIdList=new ArrayList<Long>();
        for(InspectItem item:inspectItemList){
            inspectItemIdList.add(inspectItemService.findIdByCondition(item));
        }
        inspectItemList.get(0).setId(subInspectItem.getId());
        inspectItemService.update(inspectItemList.get(0));
        inspectItemList.remove(0);
        if(!inspectItemList.isEmpty())  {
        inspectItemService.addList(inspectItemList);
        }
       //更新inspectItem_choice表

        List<InspectItemChoice> inspectItemChoicesList=new ArrayList<InspectItemChoice>();
        String[] choiceValueArray=subInspectItem.getChoiceValue().split(";");
        for (long l:inspectItemIdList){
            for(String a:choiceValueArray){
                InspectItemChoice inspectItemChoice=new InspectItemChoice();
                inspectItemChoice.setInspectItemId(l);
                 inspectItemChoice.setInspectChoiceId(inspectChoiceService.getIdByChoiceValue(a));
                 inspectItemChoicesList.add(inspectItemChoice);
            }
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
        InspectItem inspectItem = JsonMapper.buildNonDefaultMapper().fromJson(jsonString,InspectItem.class);
        int resault=inspectItemService.delete(inspectItem);
        if(resault>=0){
        return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.SUCCESS.getCode(),"操作成功");
        }
        else
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.SUCCESS.getCode(),"操作失败");
    }
}
