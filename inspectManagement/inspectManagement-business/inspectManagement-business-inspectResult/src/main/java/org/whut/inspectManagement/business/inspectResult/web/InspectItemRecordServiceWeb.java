package org.whut.inspectManagement.business.inspectResult.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.whut.inspectManagement.business.inspectResult.entity.SearchBean;
import org.whut.inspectManagement.business.inspectResult.service.InspectItemRecordService;
import org.whut.platform.business.user.security.UserContext;
import org.whut.platform.fundamental.util.json.JsonResultUtils;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: John
 * Date: 14-5-13
 * Time: 下午2:11
 * To change this template use File | Settings | File Templates.
 */
@Component
@Path("/inspectItemRecord")
public class InspectItemRecordServiceWeb {

    @Autowired
    private InspectItemRecordService inspectItemRecordService;

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @POST
    @Path("/findByCondition")
    public String getInspectTableRecordGroupByEmployer(@FormParam("userId")String userId,@FormParam("deviceId")String deviceId,@FormParam("sTime")String sTime,@FormParam("eTime")String eTime){



        Map<String,Object> condition = new HashMap<String, Object>();
        if(userId!=null&&!userId.equals("")){
            condition.put("userId",userId);
        }
        if(deviceId!=null&&!deviceId.equals("")){
            condition.put("deviceId",deviceId);
        }
        if(sTime!=null&&!sTime.equals("")){
            condition.put("startTime",sTime+" 00:00:00");
        }
        if(eTime!=null&&!eTime.equals("")){
            condition.put("endTime",eTime+" 59:59:59");
        }
        condition.put("appId", UserContext.currentUserAppId());
        List<SearchBean> list=inspectItemRecordService.findByCondition(condition);
        return JsonResultUtils.getObjectResultByStringAsDefault(list, JsonResultUtils.Code.SUCCESS);
    }

}
