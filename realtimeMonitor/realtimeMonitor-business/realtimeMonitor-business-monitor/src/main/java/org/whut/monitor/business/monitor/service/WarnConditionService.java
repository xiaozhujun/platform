package org.whut.monitor.business.monitor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.whut.monitor.business.monitor.entity.WarnCondition;
import org.whut.monitor.business.monitor.mapper.WarnConditionMapper;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: yangyang
 * Date: 14-7-31
 * Time: 上午9:15
 * To change this template use File | Settings | File Templates.
 */
public class WarnConditionService {
    @Autowired
    private WarnConditionMapper warnConditionMapper;

    public List<WarnCondition> getListByAppId(String warnType,String groupName,String areaName,String collectorName,String sensorName,String number,String sTime,String eTime) {
        return warnConditionMapper.getListByAppId(warnType,groupName,areaName,collectorName,sensorName,number,sTime,eTime);
    }

    public void add(WarnCondition warnCondition) {
        warnConditionMapper.add(warnCondition);
    }
}
