package org.whut.trackSystem.business.device.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.whut.platform.business.user.security.UserContext;
import org.whut.platform.fundamental.logger.PlatformLogger;
import org.whut.platform.fundamental.util.json.JsonMapper;
import org.whut.platform.fundamental.util.json.JsonResultUtils;
import org.whut.trackSystem.business.device.entity.Device;
import org.whut.trackSystem.business.device.service.DeviceService;
import org.whut.trackSystem.business.device.service.DeviceTypeService;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: yangyang
 * Date: 14-12-15
 * Time: 下午4:15
 * To change this template use File | Settings | File Templates.
 */
@Component
@Path("/device")
public class DeviceServiceWeb {
    @Autowired
    private DeviceService deviceService;
    @Autowired
    private DeviceTypeService deviceTypeService;
    private static final PlatformLogger logger = PlatformLogger.getLogger(DeviceService.class);
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @POST
    public String add(@FormParam("jsonString")String jsonString) {
        if (jsonString == null || jsonString.trim().equals("")) {
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"参数不能为空!");
        } else {
            Device device = JsonMapper.buildNonDefaultMapper().fromJson(jsonString,Device.class);
            if (device.getName() == null || device.getName().trim().equals("")
                    || device.getNumber() == null || device.getNumber().trim().equals("")) {
                return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"参数不能为空!");
            }
            Long appId = UserContext.currentUserAppId();
            Long id;
            try {
                id = deviceService.getIdByNumber(device.getNumber(),appId);
            } catch (Exception e) {
                logger.error(e.getMessage());
                id = null;
            }

            if (id == null) {
                device.setAppId(appId);
                deviceService.add(device);
                return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.SUCCESS.getCode(), "添加成功!");
            } else {
                return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "设备编号已存在!");
            }
        }
    }

    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @POST
    public String list() {
        Long appId = UserContext.currentUserAppId();
        List<Device> list = deviceService.getListByAppId(appId);
        return JsonResultUtils.getObjectResultByStringAsDefault(list, JsonResultUtils.Code.SUCCESS);
    }

    @Path("/update")
    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @POST
    public String update(@FormParam("jsonString")String jsonString) {
        logger.info("update!!!");
        if (jsonString == null || jsonString.trim().equals("")) {
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"参数不能为空!");
        } else {
            Device device = JsonMapper.buildNonDefaultMapper().fromJson(jsonString,Device.class);
            if (device == null ||device.getId() == null) {
                return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"参数不能为空!");
            }
            Long appId = UserContext.currentUserAppId();
            Long id;
            try {
                id = deviceService.getIdByNumber(device.getNumber(),appId);
            } catch (Exception e) {
                logger.info(e.getMessage());
                id = null;
            }
            if (id != null && id != device.getId()) {
                return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"设备已存在!");
            }
            device.setAppId(appId);
            deviceService.update(device);
            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
        }
    }

    @Path("/delete")
    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @POST
    public String delete(@FormParam("jsonString")String jsonString) {
        logger.info("delete!!!");
        Device device= JsonMapper.buildNonDefaultMapper().fromJson(jsonString,Device.class);
        Integer code=deviceService.delete(device);
        if(code > 0){
            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
        }
        else {
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "删除失败!");
        }
    }

    @Path("/findByCondition")
    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @POST
    public String findByCondition() {
        Long appId = UserContext.currentUserAppId();
        List<Map<String,String>> list = deviceService.findByCondition(appId);
        return JsonResultUtils.getObjectResultByStringAsDefault(list, JsonResultUtils.Code.SUCCESS);
    }

    @Path("/getDeviceByCondition")
    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @POST
    public String getDeviceByCondition(@FormParam("groupId")Long groupId,@FormParam("userId")Long userId) {
        Long appId = UserContext.currentUserAppId();
        List<Map<String,String>> list = deviceService.getDeviceByCondition(groupId,userId,appId);
        return JsonResultUtils.getObjectResultByStringAsDefault(list, JsonResultUtils.Code.SUCCESS);
    }

}
