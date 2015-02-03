package org.whut.trackSystem.business.device.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.whut.platform.business.user.security.UserContext;
import org.whut.platform.fundamental.logger.PlatformLogger;
import org.whut.platform.fundamental.util.json.JsonMapper;
import org.whut.platform.fundamental.util.json.JsonResultUtils;
import org.whut.trackSystem.business.device.entity.DeviceType;
import org.whut.trackSystem.business.device.service.DeviceTypeService;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: yangyang
 * Date: 14-12-15
 * Time: 下午4:20
 * To change this template use File | Settings | File Templates.
 */
@Component
@Path("/deviceType")
public class DeviceTypeServiceWeb {
    @Autowired
    private DeviceTypeService deviceTypeService;

    private static final PlatformLogger logger = PlatformLogger.getLogger(DeviceTypeServiceWeb.class);

    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @POST
    public String add(@FormParam("jsonString")String jsonString) {
        logger.info("add!!!");
        if (jsonString == null || jsonString.trim().equals("")) {
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"参数不能为空!");
        } else {
            DeviceType deviceType = JsonMapper.buildNonDefaultMapper().fromJson(jsonString,DeviceType.class);
            if (deviceType.getName() == null || deviceType.getName().trim().equals("") ||
                    deviceType.getDescription() == null || deviceType.getDescription().trim().equals("")) {
                return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"参数不能为空!");
            }
            Long appId = UserContext.currentUserAppId();
            Long id;
            try {
                id = deviceTypeService.getIdByNameAndAppId(deviceType.getName(),appId);
            } catch (Exception e) {
                id = null;
            }
            if (id == null) {
                deviceType.setAppId(appId);
                deviceTypeService.add(deviceType);
                return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
            } else {
                return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"设备类型已存在!");
            }
        }
    }

    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @POST
    public String list() {
        logger.info("list");
        Long appId = UserContext.currentUserAppId();
        List<DeviceType> list = deviceTypeService.getListByAppId(appId);
        return JsonResultUtils.getObjectResultByStringAsDefault(list, JsonResultUtils.Code.SUCCESS);
    }

    @Path("/update")
    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @POST
    public String update(@FormParam("jsonString")String jsonString) {
        if (jsonString == null || jsonString.trim().equals("")) {
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"参数不能为空!");
        } else {
            DeviceType deviceType = JsonMapper.buildNonDefaultMapper().fromJson(jsonString,DeviceType.class);
            if (deviceType == null || deviceType.getId() == null) {
                return  JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"参数不能为空!");
            }
            Long appId = UserContext.currentUserAppId();
            Long id;
            try {
                id = deviceTypeService.getIdByNameAndAppId(deviceType.getName(),appId);
            } catch (Exception e) {
                logger.info(e.getMessage());
                id = null;
            }
            if (id != null && id != deviceType.getId()) {
                return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"设备类型已存在!");
            }
            deviceType.setAppId(appId);
            deviceTypeService.update(deviceType);
            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
        }
    }

    @Path("/delete")
    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @POST
    public String delete(@FormParam("jsonString")String jsonString) {
        DeviceType deviceType = JsonMapper.buildNonDefaultMapper().fromJson(jsonString,DeviceType.class);
        Integer code = deviceTypeService.delete(deviceType);
        if(code > 0){
            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
        }
        else {
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"删除失败!");
        }
    }
}
