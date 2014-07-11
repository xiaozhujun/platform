package org.whut.inspectManagement.business.inspectLocate.web;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.whut.inspectManagement.business.inspectLocate.entity.InspectLocate;
import org.whut.inspectManagement.business.inspectLocate.service.InspectLocateService;
import org.whut.platform.business.user.security.UserContext;
import org.whut.platform.fundamental.util.json.JsonMapper;
import org.whut.platform.fundamental.util.json.JsonResultUtils;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
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
     @Autowired
     private InspectLocateService inspectLocateService;
     @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
     @POST
     @Path("/getInspectLocateInfo")
     public String getInspectLocateInfo(){
         long appId=UserContext.currentUserAppId();
         List<Map<String,String>>list=inspectLocateService.getInspectLocateInfoByAppId(appId);
         return JsonResultUtils.getObjectResultByStringAsDefault(list,JsonResultUtils.Code.SUCCESS);
     }
     @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
     @POST
     @Path("/receiveInspectLocateInfo")
     public String receiveInspectLocateInfo(@FormParam("jsonString")String jsonString){
         InspectLocate inspectLocate=new InspectLocate();
         inspectLocate=JsonMapper.buildNonDefaultMapper().fromJson(jsonString,InspectLocate.class);
         long appId=UserContext.currentUserAppId();
         Long id=inspectLocateService.validateIsExistRecord(appId,inspectLocate.getUserId());
         if(id!=null){
             //用户已存在,update即可
             inspectLocateService.update(inspectLocate);
         }else{
             //用户不存在,insert即可
             inspectLocateService.add(inspectLocate);
         }
         return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
     }
}
