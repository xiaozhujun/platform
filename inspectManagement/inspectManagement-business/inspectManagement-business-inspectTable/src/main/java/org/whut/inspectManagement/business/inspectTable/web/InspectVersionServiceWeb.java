package org.whut.inspectManagement.business.inspectTable.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.whut.inspectManagement.business.inspectTable.service.InspectVersionService;
import org.whut.platform.business.user.security.UserContext;
import org.whut.platform.fundamental.util.json.JsonResultUtils;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: yangyang
 * Date: 14-9-10
 * Time: 下午5:00
 * To change this template use File | Settings | File Templates.
 */
@Component
@Path("/inspectVersion")
public class InspectVersionServiceWeb {
    private static final long VERSION_ID = 1;
    private static String REALPATH = "http://localhost:8080/inspectManagement/";
    @Autowired
    private InspectVersionService inspectVersionService;

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/getVersionCode")
    @POST
    public String getVersionCode() {
        long appId = UserContext.currentUserAppId();
        Long versionCode;
        try {
            versionCode = inspectVersionService.getVersionCode(VERSION_ID,appId);
        } catch (Exception e) {
            versionCode = 0L;
        }
        if (versionCode == 0L) {
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"未检测到版本号");
        }
//        return JsonResultUtils.getObjectResultByStringAsDefault(versionCode, JsonResultUtils.Code.SUCCESS);
        Map<String,Long> map = new HashMap<String, Long>();
        map.put("versionCode",versionCode);
        return JsonResultUtils.getObjectResultByStringAsDefault(map , JsonResultUtils.Code.SUCCESS);
    }

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/getAddress")
    @POST
    public String getAddress() {
        long appId = UserContext.currentUserAppId();
        String downloadAddress = "";
        downloadAddress = inspectVersionService.getAddress(VERSION_ID,appId);
        if (downloadAddress == null || downloadAddress.equals("")) {
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"下载地址不存在");
        }
        REALPATH = REALPATH + downloadAddress;
        System.out.println("传递到安卓端的地址：" + REALPATH);
        return JsonResultUtils.getObjectResultByStringAsDefault(REALPATH, JsonResultUtils.Code.SUCCESS);
    }
}
