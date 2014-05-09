package org.whut.platform.business.datarule.mapper;

import org.apache.ibatis.annotations.Param;
import org.whut.platform.business.datarule.entity.DataRoleAddress;
import org.whut.platform.fundamental.orm.mapper.AbstractMapper;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: John
 * Date: 14-4-21
 * Time: 下午2:39
 * To change this template use File | Settings | File Templates.
 */

public interface DataRoleAddressMapper extends AbstractMapper<DataRoleAddress>{
    public List<Map<String,String>> getProvinceAndColorWithDataRole(@Param("userId")long userId);
    public List<Map<String,String>> getCityAndColorWithDataRole(@Param("province") String province,@Param("userId")long userId);
    public List<Map<String,String>> getAreaAndColorWithDataRole(@Param("province")String province,@Param("city")String city,@Param("userId")long userId);

    public List<Long>findAddressIdById(long id);
    public List<DataRoleAddress>findByCondition(long id);
    public List<DataRoleAddress> findByDataRoleName(String name);
    public int delete(DataRoleAddress dataRoleAddress);
    public void add(long dRoleId,long addressId,String name);
    public List<Long> getAddressIdBydRoleName(String dRoleName);

    public Long getCraneNumberByProvince(String province);
    public Long getCraneNumberByCity(@Param("province")String province,@Param("city")String city);
    public Long getCraneNumberByArea(@Param("province")String province,@Param("city")String city,@Param("area")String area);
}
