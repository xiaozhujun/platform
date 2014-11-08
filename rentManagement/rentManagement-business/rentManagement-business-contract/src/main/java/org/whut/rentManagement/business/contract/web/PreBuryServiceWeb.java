package org.whut.rentManagement.business.contract.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.whut.platform.business.user.security.UserContext;
import org.whut.platform.fundamental.util.json.JsonMapper;
import org.whut.platform.fundamental.util.json.JsonResultUtils;
import org.whut.rentManagement.business.contract.entity.PreBury;
import org.whut.rentManagement.business.contract.entity.subPreBury;
import org.whut.rentManagement.business.contract.service.PreBuryService;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Aaron
 * Date: 14-10-18
 * Time: 下午4:37
 * To change this template use File | Settings | File Templates.
 */
@Component
@Path("/prebury")
public class PreBuryServiceWeb {

    @Autowired
    PreBuryService preBuryService;

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/list")
    @POST
    public String list(){
        long appId = UserContext.currentUserAppId();
        List<Map<String,String>> list=preBuryService.getListByAppId(appId);
        return JsonResultUtils.getObjectResultByStringAsDefault(list, JsonResultUtils.Code.SUCCESS);
    }

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/add")
    @POST
    public String add(@FormParam("contractId") String contractId ,@FormParam("preBuryMan") String preBuryMan,
                      @FormParam("preBuryStatus") String preBuryStatus,@FormParam("preBuryTime") String preBuryTime){
        if(contractId==null||"".equals(contractId.trim())||preBuryMan==null
                ||preBuryStatus==null||"".equals(preBuryStatus.trim())||preBuryTime==null||"".equals(preBuryTime.trim())){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "参数不能为空!");
        }
        long contractid = Long.parseLong(contractId.replace(" ",""));
        long appId = UserContext.currentUserAppId();
        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try{
            date = sdf.parse(preBuryTime);
        }catch (Exception e){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"日期格式错误");
        }
/*        Long id;
        try {
            id = preBuryService.getIdByContractId(contractid);
        }catch (Exception e){
            id = null;
        }
        if (id==null){*/
        PreBury preBury = new PreBury();
        preBury.setAppId(appId);
        preBury.setContractId(Long.parseLong(contractId));
        preBury.setPreBuryMan(preBuryMan);
        preBury.setPreBuryTime(date);
        preBury.setPreBuryStatus(preBuryStatus);
            preBuryService.add(preBury);
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.SUCCESS.getCode(),"添加成功!");
/*        }else {
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "该合同预埋记录已经存在!");
        }*/

    }
    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/update")
    @POST
    public String update(@FormParam("jsonString")String jsonString)  {
        subPreBury subprebury = JsonMapper.buildNonDefaultMapper().fromJson(jsonString,subPreBury.class);
        if(subprebury.getPreBuryStatus()==null||"".equals(subprebury.getPreBuryStatus().trim())){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "预埋状态不能为空!");
        }
        long appId = UserContext.currentUserAppId();
        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try{
            date = sdf.parse(subprebury.getPreBuryTime());
        }catch (Exception e){
            JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"日期格式错误");
        }
        PreBury preBury = new PreBury();
        preBury.setId(subprebury.getId());
        preBury.setPreBuryTime(date);
        preBury.setPreBuryStatus(subprebury.getPreBuryStatus());
        preBury.setPreBuryMan(subprebury.getPreBuryMan());
        preBury.setAppId(appId);
        preBuryService.update(preBury);
        return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.SUCCESS.getCode(),"更新成功!");

    }
    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/delete")
    @POST
    public String delete(@FormParam("jsonString")String jsonString) {
        PreBury preBury = JsonMapper.buildNonDefaultMapper().fromJson(jsonString,PreBury.class);
        preBuryService.delete(preBury);
        return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
    }

    @Produces(MediaType.APPLICATION_JSON +";charset=UTF-8")
    @Path("/getPreburyList")
    @POST
    public String getPreburyList(@FormParam("user")String user,@FormParam("sTime")String sTime,@FormParam("eTime")String eTime){
        Map<String,Object> condition = new HashMap<String, Object>();
        if(user!=null&&!user.equals("")){
            condition.put("user",user);
        }
        if(sTime!=null&&!sTime.equals("")){
            condition.put("startTime",sTime+" 00:00:00");
        }
        if(eTime!=null&&!eTime.equals("")){
            condition.put("endTime",eTime+" 59:59:59");
        }
        condition.put("appId", UserContext.currentUserAppId());
        List<Map<String,String>> list=preBuryService.getPreburyList(condition);
        return JsonResultUtils.getObjectResultByStringAsDefault(list, JsonResultUtils.Code.SUCCESS);
    }




}
