package org.whut.rentManagement.business.transport.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.whut.platform.business.user.security.UserContext;
import org.whut.platform.fundamental.util.json.JsonMapper;
import org.whut.platform.fundamental.util.json.JsonResultUtils;
import org.whut.rentManagement.business.transport.entity.TransportDevice;
import org.whut.rentManagement.business.transport.service.TransportDeviceService;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: xiaozhujun
 * Date: 14-11-11
 * Time: 上午12:07
 * To change this template use File | Settings | File Templates.
 */
@Component
@Path("/transportDevice")
public class TransportDeviceServiceWeb {
    @Autowired
    TransportDeviceService transportDeviceService;

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/listBy")
    @POST
    public String list(){
        long appId = UserContext.currentUserAppId();
        List<Map<String,String>> list=transportDeviceService.getListByAppId(appId);
        return JsonResultUtils.getObjectResultByStringAsDefault(list, JsonResultUtils.Code.SUCCESS);
    }

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/add")
    @POST
    public String add(@FormParam("transportId") String transportId,@FormParam("deviceId") String deviceId){
        if(transportId==null||transportId.trim().equals("")||deviceId==null||deviceId.trim().equals("")){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "对不起，参数不能为空!");
        }
        String[] deviceList = deviceId.split(",");
        Set set = new TreeSet();
        TransportDevice transportDevice;
        for(String deviceToTransport:deviceList){
            if(!set.contains(deviceToTransport)&&!deviceToTransport.trim().equals("")){
                transportDevice = new TransportDevice();
                transportDevice.setDeviceId(Long.parseLong(deviceToTransport));
                transportDevice.setTransportId(Long.parseLong(transportId));
                transportDeviceService.add(transportDevice);
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
        TransportDevice transportDevice = JsonMapper.buildNonDefaultMapper().fromJson(jsonString,TransportDevice.class);
        if(transportDevice.getId()==0){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "对不起，没有指定对象!");
        }
        transportDeviceService.delete(transportDevice);
        return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
    }

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/listByTransportId")
    @POST
    public String listByTransportId(@FormParam("transportId")String transportId){
        if(transportId==null||transportId.trim().equals("")){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "对不起，参数不能为空!");
        }
        Map<String,Object> condition = new HashMap<String, Object>();
        condition.put("id",Long.parseLong(transportId));
        condition.put("appId",UserContext.currentUserAppId());
        List<Map<String,Object>> list=transportDeviceService.listByTransportId(condition);
        return JsonResultUtils.getObjectResultByStringAsDefault(list,JsonResultUtils.Code.SUCCESS);
    }

}
