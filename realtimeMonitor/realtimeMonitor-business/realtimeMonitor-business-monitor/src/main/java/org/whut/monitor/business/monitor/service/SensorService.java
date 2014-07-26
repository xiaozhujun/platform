package org.whut.monitor.business.monitor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.whut.monitor.business.monitor.entity.Sensor;
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
    public long getSensorId(String groupName,String areaName,String collectorName,String name,String number,long appId){
         return sensorMapper.getSensorId(groupName,areaName,collectorName,name,number,appId);
    }
    public void add(Sensor sensor){
         sensorMapper.add(sensor);
    }
    public List<Map<String,String>> list(long appId){
         return sensorMapper.list(appId);
    }
    public List<Map<String,String>> listByGroupCollectionAndMonitor(long appId,String group,String collector,String monitor){
        return sensorMapper.listByGroupCollectionAndMonitor(appId,group,collector,monitor);
    }

    public int deleteById(long id){
       return sensorMapper.deleteById(id);
    }

    public int update(Sensor sensor){
        return sensorMapper.update(sensor);
    }

    public List<Sensor> getSensorsByCollectorId(long collectorId,long appId){
        return sensorMapper.getSensorsByCollectorId(collectorId,appId);
    }
}
