package org.whut.platform.business.address.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.whut.platform.business.address.entity.Address;
import org.whut.platform.business.address.mapper.AddressMapper;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: xiaozhujun
 * Date: 14-2-6
 * Time: 下午9:31
 * To change this template use File | Settings | File Templates.
 */
public class AddressService {

    @Autowired
    private AddressMapper mapper;

    public Address findById(long id){
        return mapper.findById(id);
    }

    public Address findByArea(String area){
        if(area==null || area.trim().equals("")){
            return null;
        }
        return mapper.findByArea(area);
    }
    public Long findIdByArea(String province,String city,String area){
          if(province==null||province.trim().equals("")||city==null||city.trim().equals("")||area==null||area.trim().equals("")){
              return null;
          }
        return mapper.findIdByArea(province,city,area);
    }
    public Long findIdByCityArea(String city,String area){
        if(city==null||city.trim().equals("")||area==null||area.trim().equals("")){
            return  null;
        }
        return mapper.findIdByCityArea(city,area);
    }

    public List<Address> getProvinceList()
    {
        return mapper.getProvinceList();
    }
    public List<Address> list()
    {
        return  mapper.list();
    }
    public List<Address> getCityByProvince(String province)
    {
        return mapper.getCityByProvince(province);
    }
    public List<Address> getAreaByCity(String city)
    {
        return mapper.getAreaByCity(city);
    }
    public List<Address> getAreaByProvinceAndCity(String province,String city){
        return mapper.getAreaByProvinceAndCity(province,city);
    }

}
