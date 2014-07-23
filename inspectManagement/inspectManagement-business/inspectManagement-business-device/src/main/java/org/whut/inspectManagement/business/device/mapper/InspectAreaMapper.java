package org.whut.inspectManagement.business.device.mapper;

import org.apache.ibatis.annotations.Param;
import org.whut.inspectManagement.business.device.entity.InspectArea;
import org.whut.platform.fundamental.orm.mapper.AbstractMapper;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-5-19
 * Time: 上午11:39
 * To change this template use File | Settings | File Templates.
 */
public interface InspectAreaMapper extends AbstractMapper<InspectArea> {
    public List<InspectArea> findByCondition(Map<String,Object> map);
    public Long findIdByName(String name);
    public Long getTypeIdByAreaId(long id);
    public String getAreaById(long id);
    public String getDeviceTypeByAreaId(long inspectAreaId);
    public long getInspectAreaIdByAreaNameAndDeviceTypeName(@Param("areaName") String areaName,@Param("deviceTypeName") String deviceTypeName,@Param("appId") long appId);
    public String getDeviceTypeById(long inspectAreaId);
    public List<InspectArea> getInspectAreaByDeviceTypeId(long deviceTypeId);
    public List<InspectArea> getListByAppId(long appId);
    public long getIdByName(@Param("name") String name, @Param("appId") long appId);
    public long getIdByNumber(@Param("number") String number, @Param("appId") long appId);
    public List<Map<String,String>> getAreaNameByAppId(long appId);
}