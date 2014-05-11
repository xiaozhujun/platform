package org.whut.platform.business.datarule.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.whut.platform.business.address.entity.Address;
import org.whut.platform.business.address.service.AddressService;
import org.whut.platform.business.datarule.service.DataRoleAddressService;
import org.whut.platform.business.user.service.UserService;
import org.whut.platform.fundamental.logger.PlatformLogger;
import org.whut.platform.fundamental.util.json.JsonResultUtils;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: ThinkPad
 * Date: 14-4-23
 * Time: 下午2:26
 * To change this template use File | Settings | File Templates.
 */
@Component
@Path("/dataRuleAddress")
public class DataRoleAddressServiceWeb {
    private static PlatformLogger logger = PlatformLogger.getLogger(DataRoleAddressServiceWeb.class);

    @Autowired
    private DataRoleAddressService dataRoleAddressService;
    @Autowired
    private UserService userService;

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @POST
    @Path("/getProvinceAndColorWithDataRole")
    public String getProvinceAndColorWithDataRole(){
        String userName=userService.getMyUserDetailFromSession().getUsername();
        long userId=userService.getIdByName(userName);
        List<Map<String,String>> list=dataRoleAddressService.getProvinceAndColorWithDataRole(userId);
        return JsonResultUtils.getObjectResultByStringAsDefault(list, JsonResultUtils.Code.SUCCESS);
    }
    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @POST
    @Path("/getProvinceInfoWithDataRuleByCondition")
    public String getProvinceInfoWithDataRuleByCondition(@FormParam("equipmentVariety")String equipmentVariety,@FormParam("useTime")String useTime){
        String userName=userService.getMyUserDetailFromSession().getUsername();
        long userId=userService.getIdByName(userName);
        /*if (equipmentVariety.trim().equals("")){
            equipmentVariety=null;
        }else if(useTime.trim().equals("")){
            useTime=null;
        }*/
        List<Map<String,String>> list=dataRoleAddressService.getProvinceInfoWithDataRuleByCondition(userId,equipmentVariety,useTime);
        return JsonResultUtils.getObjectResultByStringAsDefault(list, JsonResultUtils.Code.SUCCESS);
    }
    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @POST
    @Path("/getCityAndColorWithDataRole")
    public String getCityAndColorWithDataRole(@FormParam("province") String province){
        String userName=userService.getMyUserDetailFromSession().getUsername();
        long userId=userService.getIdByName(userName);
        List<Map<String,String>> list=dataRoleAddressService.getCityAndColorWithDataRole(province,userId);
        List<Map<String,String>> resultList=new ArrayList<Map<String, String>>();
        for(Map<String,String>map:list){
            Map<String,String>map1=new HashMap<String, String>();
            map1=dataRoleAddressService.transferCityMap(map1,map);
            Long craneNumber=dataRoleAddressService.getCraneNumberByCity(province,map.get("city"));
            if(craneNumber==null){
            map1.put("craneNumber","0");
            }
            map1.put("craneNumber",String.valueOf(craneNumber));
            resultList.add(map1);
        }
        return JsonResultUtils.getObjectStrResultByStringAsDefault(resultList,200,province);
    }
    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @POST
    @Path("/getAreaAndColorWithDataRole")
    public String getAreaAndColorWithDataRole(@FormParam("province") String province,@FormParam("city")String city){
        String userName=userService.getMyUserDetailFromSession().getUsername();
        long userId=userService.getIdByName(userName);
        List<Map<String,String>> list=dataRoleAddressService.getAreaAndColorWithDataRole(province,city,userId);
        List<Map<String,String>> resultList=new ArrayList<Map<String, String>>();
        for(Map<String,String>map:list){
            Map<String,String> map1=new HashMap<String, String>();
            map1=dataRoleAddressService.transferAreaMap(map1,map);
            Long craneNumber=dataRoleAddressService.getCraneNumberByArea(province,city,map.get("area"));
            if(craneNumber==null){
            map1.put("craneNumber","0");
            }
            map1.put("craneNumber",String.valueOf(craneNumber));
            resultList.add(map1);
        }
        String province_city=province+","+city;
        return JsonResultUtils.getObjectStrResultByStringAsDefault(resultList,200,province_city);
    }
    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @POST
    @Path("/getAddressIdBydRoleName")
    public String getAddressIdBydRoleName(@FormParam("dRoleName") String dRoleName){
        List<Long> list=dataRoleAddressService.getAddressIdBydRoleName(dRoleName);
        return JsonResultUtils.getObjectResultByStringAsDefault(list,JsonResultUtils.Code.SUCCESS);
    }
}
