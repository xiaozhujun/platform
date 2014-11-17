package org.whut.platform.business.address.mapper;

import org.apache.ibatis.annotations.Param;
import org.whut.platform.business.address.entity.Address;
import org.whut.platform.fundamental.orm.mapper.AbstractMapper;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: xiaozhujun
 * Date: 14-2-6
 * Time: 下午9:30
 * To change this template use File | Settings | File Templates.
 */
public interface AddressMapper extends AbstractMapper {
    public Address findById(long id);
    public Address findByArea(String area);
    public Long findIdByArea(@Param("province") String province,@Param("city")String city,@Param("area")String area);
    public List<Long> findIdByProvinceCity(@Param("province") String province,@Param("city")String city);
    public List<Long> findIdByProvince(String province);
    public Long findIdByCityArea(@Param("city")String city,@Param("area")String area);
    public List<Address> getProvinceList();
    public List<Address> list();
    public List<Address> getCityByProvince(String province);
    public List<Address> getAreaByCity(String city);
    public List<Address> getAreaByProvinceAndCity(@Param("province")String province,@Param("city")String city);
    public List<Address> getProvinceWithDataRule(@Param("userName")String userName);
    public List<Address> getCityWithDataRule(@Param("province") String province,@Param("userName")String userName);
    public List<Address> getAreaWithDataRule(@Param("province")String province,@Param("city")String city,@Param("userName")String userName);

    public String findProvinceById(long id);
    public String findCityById(long id);
    public String findAreaById(long id);

    public List<Address>getAddressInfoByAddressId();
    public List<Address>getProvinceCity();
    public List<Address>getProvince();
    public List<Address> getId();
}
