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
    public List<Map<String,String>> getProvinceAndColorWithDataRole(String userName){
        return dataRoleAddressMapper.getProvinceAndColorWithDataRole(userName);
    }
    public List<Map<String,String>> getCityAndColorWithDataRole(String province,String userName){
        return dataRoleAddressMapper.getCityAndColorWithDataRole(province,userName);
    }
    public List<Map<String,String>> getAreaAndColorWithDataRole(String province,String city,String userName){
        return dataRoleAddressMapper.getAreaAndColorWithDataRole(province,city,userName);
    }

}
