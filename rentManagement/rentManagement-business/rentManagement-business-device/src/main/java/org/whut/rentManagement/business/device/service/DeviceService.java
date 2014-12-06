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

    public List<Map<String,Object>> findByCondition(Map<String,Object> condition){
        return deviceMapper.findByCondition(condition);
    }

    public ArrayList<Long> findMainDeviceList(long appId,List<String> deviceIdList){
        return deviceMapper.findMainDeviceList(appId,deviceIdList);
    }

    public void installDevice(long appId,long mainDeviceId,List<String> deviceIdList){
        deviceMapper.installDevice(appId,mainDeviceId,deviceIdList);
    }

    public void removeDevice(long appId,List<String> deviceIdList){
        deviceMapper.removeDevice(appId,deviceIdList);
    }

    public void transportDevice(long appId,List<String> deviceIdList){
        deviceMapper.transportDevice(appId,deviceIdList);
    }

    public void stockIn(long appId,List<String> deviceIdList){
        deviceMapper.stockIn(appId,deviceIdList);
    }

    public void stockOut(long appId,List<String> deviceIdList){
        deviceMapper.stockOut(appId,deviceIdList);
    }

    public Map<String,Object> getMainDeviceInfo(Map<String,Object> condition){
        return deviceMapper.getMainDeviceInfo(condition);
    }

    public List<Map<String,Object>> listByMainDeviceId(Map<String,Object> condition){
        return deviceMapper.listByMainDeviceId(condition);
    }

    public List<Map<String,Object>> findMainDeviceByCondition(Map<String,Object> condition){
        return deviceMapper.findMainDeviceByCondition(condition);
    }
}
