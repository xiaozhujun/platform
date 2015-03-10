package org.whut.platform.business.datarule.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.whut.platform.business.address.entity.Address;
import org.whut.platform.business.datarule.entity.DataRoleAddress;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Created with IntelliJ IDEA.
 * User: John
 * Date: 14-4-21
 * Time: 下午2:41
 * To change this template use File | Settings | File Templates.
 */

public class DataRoleAddressService {
    @Autowired
    private org.whut.platform.business.datarule.mapper.DataRoleAddressMapper dataRoleAddressMapper;
    public void add(DataRoleAddress dataRoleAddress){
       dataRoleAddressMapper.add(dataRoleAddress);
    }
    public List<Map<String,String>> getProvinceAndColorWithDataRole(long userId){
        List<String> addressIds=dataRoleAddressMapper.getProvinceByUserId(userId);
        List<Map<String,String>> list=new ArrayList<Map<String, String>>();
        for(String province:addressIds){
            Map<String,String> mm=dataRoleAddressMapper.getProvinceInfoWithDataRuleByCondition(province,"0","0","0",0f,0f);
            list.add(mm);
        }
        return list;
    }
    public List<Map<String,String>> getCityAndColorWithDataRole(String province,long userId){
        return dataRoleAddressMapper.getCityAndColorWithDataRole(province,userId);
    }
    public List<Map<String,String>> getCityWithDataRole(String province,long userId){
        return dataRoleAddressMapper.getCityWithDataRole(province,userId);
    }
    public List<Map<String,String>> getAreaAndColorWithDataRole(String province,String city,long userId){
        return dataRoleAddressMapper.getAreaAndColorWithDataRole(province,city,userId);
    }
    public List<Map<String,String>> getAreaWithDataRole(String province,String city,long userId){
        return dataRoleAddressMapper.getAreaWithDataRole(province,city,userId);
    }

    public List<Long> findAddressIdById(long id){
        return dataRoleAddressMapper.findAddressIdById(id);
    }
    public List<DataRoleAddress>findByCondition(long id){
        return dataRoleAddressMapper.findByCondition(id);
    }
    public List<DataRoleAddress> findByDataRoleName(String name){
         return dataRoleAddressMapper.findByDataRoleName(name);
    }
   public int delete(DataRoleAddress dataRoleAddress){
       return  dataRoleAddressMapper.delete(dataRoleAddress);
   }

    public void add(long dRoleId,long addressId,String name){
        dataRoleAddressMapper.add(dRoleId,addressId,name);
    }

    public List<Long> getAddressIdBydRoleName(String dRoleName){
        return dataRoleAddressMapper.getAddressIdBydRoleName(dRoleName);
    }

    public Map<String,String> transferProvinceMap(Map<String,String> map1,Map<String,String> map2){
        map1.put("id",map2.get("id"));
        map1.put("province",map2.get("province"));
        map1.put("color",map2.get("color"));
        map1.put("avgRiskValue",map2.get("avgRiskValue"));
        return map1;
    }
    public Map<String,String> transferCityMap(Map<String,String> map1,Map<String,String> map2){
        map1.put("id",map2.get("id"));
        map1.put("city",map2.get("city"));
        map1.put("color",map2.get("color"));
        map1.put("avgRiskValue",map2.get("avgRiskValue"));
        return map1;
    }
    public Map<String,String> transferAreaMap(Map<String,String> map1,Map<String,String> map2){
        map1.put("id",map2.get("id"));
        map1.put("area",map2.get("area"));
        map1.put("color",map2.get("color"));
        map1.put("avgRiskValue",map2.get("avgRiskValue"));
        return map1;
    }
    public Long getCraneNumberByProvince(String province){
        return dataRoleAddressMapper.getCraneNumberByProvince(province);
    }
    public Long getCraneNumberByCity(String province,String city){
        return dataRoleAddressMapper.getCraneNumberByCity(province,city);
    }
    public Long getCraneNumberByArea(String province,String city,String area){
        return dataRoleAddressMapper.getCraneNumberByArea(province,city,area);
    }
    public   List<Map<String,String>> getProvinceInfoWithDataRuleByCondition(Long userId,String equipmentVariety,String sTime,String eTime,float startValue,float endValue){
        List<String> addressIds=dataRoleAddressMapper.getProvinceByUserId(userId);
        List<Map<String,String>> list=new ArrayList<Map<String, String>>();
        for(String province:addressIds){
            Map<String,String> mm=dataRoleAddressMapper.getProvinceInfoWithDataRuleByCondition(province,equipmentVariety,sTime,eTime,startValue,endValue);
            list.add(mm);
        }
        return list;
    }
    public   List<Map<String,String>> getProvinceInfoWithDataRuleByCondition0(Long userId,String equipmentVariety,String sTime,String eTime,float startValue,float endValue){
        return dataRoleAddressMapper.getProvinceInfoWithDataRuleByCondition0(userId,equipmentVariety,sTime,eTime,startValue,endValue);
    }
    public List<Map<String,String>>getProvinceListWithDataRole(long userId){
           return dataRoleAddressMapper.getProvinceListWithDataRole(userId);
    }
    public void batchInsertToProvinceRiskValue(List<Map<String,String>> mapList){
           dataRoleAddressMapper.batchInsertToProvinceRiskValue(mapList);
    }
    public void updateProvinceRiskValue(String province,Float riskValue){
           dataRoleAddressMapper.updateProvinceRiskValue(province,riskValue);
    }
    public Map<String,String> validateProvinceRiskValueIsExistByProvince(String province){
           return dataRoleAddressMapper.validateProvinceRiskValueIsExistByProvince(province);
    }
    public int deleteProvinceRiskValue(String province){
          return dataRoleAddressMapper.deleteProvinceRiskValue(province);
    }
}
