package org.whut.trackSystem.business.device.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.whut.trackSystem.business.device.entity.DeviceType;
import org.whut.trackSystem.business.device.mapper.DeviceTypeMapper;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: yangyang
 * Date: 14-12-15
 * Time: 下午4:19
 * To change this template use File | Settings | File Templates.
 */
public class DeviceTypeService {
    @Autowired
    private DeviceTypeMapper deviceTypeMapper;

    public List<DeviceType> getListByAppId(long appId) {
        return deviceTypeMapper.getListByAppId(appId);
    }

    public Long getIdByNameAndAppId(String name,Long appId) {
        return deviceTypeMapper.getIdByNameAndAppId(name, appId);
    }

    public void add(DeviceType deviceType) {
        deviceTypeMapper.add(deviceType);
    }

    public int update(DeviceType deviceType) {
        return deviceTypeMapper.update(deviceType);
    }

    public int delete(DeviceType deviceType) {
        return deviceTypeMapper.delete(deviceType);
    }
}
