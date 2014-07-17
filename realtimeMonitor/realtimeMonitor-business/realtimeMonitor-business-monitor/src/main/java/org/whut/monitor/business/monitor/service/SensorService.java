package org.whut.monitor.business.monitor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.whut.monitor.business.monitor.entity.Sensor;
import org.whut.monitor.business.monitor.mapper.SensorMapper;

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
    public long getSensorIdByNameAndNumber(String name,String number,long appId){
         return sensorMapper.getSensorIdByNameAndNumber(name,number,appId);
    }
    public void add(Sensor sensor){
         sensorMapper.add(sensor);
    }
}
