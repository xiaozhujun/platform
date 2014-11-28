package org.whut.rentManagement.business.contract.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.whut.platform.business.user.security.UserContext;
import org.whut.platform.fundamental.util.json.JsonMapper;
import org.whut.platform.fundamental.util.json.JsonResultUtils;
import org.whut.rentManagement.business.contract.entity.SelfInspect;
import org.whut.rentManagement.business.contract.service.SelfInspectService;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-10-14
 * Time: 下午5:10
 * To change this template use File | Settings | File Templates.
 */
@Component
@Path("/selfInspect")
public class SelfInspectServiceWeb {
    @Autowired
    SelfInspectService selfInspectService;

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/list")
    @POST
    public String list(){
        long appId = UserContext.currentUserAppId();
        List<SelfInspect> list=selfInspectService.getListByAppId(appId);
        return JsonResultUtils.getObjectResultByStringAsDefault(list, JsonResultUtils.Code.SUCCESS);
    }

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/add")
    @POST
    public String add(@FormParam("contractId") String contractId ,@FormParam("selfInspectDeviceId") String selfInspectDeviceId,
                      @FormParam("selfInspectMan") String selfInspectMan,@FormParam("selfInspectTime") String selfInspectTime, @FormParam("selfInspectStatus") String selfInspectStatus){
        if(contractId==null||"".equals(contractId.trim())||selfInspectDeviceId==null||"".equals(selfInspectDeviceId.trim())
                ||selfInspectMan==null||"".equals(selfInspectMan)||selfInspectStatus==null){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "参数不能为空!");
        }
        contractId = contractId.replace(" ","");
        selfInspectDeviceId = selfInspectDeviceId.replace(" ","");
        long appId = UserContext.currentUserAppId();
        Date date=new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try{
            date = sdf.parse(selfInspectTime);
        }catch (Exception e){
            JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"日期格式错误");
        }
        Long id;
        try {
           id = selfInspectService.getContractIdById(Long.parseLong(contractId));
      }
       catch(Exception e){
        id = null;
       }
        if (id==null){
            SelfInspect SelfInspect = new org.whut.rentManagement.business.contract.entity.SelfInspect();
            SelfInspect.setAppId(appId);
            SelfInspect.setContractId(Long.parseLong(contractId));
            SelfInspect.setSelfInspectDeviceId(Long.parseLong(selfInspectDeviceId));
            SelfInspect.setSelfInspectMan(selfInspectMan);
            SelfInspect.setSelfInspectStatus(selfInspectStatus);
            SelfInspect.setSelfInspectTime(date);
            selfInspectService.add(SelfInspect);
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.SUCCESS.getCode(), "添加成功!");
        }
        else {
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "合同已经存在!");
        }
    }

    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/update")
    @POST
    public String update(@FormParam("jsonString") String jsonString){
        SelfInspect selfInspect= JsonMapper.buildNonDefaultMapper().fromJson(jsonString,SelfInspect.class);
        int result=selfInspectService.update(selfInspect);
        if(result>0){
            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS) ;
        }
        else {
            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.ERROR);
        }
    }

    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/delete")
    @POST
    public  String delete(@FormParam("jsonString") String jsonString){
        SelfInspect selfInspect= JsonMapper.buildNonDefaultMapper().fromJson(jsonString,SelfInspect.class);
        int result=selfInspectService.delete(selfInspect);
        if(result>0){
            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
        }
        else {
            return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.ERROR);
        }
    }
}
