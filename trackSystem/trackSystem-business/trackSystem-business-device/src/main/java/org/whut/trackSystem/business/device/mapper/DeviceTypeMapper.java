package org.whut.trackSystem.business.device.mapper;

import org.apache.ibatis.annotations.Param;
import org.whut.platform.fundamental.orm.mapper.AbstractMapper;
import org.whut.trackSystem.business.device.entity.DeviceType;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: yangyang
 * Date: 14-12-15
 * Time: 下午4:19
 * To change this template use File | Settings | File Templates.
 */
public interface DeviceTypeMapper extends AbstractMapper<DeviceType> {
    public List<DeviceType> getListByAppId(long appId);
    public Long getIdByNameAndAppId(@Param("name")String name,@Param("appId")Long appId);
}
