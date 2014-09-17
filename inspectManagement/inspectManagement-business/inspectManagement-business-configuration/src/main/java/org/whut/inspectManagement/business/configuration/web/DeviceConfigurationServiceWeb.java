package org.whut.inspectManagement.business.configuration.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.whut.inspectManagement.business.configuration.entity.ConfigureParameter;
import org.whut.inspectManagement.business.configuration.service.DeviceConfigurationService;
import org.whut.inspectManagement.business.device.entity.DeviceTypeTag;
import org.whut.inspectManagement.business.device.service.DeviceService;
import org.whut.platform.fundamental.logger.PlatformLogger;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-5-29
 * Time: 下午6:29
 * To change this template use File | Settings | File Templates.
 */
@Component
@Path("/deviceConfiguration")
public class DeviceConfigurationServiceWeb {
    private static PlatformLogger logger = PlatformLogger.getLogger(DeviceConfigurationServiceWeb.class);

    @Autowired
    DeviceConfigurationService deviceConfigurationService;

    @Autowired
    DeviceService deviceService;
    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/importDeviceConfiguration/{data}")
    @GET
    public void importPersonnelConfiguration(@PathParam("data") String data,@Context HttpServletResponse response){
        List<DeviceTypeTag> deviceList = new ArrayList<DeviceTypeTag>();
        String[] tagIds = data.split(",");
        for(String s:tagIds){
            long tagId  = Long.parseLong(s);
            List<Map<String,Object>> mapList = deviceService.getListByTagId(tagId);
            for(Map<String,Object> map:mapList){
                DeviceTypeTag dtt = new DeviceTypeTag();
                dtt.setDeviceNumber((String)map.get("deviceNumber"));
                dtt.setDeviceType((String)map.get("deviceType"));
                dtt.setDeviceTypeNumber((String)map.get("deviceTypeNumber"));;
                dtt.setTagId(tagId);
                dtt.setTagAreaName((String)map.get("tagAreaName"));
                dtt.setTagAreaId(map.get("tagAreaId").toString());
                deviceList.add(dtt);
            }
        }
        String result = deviceConfigurationService.configurationConstruction(deviceList);
        System.out.println(result);
        if(result!=""){
            try{
                String fileName = ConfigureParameter.tagsXml;
                response.setContentType("text/plain;charset=utf-8");
                response.setHeader("Location",fileName);
                response.setHeader("Content-Disposition","attachment;filename="+new String(fileName.getBytes("utf-8"),"ISO8859-1"));
                OutputStream outputStream = response.getOutputStream();
                outputStream.write(result.getBytes("utf-8"));
                outputStream.flush();
                outputStream.close();
            }
            catch(Exception e){
                e.printStackTrace();
                logger.error(e.getMessage());
            }
        }
    }
}
