package org.whut.rentManagement.business.device.mapper;

import org.whut.platform.fundamental.orm.mapper.AbstractMapper;
import org.whut.rentManagement.business.device.entity.DeviceType;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Steven
 * Date: 14-10-10
 * Time: 上午11:21
 * To change this template use File | Settings | File Templates.
 */
public interface DeviceTypeMapper extends AbstractMapper<DeviceType> {
    public long getIdByName(String name, long appId);

    public List<DeviceType> getListByAppId(Long appId);
}
