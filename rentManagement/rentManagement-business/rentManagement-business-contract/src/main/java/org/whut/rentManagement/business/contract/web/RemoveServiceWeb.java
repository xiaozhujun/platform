package org.whut.rentManagement.business.contract.web;

import org.codehaus.jettison.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.whut.platform.business.user.security.UserContext;
import org.whut.platform.business.user.service.UserService;
import org.whut.platform.fundamental.util.json.JsonMapper;
import org.whut.platform.fundamental.util.json.JsonResultUtils;
import org.whut.rentManagement.business.contract.entity.Remove;
import org.whut.rentManagement.business.contract.entity.SubRemove;
import org.whut.rentManagement.business.contract.service.RemoveService;

import javax.ws.rs.*;
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
 * User: Administrator
 * Date: 14-10-14
 * Time: 下午5:09
 * To change this template use File | Settings | File Templates.
 */

@Component
@Path("/remove")
public class RemoveServiceWeb {
    @Autowired
    RemoveService removeService;
    @Autowired
    UserService userService;
    @Produces(MediaType.APPLICATION_JSON +";charset=UTF-8")
    @Path("/list")
    @GET
    public String list(){
        long appId= UserContext.currentUserAppId();
        List<Map<String,String>> list=removeService.getListByAppId(appId);
        return JsonResultUtils.getObjectResultByStringAsDefault(list, JsonResultUtils.Code.SUCCESS);
    }

    @Produces(MediaType.APPLICATION_JSON +";charset=UTF-8")
    @Path("/add")
    @POST
    public String add(@FormParam("jsonStringList") String jsonStringList) {
        long appId= UserContext.currentUserAppId();
        List<SubRemove> removeSuccessList = new ArrayList<SubRemove>();
        List<SubRemove> removeErrorList = new ArrayList<SubRemove>();
        List<SubRemove> removeRepeatList=new ArrayList<SubRemove>();
        try {
            JSONArray jsonArray = new JSONArray(jsonStringList);
            if(jsonArray.length()==0){
                return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "参数不能为空");
            }
            for(int i=0;i<jsonArray.length();i++){
                String jsonString= jsonArray.get(i).toString();
                SubRemove subRemove = JsonMapper.buildNonDefaultMapper().fromJson(jsonString,SubRemove.class);
                if(subRemove.getAddStatus().equals("提交成功1")){
                    removeSuccessList.add(subRemove);
                }
                else {
                    if (subRemove.getContractId().equals("") || subRemove.getContractId()==null || subRemove.getRemoveDeviceId().equals("") || subRemove.getRemoveDeviceId()==null) {
                        subRemove.setAddStatus("参数缺省");
                        removeErrorList.add(subRemove);
                    }
                    else {
                        long tempId;
                        try {
                            tempId = removeService.getIdByContractIdAndDeviceIdAndAppId(Long.parseLong(subRemove.getContractId())
                                    ,Long.parseLong(subRemove.getRemoveDeviceId()),appId);
                        }catch (Exception e) {
                            tempId = 0;
                        }

                        if(tempId != 0){
                            subRemove.setAddStatus("参数重复");
                            removeRepeatList.add(subRemove);
                        }
                        else {
                            Remove remove = new Remove();
                            remove.setContractId(Long.parseLong(subRemove.getContractId()));
                            remove.setRemoveDeviceId(Long.parseLong(subRemove.getRemoveDeviceId()));
                            remove.setRemoveMan(subRemove.getRemoveMan());
                            remove.setRemoveStatus(subRemove.getRemoveStatus());
                            Date date=new Date();
                            remove.setRemoveTime(date);
                            remove.setAppId(appId);
                            removeService.add(remove);
                            subRemove.setAddStatus("提交成功");
                            removeSuccessList.add(subRemove);
                        }
                    }
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        if (removeErrorList.size()!=0){
            removeErrorList.addAll(removeRepeatList);
            removeErrorList.addAll(removeSuccessList);
            return JsonResultUtils.getObjectResultByStringAsDefault(removeErrorList, JsonResultUtils.Code.ERROR);
        }else if(removeRepeatList.size()!=0){
            removeRepeatList.addAll(removeSuccessList);
            return JsonResultUtils.getObjectResultByStringAsDefault(removeRepeatList, JsonResultUtils.Code.DUPLICATE);
        }else {
            return JsonResultUtils.getObjectResultByStringAsDefault(removeSuccessList, JsonResultUtils.Code.SUCCESS);
        }
    }

    @Produces(MediaType.APPLICATION_JSON +";charset=UTF-8")
    @Path("/update")
    @POST
    public String update(@FormParam("jsonString")String jsonString) throws ParseException {
        long appId = UserContext.currentUserAppId();
        SubRemove subRemove = JsonMapper.buildNonDefaultMapper().fromJson(jsonString,SubRemove.class);
        long contractId = Long.parseLong(subRemove.getContractId());
        long removeDeviceId = Long.parseLong(subRemove.getRemoveDeviceId());
        Remove remove = new Remove();
        remove.setAppId(appId);
        remove.setContractId(contractId);
        remove.setRemoveDeviceId(removeDeviceId);
        remove.setRemoveMan(subRemove.getRemoveMan());
        remove.setRemoveStatus(subRemove.getRemoveStatus());
        DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        remove.setRemoveTime(format.parse(subRemove.getRemoveTime()));

        String removeMan = remove.getRemoveMan();
        if (removeMan.trim().equals("")) {
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "参数不能为空");
        }

        long id;
        try {
            id = removeService.getIdByContractIdAndDeviceIdAndAppId(Long.parseLong(subRemove.getContractId())
                    ,Long.parseLong(subRemove.getRemoveDeviceId()),appId);
        } catch (Exception e) {
            id = 0;
        }
        if (id != 0) {
            if (id != remove.getId()) {
                return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "设备已移除");
            }
        }
        removeService.update(remove);
        return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
    }
    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/getListByContractId")
    @POST
    public String getListByContractId(@FormParam("contractId") String contractId ){
        long appId= UserContext.currentUserAppId();
        List<Map<String,String>> list=removeService.getListByContractId(Long.parseLong(contractId));
        return JsonResultUtils.getObjectResultByStringAsDefault(list, JsonResultUtils.Code.SUCCESS);
    }

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/getListByDeviceId")
    @POST
    public String getListByDeviceId(@FormParam("removeDeviceId")long removeDeviceId){
        long appId= UserContext.currentUserAppId();
        List<Map<String,String>>list =removeService.getListByDeviceId(removeDeviceId);
        return JsonResultUtils.getObjectResultByStringAsDefault(list, JsonResultUtils.Code.SUCCESS);
    }

    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/delete")
    @POST
    public  String delete(@FormParam("jsonString") String jsonString){
        Remove remove= JsonMapper.buildNonDefaultMapper().fromJson(jsonString,Remove.class);
        int result=removeService.delete(remove);
        if(result>0){
            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
        }
        else {
            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.ERROR);
        }
    }
}
