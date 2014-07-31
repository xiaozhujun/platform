package org.whut.monitor.business.monitor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.whut.monitor.business.monitor.mapper.WarnRecordMapper;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: yangyang
 * Date: 14-7-30
 * Time: 下午3:04
 * To change this template use File | Settings | File Templates.
 */
public class WarnRecordService {
    @Autowired
    private WarnRecordMapper warnRecordMapper;

    public List<WarnRecord> getListByAppId(String groupName,String areaName,String collectorName,String sensorName,String number,long appId) {
        return warnRecordMapper.getListByAppId(groupName,areaName,collectorName,sensorName,number,appId);
    }

    public void add(WarnRecord warnRecord) {
        warnRecordMapper.add(warnRecord);
    }

}
