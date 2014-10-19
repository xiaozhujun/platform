package org.whut.rentManagement.business.contract.web;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.whut.platform.business.user.security.UserContext;
import org.whut.platform.fundamental.util.json.JsonMapper;
import org.whut.platform.fundamental.util.json.JsonResultUtils;
import org.whut.rentManagement.business.contract.entity.Installation;
import org.whut.rentManagement.business.contract.entity.subInstallation;
import org.whut.rentManagement.business.contract.service.InstallationService;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Aaron
 * Date: 14-10-12
 * Time: 下午4:36
 * To change this template use File | Settings | File Templates.
 */
@Component
@Path("/installation")
public class InstallationServiceWeb {

    @Autowired
    InstallationService installationservice;

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/list")
    @POST
    public String list(){
        long appId = UserContext.currentUserAppId();
        List<Map<String,String>> list=installationservice.getListByAppId(appId);
        return JsonResultUtils.getObjectResultByStringAsDefault(list,JsonResultUtils.Code.SUCCESS);
    }

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/add")
    @POST
    public String add(@FormParam("contractId") String contractId ,@FormParam("type") String type,@FormParam("installDeviceId") String installDeviceId,
                      @FormParam("installMan") String installMan,@FormParam("installTime") String installTime,
                      @FormParam("installStatus") String installStatus){
        if(contractId==null||"".equals(contractId.trim())||"".equals(type)||type==null||installDeviceId==null||"".equals(installDeviceId.trim())
                ||installMan==null||"".equals(installMan)||installTime==null||"".equals(installStatus)||installStatus==null){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "参数不能为空!");
        }
        long contractIdnew = Long.parseLong(contractId.replace(" ",""));
        installDeviceId = installDeviceId.replace(" ","");
        long appId = UserContext.currentUserAppId();
        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try{
            date = sdf.parse(installTime);
        }catch (Exception e){
            JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"日期格式错误");
        }
        Long id;
        try {
            id = installationservice.getIdByDeviceId(contractIdnew);
        }
        catch(Exception e){
            id = null;
        }
        if (id==null){
            Installation Installation = new Installation();
            Installation.setAppId(appId);
            Installation.setContractId(contractIdnew);
            Installation.setType(type);
            Installation.setInstallDeviceId(Long.parseLong(installDeviceId));
            Installation.setInstallMan(installMan);
            Installation.setInstallStatus(installStatus);
            Installation.setInstallTime(date);
            installationservice.add(Installation);
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.SUCCESS.getCode(),"添加成功!");
        }
        else {
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "该合同的安装设备记录已存在!");
        }
    }
    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/update")
    @POST
    public String update(@FormParam("jsonString")String jsonString)  {
        subInstallation subinstallation = JsonMapper.buildNonDefaultMapper().fromJson(jsonString,subInstallation.class);
        if ("".equals(subinstallation.getInstallStatus().trim())){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"参数不能为空");
        }
        long appId = UserContext.currentUserAppId();
        Installation installation = new Installation();
        Date date = null;
        installation.setId(subinstallation.getId());
        installation.setContractId(Long.parseLong(subinstallation.getContractId()));
        installation.setType(subinstallation.getType());
        installation.setInstallStatus(subinstallation.getInstallStatus());
        installation.setInstallMan(subinstallation.getInstallMan());
        installation.setInstallDeviceId(Long.parseLong(subinstallation.getInstallDeviceId()));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try{
            date = sdf.parse(subinstallation.getInstallTime());
        }catch (Exception e){
            JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(),"日期格式错误");
        }
        installation.setInstallTime(date);
        installation.setAppId(appId);
        installationservice.update(installation);
        return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.SUCCESS.getCode(),"添加成功!");

    }
    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/delete")
    @POST
    public String delete(@FormParam("jsonString")String jsonString) {
        Installation installation = JsonMapper.buildNonDefaultMapper().fromJson(jsonString,Installation.class);
        installationservice.delete(installation);
        return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
    }




}
