package org.whut.inspectManagement.business.inspectLocate.web;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.whut.inspectManagement.business.inspectLocate.entity.InspectLocate;
import org.whut.inspectManagement.business.inspectLocate.service.InspectLocateService;
import org.whut.platform.business.user.security.UserContext;
import org.whut.platform.fundamental.logger.PlatformLogger;
import org.whut.platform.fundamental.util.json.JsonMapper;
import org.whut.platform.fundamental.util.json.JsonResultUtils;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: ThinkPad
 * Date: 14-7-11
 * Time: 上午10:15
 * To change this template use File | Settings | File Templates.
 */
@Component
@Path("/inspectLocate")
public class InspectLocateServiceWeb {

    public static final PlatformLogger logger = PlatformLogger.getLogger(InspectLocateServiceWeb.class);

     @Autowired
     private InspectLocateService inspectLocateService;
     @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
     @GET
     @Path("/getInspectLocateInfo")
     public String getInspectLocateInfo(){
         long appId=UserContext.currentUserAppId();
         Map<String,Object> condition = new HashMap<String, Object>();
         Date now = new Date();
         now.setTime(now.getTime()-60*60*1000);
         condition.put("appId",appId);
         condition.put("updateTime",now);
         List<Map<String,String>>list=inspectLocateService.findByCondition(condition);
         return JsonResultUtils.getObjectResultByStringAsDefault(list,JsonResultUtils.Code.SUCCESS);
     }
     @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
     @POST
     @Path("/receiveInspectLocateInfo")
     public String receiveInspectLocateInfo(@FormParam("jsonString")String jsonString){
         InspectLocate inspectLocate=new InspectLocate();
         inspectLocate=JsonMapper.buildNonDefaultMapper().fromJson(jsonString,InspectLocate.class);
         long appId=UserContext.currentUserAppId();
         long userId = UserContext.currentUserId();
         inspectLocate.setUserName(UserContext.currentUserName());
         inspectLocate.setUpdateTime(new Date());
         logger.info(jsonString);
         if(userId==inspectLocate.getUserId()){
             inspectLocate.setAppId(appId);
             Long id=inspectLocateService.validateIsExistRecord(appId,inspectLocate.getUserId());

             if(id!=null){
                 //用户已存在,update即可
                 inspectLocate.setId(id);
                 logger.info("update location info-id "+id);
                 inspectLocateService.update(inspectLocate);
             }else{
                 //用户不存在,insert即可
                 logger.info("add location for "+inspectLocate.getUserName());
                 inspectLocateService.add(inspectLocate);
             }
             return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
         }
         return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.ERROR);
     }
}
