package org.whut.deviceManagement.business.maintain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.whut.deviceManagement.business.maintain.entity.DeviceMaintain;
import org.whut.deviceManagement.business.maintain.mapper.DeviceMaintainMapper;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: xiaozhujun
 * Date: 15-4-25
 * Time: 下午11:59
 * To change this template use File | Settings | File Templates.
 */
public class DeviceMaintainService {


    @Autowired
    private DeviceMaintainMapper deviceMaintainMapper;

    public void add(DeviceMaintain deviceMaintain){
        deviceMaintainMapper.add(deviceMaintain);
    }

    public int delete(DeviceMaintain deviceMaintain){
        return deviceMaintainMapper.delete(deviceMaintain);
    }

    public int update(DeviceMaintain deviceMaintain){
        return deviceMaintainMapper.update(deviceMaintain);
    }

    public int deleteById(long id){
        return deviceMaintainMapper.deleteById(id);
    }

    public List<Map<String,String>> getListByAppId(long appId) {
        return deviceMaintainMapper.getListByAppId(appId);
    }

    public HashMap<String,List<Map<String,String>>>  getLastDeviceMaintainByDeviceGroup(long appId){
        List<Map<String,String>> list = deviceMaintainMapper.getListByAppId(appId);
        HashMap<String,List<Map<String,String>>> resultMap = new HashMap<String,List<Map<String,String>>>();
        for(Map<String,String> deviceMaintain:list){
            if(resultMap.containsKey(deviceMaintain.get("deviceName"))){
                resultMap.get(deviceMaintain.get("deviceName")).add(deviceMaintain);
            }else{
                List<Map<String,String>> temp = new ArrayList<Map<String,String>>();
                temp.add(deviceMaintain);
                resultMap.put(deviceMaintain.get("deviceName"),temp);
            }
        }
        return resultMap;
    }
}
