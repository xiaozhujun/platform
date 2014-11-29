package org.whut.rentManagement.business.device.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.whut.rentManagement.business.device.entity.Device;
import org.whut.rentManagement.business.device.mapper.DeviceMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Steven
 * Date: 14-10-10
 * Time: 上午11:17
 * To change this template use File | Settings | File Templates.
 */
public class DeviceService {
    @Autowired
    private DeviceMapper deviceMapper;

    public void add(Device device){
        deviceMapper.add(device);
    }

    public int delete(Device device){
        return deviceMapper.delete(device);
    }

    public int update(Device device){
        return deviceMapper.update(device);
    }

    public Long getIdByNumber(String number, Long appId) {
        return deviceMapper.getIdByNumber(number,appId);
    }
    public int deleteById(long id){
        return deviceMapper.deleteById(id);
    }

    public List<Map<String,String>> getListByAppId(long appId) {
        return deviceMapper.getListByAppId(appId);
    }

    public List<Map<String,String>> getMainDeviceListByAppId(long appId) {
        return deviceMapper.getMainDeviceListByAppId(appId);
    }

    public List<Map<String,String>> findByCondition(Map<String,Object> condition){
        return deviceMapper.findByCondition(condition);
    }

    public ArrayList<Long> findMainDeviceList(long appId,List<String> deviceIdList){
        return deviceMapper.findMainDeviceList(appId,deviceIdList);
    }

    public void installDevice(long mainDeviceId,List<String> deviceIdList){
        deviceMapper.installDevice(mainDeviceId,deviceIdList);
    }

    public void removeDevice(long mainDeviceId,List<String> deviceIdList){
        deviceMapper.removeDevice(mainDeviceId,deviceIdList);
    }
}
