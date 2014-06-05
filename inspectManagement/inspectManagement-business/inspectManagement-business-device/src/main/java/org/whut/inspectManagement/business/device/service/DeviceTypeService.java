package org.whut.inspectManagement.business.device.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.whut.inspectManagement.business.device.entity.DeviceType;
import org.whut.inspectManagement.business.device.mapper.DeviceTypeMapper;

import java.util.HashMap;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-5-19
 * Time: 上午11:42
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
    public List<DeviceType> list(){
        return deviceTypeMapper.findByCondition(new HashMap<String, Object>());
    }
    public String getNameById(long id){
        return deviceTypeMapper.getNameById(id);
    }
    public List<DeviceType> getListByAppId(long appId)
    {
        return deviceTypeMapper.getListByAppId(appId);
    }

    public long getIdByName(String name,long appId)
    {
        return deviceTypeMapper.getIdByName(name,appId);
    }
}
