package org.whut.inspectManagement.business.configuration.web;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.whut.inspectManagement.business.configuration.service.DeviceConfigurationService;
import org.whut.inspectManagement.business.device.entity.DeviceTypeTag;
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
    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/importDeviceConfiguration/{data}")
    @GET
    public void importPersonnelConfiguration(@PathParam("data") String data,@Context HttpServletResponse response){
        List<DeviceTypeTag> deviceList = new ArrayList<DeviceTypeTag>();
        try {
            JSONArray jsonArray = new JSONArray(data);
            for(int i = 0;i<jsonArray.length();i++){
                DeviceTypeTag dtt = new DeviceTypeTag();
                JSONObject jsonObject = (JSONObject)jsonArray.get(i);
                dtt.setDeviceType(jsonObject.getString("deviceType"));
                dtt.setDeviceTypeNumber(jsonObject.getString("deviceTypeNumber"));
                dtt.setDeviceNumber(jsonObject.getString("deviceNumber"));
                dtt.setTagName(jsonObject.getString("tagName"));
                dtt.setTagId(jsonObject.getLong("tagId"));
                dtt.setTagNumber(jsonObject.getString("tagNumber"));
                deviceList.add(dtt);
            }
            String result = deviceConfigurationService.configurationConstruction(deviceList);
            System.out.println(result);
            if(result!=""){
                try{
                    String fileName = "tags.xml";
                    response.setContentType("text/plain");
                    response.setHeader("Location",new String(fileName.getBytes("GBK"),"UTF-8"));
                    response.setHeader("Content-Disposition","attachment;filename="+new String(fileName.getBytes("gb2312"),"ISO8859-1"));
                    OutputStream outputStream = response.getOutputStream();
                    outputStream.write(result.getBytes());
                    outputStream.flush();
                    outputStream.close();
                }
                catch(Exception e){
                    e.printStackTrace();
                    logger.error(e.getMessage());
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            logger.error(e.getMessage());
        }

    }
}
