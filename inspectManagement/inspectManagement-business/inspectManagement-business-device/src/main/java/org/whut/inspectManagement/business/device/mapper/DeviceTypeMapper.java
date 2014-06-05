package org.whut.inspectManagement.business.device.mapper;

import org.apache.ibatis.annotations.Param;
import org.whut.inspectManagement.business.device.entity.DeviceType;
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
public interface DeviceTypeMapper extends AbstractMapper<DeviceType> {
    public List<DeviceType> findByCondition(Map<String,Object> map);
    public String getNameById(long id);
    public List<DeviceType> getListByAppId(long appId);
    public long getIdByName(@Param("name") String name, @Param("appId") long appId);

}
