package org.whut.rentManagement.business.contract.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.whut.platform.business.user.security.UserContext;
import org.whut.platform.fundamental.util.json.JsonMapper;
import org.whut.platform.fundamental.util.json.JsonResultUtils;
import org.whut.rentManagement.business.contract.entity.RemoveDevice;
import org.whut.rentManagement.business.contract.service.RemoveDeviceService;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: xiaozhujun
 * Date: 14-11-12
 * Time: 下午8:49
 * To change this template use File | Settings | File Templates.
 */
@Component
@Path("/removeDevice")
public class RemoveDeviceServiceWeb {

    @Autowired
    RemoveDeviceService removeDeviceService;

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/list")
    @POST
    public String list(){
        long appId = UserContext.currentUserAppId();
        List<Map<String,String>> list=removeDeviceService.getListByAppId(appId);
        return JsonResultUtils.getObjectResultByStringAsDefault(list, JsonResultUtils.Code.SUCCESS);
    }

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/add")
    @POST
    public String add(@FormParam("removeId") String removeId,@FormParam("deviceId") String deviceId){
        if(removeId==null||removeId.trim().equals("")||deviceId==null||deviceId.trim().equals("")){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "对不起，参数不能为空!");
        }
        String[] deviceList = deviceId.split(",");
        Set set = new TreeSet();
        RemoveDevice removeDevice;
        for(String deviceToTransport:deviceList){
            if(!set.contains(deviceToTransport)&&!deviceToTransport.trim().equals("")){
                removeDevice = new RemoveDevice();
                removeDevice.setDeviceId(Long.parseLong(deviceToTransport));
                removeDevice.setRemoveId(Long.parseLong(removeId));
                removeDeviceService.add(removeDevice);
            }
            set.add(deviceToTransport);
        }
        return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.SUCCESS.getCode(),"添加成功!");
    }

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/delete")
    @POST
    public String delete(@FormParam("jsonString")String jsonString) {
        if(jsonString==null||jsonString.trim().equals("")){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "对不起，参数不能为空!");
        }
        RemoveDevice removeDevice = JsonMapper.buildNonDefaultMapper().fromJson(jsonString,RemoveDevice.class);
        if(removeDevice.getId()==0){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "对不起，没有指定对象!");
        }
        removeDeviceService.delete(removeDevice);
        return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
    }

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/listByInstallationId")
    @POST
    public String listByTransportId(@FormParam("removeId")String removeId){
        if(removeId==null||removeId.trim().equals("")){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "对不起，参数不能为空!");
        }
        Map<String,Object> condition = new HashMap<String, Object>();
        condition.put("id",Long.parseLong(removeId));
        condition.put("appId",UserContext.currentUserAppId());
        List<Map<String,Object>> list=removeDeviceService.listByRemoveId(condition);
        return JsonResultUtils.getObjectResultByStringAsDefault(list,JsonResultUtils.Code.SUCCESS);
    }
}
