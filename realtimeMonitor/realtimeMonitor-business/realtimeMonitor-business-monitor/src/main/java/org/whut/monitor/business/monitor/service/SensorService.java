package org.whut.monitor.business.monitor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.whut.monitor.business.monitor.entity.Sensor;
import org.whut.monitor.business.monitor.mapper.AreaMapper;
import org.whut.monitor.business.monitor.mapper.GroupMapper;
import org.whut.monitor.business.monitor.mapper.SensorMapper;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: John
 * Date: 14-7-16
 * Time: 下午3:06
 * To change this template use File | Settings | File Templates.
 */
public class SensorService {
    @Autowired
    private SensorMapper sensorMapper;
    @Autowired
    private AreaMapper areaMapper;
    @Autowired
    private GroupMapper groupMapper;
    public Long getSensorId(String number,long appId){
         return sensorMapper.getSensorId(number,appId);
    }
    public void add(Sensor sensor){
         sensorMapper.add(sensor);
    }
    public List<Map<String,String>> list(long appId){
         return sensorMapper.list(appId);
    }
    public List<Map<String,String>> getListByGroupAreaAndMonitor(long appId,String group,String areaName,String monitor){
        return sensorMapper.getListByGroupAreaAndMonitor(appId,group,areaName,monitor);
    }

       public int deleteById(long id){
       return sensorMapper.deleteById(id);
    }

    public int update(Sensor sensor){
        return sensorMapper.update(sensor);
    }

    public List<Sensor>getSensorsByAreaId(long areaId,long appId){
        return sensorMapper.getSensorsByAreaId(areaId,appId);
    }

    public List<Map<String,String>> homePageList(String fStatus,long appId) {
        return sensorMapper.homePageList(fStatus,appId);
    }

    public void updateWarnCount(long warnCount,long id,long appId) {
        sensorMapper.updateWarnCount(warnCount,id,appId);
    }

    public List<String> getNumberBySensorId(long sensorId,long appId) {
        return sensorMapper.getNumberBySensorId(sensorId,appId);
    }

    public Map getWarnConditionByNumber(String number) {
        return sensorMapper.getWarnConditionByNumber(number);
    }

    public void updateWarnCountByNumber(String number,long warnCount) {
        sensorMapper.updateWarnCountByNumber(number,warnCount);
    }

    public Map findByNumber(String number) {
        return sensorMapper.findByNumber(number);
    }
    public List<Map<String,String>> getSensorIdAndNumbersByName(String name){
        return sensorMapper.getSensorIdAndNumbersByName(name);
    }
    public List<Map<String,String>> getCollectorNameBySensorNumber(long number){
        return sensorMapper.getCollectorNameBySensorNumber(number);
    }

    public long getDataTypeByIdAndAppId(long id,long appId) {
        return sensorMapper.getDataTypeByIdAndAppId(id, appId);
    }
    public String getCNumBySNum(String sNum){
        return sensorMapper.getCNumBySNum(sNum);
    }

    public List<String> getSensorNumByCNum(String CNum) {
        return sensorMapper.getSensorNumByCNum(CNum);
    }

    public Long  getAppIdBySNum(String sNum) {
        return sensorMapper.getAppIdBySNum(sNum);
    }

    public String  getGroupNameBySNum(String sNum) {
        Long id=sensorMapper.getGroupIdBySNum(sNum);
         return groupMapper.getNameById(id);

    }
    public List<Map<String,String>> getListByGroupName(long appId,String group){
        return sensorMapper.getListByGroupName(appId,group);
    }
    public List<Sensor>getSensorsByAreaName(String areaName,String groupName,long appId){
        return sensorMapper.getSensorsByAreaName(areaName,groupName,appId);
    }
    public String  getAreaNameBySNum(String sNum) {
        return areaMapper.getAreaNameBySNum(sNum);
    }
    public long getDataTypeBySNumAndAppId(String number,long appId) {
        return sensorMapper.getDataTypeBySNumAndAppId(number, appId);
    }
}
