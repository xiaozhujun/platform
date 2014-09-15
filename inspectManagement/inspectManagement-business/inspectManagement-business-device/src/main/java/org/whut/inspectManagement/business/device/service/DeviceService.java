package org.whut.inspectManagement.business.device.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.whut.inspectManagement.business.device.entity.Device;
import org.whut.inspectManagement.business.device.mapper.DeviceMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-5-15
 * Time: 下午3:42
 * To change this template use File | Settings | File Templates.
 */
public class DeviceService {
    @Autowired
    private DeviceMapper deviceMapper;

    public void add(Device device){
        deviceMapper.add(device);
    }

    public List<Device> list(){
      return  deviceMapper.findByCondition(new HashMap<String, Object>());
    }

    public List<Device> getListByAppId(long appId)
    {
        return deviceMapper.getListByAppId(appId);
    }

    public Device get(long id){
        return deviceMapper.get(id);
    }

    public void update(Device device){
        deviceMapper.update(device);
    }

    public void delete(Device device){
        deviceMapper.delete(device);
    }
    public Long getIdByNumber(String number,long appId) {
        return deviceMapper.getIdByNumber(number,appId);
    }
    public List<Map<String,String>> getListByCondition(String deviceType,String deviceNumber,String tagName,long appId){
       return deviceMapper.getListByCondition(deviceType,deviceNumber,tagName,appId);
    }
    public List<Map<String,Object>> getListByTagId(long tagId){
        return deviceMapper.getListByTagId(tagId);
    }

    public String getNameById(long id)
    {
        return deviceMapper.getNameById(id);
    }

    public Long getIdByName(String name,long appId)
    {
        return deviceMapper.getIdByName(name,appId);
    }
    public List<Device> getInfoByCondition(String name,String number,long deviceTypeId,long appId){
        return  deviceMapper.getInfoByCondition(name,number,deviceTypeId,appId);
    }

    public void updateImage(Device device){
         deviceMapper.updateImage(device);
    }
}
