package org.whut.trackSystem.business.trackRecord.mapper;

import org.apache.ibatis.annotations.Param;
import org.whut.platform.fundamental.orm.mapper.AbstractMapper;
import org.whut.trackSystem.business.trackRecord.entity.TrackRecord;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: yangyang
 * Date: 15-2-10
 * Time: 下午8:25
 * To change this template use File | Settings | File Templates.
 */
public interface TrackRecordMapper extends AbstractMapper<TrackRecord> {
    public List<TrackRecord> getListByAppId(Long appId);
    public Long getIdByDeviceNumAndStartTime(@Param("deviceNum")String deviceNum,@Param("startTime")Date startTime,
                                             @Param("appId")Long appId);
    public List<Map<String,String>> getListByCondition(@Param("sTime")String sTime,@Param("eTime")String eTime,
                                                       @Param("deviceId")Long deviceId,@Param("appId")Long appId);
}
