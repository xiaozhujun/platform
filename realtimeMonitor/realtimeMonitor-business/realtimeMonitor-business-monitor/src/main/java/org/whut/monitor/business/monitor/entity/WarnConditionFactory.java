package org.whut.monitor.business.monitor.entity;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: yangyang
 * Date: 14-8-8
 * Time: 上午12:55
 * To change this template use File | Settings | File Templates.
 */
public class WarnConditionFactory {
    public static WarnCondition create(String groupName,String areaName,String collectorName,
                                       String sensorName,Date warnTime,String number,double curWarnValue) {
        WarnCondition warnCondition = new WarnCondition();
        warnCondition.setGroupName(groupName);
        warnCondition.setAreaName(areaName);
        warnCondition.setCollectorName(collectorName);
        warnCondition.setSensorName(sensorName);
        warnCondition.setWarnTime(warnTime);
        warnCondition.setNumber(number);
        warnCondition.setCurWarnValue(curWarnValue);
        warnCondition.setAppId(1);
        return warnCondition;
    }
}
