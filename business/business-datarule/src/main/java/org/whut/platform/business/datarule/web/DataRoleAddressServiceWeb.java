package org.whut.platform.business.datarule.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.whut.platform.business.address.entity.Address;
import org.whut.platform.business.address.service.AddressService;
import org.whut.platform.business.datarule.service.DataRoleAddressService;
import org.whut.platform.business.user.entity.User;
import org.whut.platform.business.user.service.UserService;
import org.whut.platform.fundamental.logger.PlatformLogger;
import org.whut.platform.fundamental.redis.connector.RedisConnector;
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
    @Autowired
    private AddressService addressService;

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @POST
    @Path("/getProvinceAndColorWithDataRole")
    public String getProvinceAndColorWithDataRole(){
        String userName=userService.getMyUserDetailFromSession().getUsername();
        long userId=userService.getIdByName(userName);

       /* RedisConnector redisConnector = new RedisConnector();
        if(redisConnector.get(userId+":getProvinceAndColorWithDataRole")!=null){
            return redisConnector.get(userId+":getProvinceAndColorWithDataRole");
        }*/
        List<Map<String,String>> list=dataRoleAddressService.getProvinceAndColorWithDataRole(userId);
//        redisConnector.set(userId+":getProvinceAndColorWithDataRole",24*60*60,JsonResultUtils.getObjectResultByStringAsDefault(list, JsonResultUtils.Code.SUCCESS));
        /*return redisConnector.get(userId+":getProvinceAndColorWithDataRole");*/
        return JsonResultUtils.getObjectResultByStringAsDefault(list,JsonResultUtils.Code.SUCCESS);
    }

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @POST
    @Path("/getProvinceInfoWithDataRuleByCondition")
    public String getProvinceInfoWithDataRuleByCondition(@FormParam("equipmentVariety")String equipmentVariety,@FormParam("useTime")String useTime,@FormParam("value") String value){
        String userName=userService.getMyUserDetailFromSession().getUsername();
        long userId=userService.getIdByName(userName);
        List<Map<String,String>> list=new ArrayList<Map<String, String>>();
        if(value.equals("0")&&useTime.equals("0")){
        list=dataRoleAddressService.getProvinceInfoWithDataRuleByCondition(userId,equipmentVariety,"0","0",0f,0f);
        }else if(!value.equals("0")&&useTime.equals("0")){
        String[] values= value.split(";");
        float startValue = Float.parseFloat(values[0]);
        float endValue=Float.parseFloat(values[1]);
        list=dataRoleAddressService.getProvinceInfoWithDataRuleByCondition(userId,equipmentVariety,"0","0",startValue,endValue);
        }else if(value.equals("0")&&!useTime.equals("0")){
        String[] useTimes=useTime.split(";");
        list=dataRoleAddressService.getProvinceInfoWithDataRuleByCondition(userId,equipmentVariety,useTimes[0],useTimes[1],0f,0f);
        }else if(!value.equals("0")&&!useTime.equals("0")){
        String[] values= value.split(";");
        float startValue = Float.parseFloat(values[0]);
        float endValue=Float.parseFloat(values[1]);
        String[] useTimes=useTime.split(";");
        list=dataRoleAddressService.getProvinceInfoWithDataRuleByCondition(userId,equipmentVariety,useTimes[0],useTimes[1],startValue,endValue);
        }
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
    @Path("/getCityWithDataRole")
    public String getCityWithDataRole(@FormParam("province") String province){
        String userName=userService.getMyUserDetailFromSession().getUsername();
        long userId=userService.getIdByName(userName);
        List<Map<String,String>> list=dataRoleAddressService.getCityWithDataRole(province,userId);
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
    @Path("/getAreaWithDataRole")
    public String getAreaWithDataRole(@FormParam("province") String province,@FormParam("city")String city){
        String userName=userService.getMyUserDetailFromSession().getUsername();
        long userId=userService.getIdByName(userName);
        List<Map<String,String>> list=dataRoleAddressService.getAreaWithDataRole(province,city,userId);
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
    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @POST
    @Path("/getProvinceListWithDataRole")
    public String  getProvinceListWithDataRole(){
        String userName=userService.getMyUserDetailFromSession().getUsername();
        long userId=userService.getIdByName(userName);
        List<Map<String,String>> list=dataRoleAddressService.getProvinceListWithDataRole(userId);
        return JsonResultUtils.getObjectResultByStringAsDefault(list,JsonResultUtils.Code.SUCCESS);
    }
    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @POST
    @Path("/calculateProvinceRisk")
    public String  calculateProvinceRisk(){
        String userName=userService.getMyUserDetailFromSession().getUsername();
        long userId=userService.getIdByName(userName);
        List<Map<String,String>> provinceList=dataRoleAddressService.getProvinceInfoWithDataRuleByCondition0(userId,"0","0","0",0f,0f);
        for(Map<String,String> addressMap:provinceList){
            Address address=new Address();
            address.setProvince(addressMap.get("province"));
            Map<String,String> repeatProvinceMap=dataRoleAddressService.validateProvinceRiskValueIsExistByProvince(address.getProvince());
            if(repeatProvinceMap==null){

            }else{
                //如果存在,则删掉
                dataRoleAddressService.deleteProvinceRiskValue(repeatProvinceMap.get("province"));
            }
        }
        if(provinceList!=null&&provinceList.size()!=0){
        dataRoleAddressService.batchInsertToProvinceRiskValue(provinceList);
        }
        return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
    }
}
