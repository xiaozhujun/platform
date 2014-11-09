package org.whut.rentManagement.business.contract.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.whut.platform.business.user.security.UserContext;
import org.whut.platform.business.user.service.UserService;
import org.whut.platform.fundamental.util.json.JsonMapper;
import org.whut.platform.fundamental.util.json.JsonResultUtils;
import org.whut.rentManagement.business.contract.entity.Remove;
import org.whut.rentManagement.business.contract.entity.SubRemove;
import org.whut.rentManagement.business.contract.service.RemoveService;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
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
    @POST
    public String list(){
        long appId= UserContext.currentUserAppId();
        List<Map<String,String>> list=removeService.getListByAppId(appId);
        return JsonResultUtils.getObjectResultByStringAsDefault(list, JsonResultUtils.Code.SUCCESS);
    }

    @Produces(MediaType.APPLICATION_JSON +";charset=UTF-8")
    @Path("/add")
    @POST
    public String add(@FormParam("contractId") String contractId ,@FormParam("removeDeviceId") String removeDeviceId,@FormParam("removeMan") String removeMan,@FormParam("removeTime") String removeTime,@FormParam("removeStatus") String removeStatus){
        if(contractId==null||"".equals(contractId.trim())||removeDeviceId==null||"".equals(removeDeviceId.trim())
                ||removeMan==null||"".equals(removeMan)||removeStatus==null){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "参数不能为空!");
        }
        long appId= UserContext.currentUserAppId();
        Date date=new Date();
        Date date1=new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try{
            date = sdf.parse(removeTime);
        }catch (Exception e){
            JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"日期格式错误");
        }
        Long id;
        try {
            id = removeService.getRemoveDeviceIdById(Long.parseLong(removeDeviceId));
        }
        catch(Exception e){
            id = null;
        }
        if (id==null){
            Remove remove = new Remove();
            remove.setAppId(appId);
            remove.setContractId(Long.parseLong(contractId));
            remove.setRemoveDeviceId(Long.parseLong(removeDeviceId));
            remove.setRemoveMan(removeMan);
            remove.setRemoveStatus(removeStatus);
            remove.setRemoveTime(date1);
            removeService.add(remove);
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.SUCCESS.getCode(), "添加成功!");
        }
        else {
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "设备已经移除!");
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
        Remove remove1= JsonMapper.buildNonDefaultMapper().fromJson(jsonString,Remove.class);
        int result=removeService.update(remove1);
        if(result>0){
            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS) ;
        }
        else {
            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.ERROR);
        }


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

    @Produces(MediaType.APPLICATION_JSON +";charset=UTF-8")
    @Path("/getRemoveList")
    @POST
    public String getRemoveList(@FormParam("user")String user,@FormParam("device")String device,@FormParam("sTime")String sTime,@FormParam("eTime")String eTime){
        Map<String,Object> condition = new HashMap<String, Object>();
        if(user!=null&&!user.equals("")){
            condition.put("user",user);
        }
        if(device!=null&&!device.equals("")){
            condition.put("device",device);
        }
        if(sTime!=null&&!sTime.equals("")){
            condition.put("startTime",sTime+" 00:00:00");
        }
        if(eTime!=null&&!eTime.equals("")){
            condition.put("endTime",eTime+" 59:59:59");
        }
        condition.put("appId", UserContext.currentUserAppId());
        List<Map<String,String>> list=removeService.getRemoveList(condition);
        return JsonResultUtils.getObjectResultByStringAsDefault(list, JsonResultUtils.Code.SUCCESS);
    }
}
