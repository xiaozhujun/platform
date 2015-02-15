package org.whut.trackSystem.business.device.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.whut.trackSystem.business.device.entity.Device;
import org.whut.trackSystem.business.device.mapper.DeviceMapper;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: yangyang
 * Date: 14-12-15
 * Time: 下午4:14
 * To change this template use File | Settings | File Templates.
 */
public class DeviceService {
    @Autowired
    private DeviceMapper deviceMapper;

    public void add(Device device) {
        deviceMapper.add(device);
    }

    public int update(Device device) {
        return deviceMapper.update(device);
    }

    public int delete(Device device) {
        return deviceMapper.delete(device);
    }

    public List<Device> getListByAppId(Long appId) {
        return deviceMapper.getListByAppId(appId);
    }

    public Long getAppIdByDeviceNum(String deviceNum) {
        return deviceMapper.getAppIdByDeviceNum(deviceNum);
    }

    public Long getIdByNumber(String number,Long appId) {
        return deviceMapper.getIdByNumber(number, appId);
    }

    public List<Map<String,String>> findByCondition(Long appId) {
        return deviceMapper.findByCondition(appId);
    }

    public List<Map<String,String>> getDeviceByCondition(Long groupId,Long userId,Long appId) {
        return deviceMapper.getDeviceByCondition(groupId,userId,appId);
    }

}
