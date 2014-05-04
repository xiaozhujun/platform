package org.whut.platform.business.datarule.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.whut.platform.business.datarule.entity.DataRoleAddress;

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
        return dataRoleAddressMapper.getProvinceAndColorWithDataRole(userId);
    }
    public List<Map<String,String>> getCityAndColorWithDataRole(String province,long userId){
        return dataRoleAddressMapper.getCityAndColorWithDataRole(province,userId);
    }
    public List<Map<String,String>> getAreaAndColorWithDataRole(String province,String city,long userId){
        return dataRoleAddressMapper.getAreaAndColorWithDataRole(province,city,userId);
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
}
