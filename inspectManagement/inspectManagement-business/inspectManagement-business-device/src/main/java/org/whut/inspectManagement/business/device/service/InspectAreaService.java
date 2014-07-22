package org.whut.inspectManagement.business.device.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.whut.inspectManagement.business.device.entity.InspectArea;
import org.whut.inspectManagement.business.device.mapper.InspectAreaMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-5-19
 * Time: 上午11:43
 * To change this template use File | Settings | File Templates.
 */
public class InspectAreaService {
    @Autowired
    private InspectAreaMapper inspectAreaMapper;
    public  void add(InspectArea inspectArea){
        inspectAreaMapper.add(inspectArea);
    }

    public int delete(InspectArea inspectArea){
        return inspectAreaMapper.delete(inspectArea);
    }

    public int update(InspectArea inspectArea){
        return  inspectAreaMapper.update(inspectArea);
    }

    public List<InspectArea> list(){
        return inspectAreaMapper.findByCondition(new HashMap<String, Object>());
    }

    public long findIdByName(String name){
        return inspectAreaMapper.findIdByName(name);
    }

    public long getTypeIdByAreaId(long id){
        return inspectAreaMapper.getTypeIdByAreaId(id);
    }
    public long getInspectAreaIdByNames(String areaName,String deviceTypeName,long appId) {
        return inspectAreaMapper.getInspectAreaIdByAreaNameAndDeviceTypeName(areaName,deviceTypeName,appId);
    }
    public String getDeviceTypeById(long inspectAreaId){
        return inspectAreaMapper.getDeviceTypeById(inspectAreaId);
    }
    public String getAreaById(long inspectAreaId){
        return inspectAreaMapper.getAreaById(inspectAreaId);
    }

    public List<InspectArea> getInspectAreaByDeviceTypeId(long deviceTypeId){
        return inspectAreaMapper.getInspectAreaByDeviceTypeId(deviceTypeId);
    }

    public List<InspectArea> getListByAppId(long appId)
    {
        return inspectAreaMapper.getListByAppId(appId);
    }

    public long getIdByName(String name,long appId)
    {
        return inspectAreaMapper.getIdByName(name,appId);
    }
    public long getIdByNumber(String number,long appId)
    {
        return inspectAreaMapper.getIdByNumber(number,appId);
    }

    public List<Map<String,String>> getAreaNameByAppId(long appId){
        return inspectAreaMapper.getAreaNameByAppId(appId);
    }
}