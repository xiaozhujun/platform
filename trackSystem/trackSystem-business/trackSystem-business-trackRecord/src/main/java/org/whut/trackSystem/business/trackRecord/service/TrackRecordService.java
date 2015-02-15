package org.whut.trackSystem.business.trackRecord.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.whut.trackSystem.business.trackRecord.entity.TrackRecord;
import org.whut.trackSystem.business.trackRecord.mapper.TrackRecordMapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: yangyang
 * Date: 15-2-10
 * Time: 下午8:26
 * To change this template use File | Settings | File Templates.
 */
public class TrackRecordService {
    @Autowired
    private TrackRecordMapper trackRecordMapper;

    public List<TrackRecord> getListByAppId(Long appId) {
        return trackRecordMapper.getListByAppId(appId);
    }

    public void add(TrackRecord trackRecord) {
        trackRecordMapper.add(trackRecord);
    }

    public int update(TrackRecord trackRecord) {
        return trackRecordMapper.update(trackRecord);
    }

    public Long getIdByDeviceNumAndStartTime(String deviceNum,Date startTime,Long appId) {
        return trackRecordMapper.getIdByDeviceNumAndStartTime(deviceNum,startTime,appId);
    }

    public List<Map<String,String>> getListByCondition(String sTime,String eTime,Long deviceId,Long appId) {
        return trackRecordMapper.getListByCondition(sTime,eTime,deviceId,appId);
    }
}
