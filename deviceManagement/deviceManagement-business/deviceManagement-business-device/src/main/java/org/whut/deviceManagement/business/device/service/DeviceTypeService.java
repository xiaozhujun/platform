package org.whut.deviceManagement.business.device.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.whut.deviceManagement.business.device.entity.DeviceType;
import org.whut.deviceManagement.business.device.mapper.DeviceTypeMapper;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Steven
 * Date: 14-10-10
 * Time: 上午11:21
 * To change this template use File | Settings | File Templates.
 */
public class DeviceTypeService {
    @Autowired
    private DeviceTypeMapper deviceTypeMapper;

    public void add(DeviceType deviceType){
        deviceTypeMapper.add(deviceType);
    }

    public int delete(DeviceType deviceType){
        return deviceTypeMapper.delete(deviceType);
    }

    public int update(DeviceType deviceType){
        return deviceTypeMapper.update(deviceType);
    }

    public int deleteById(long id){
        return  deviceTypeMapper.deleteById(id);
    }
    public Long getIdByName(String name,long appId) {
        return deviceTypeMapper.getIdByName(name,appId);  //To change body of created methods use File | Settings | File Templates.
    }

    public List<DeviceType> getListByAppId(Long appId) {
        return deviceTypeMapper.getListByAppId(appId);  //To change body of created methods use File | Settings | File Templates.
    }
}
