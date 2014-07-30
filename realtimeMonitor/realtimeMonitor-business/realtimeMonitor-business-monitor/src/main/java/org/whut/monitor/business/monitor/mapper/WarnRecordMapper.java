package org.whut.monitor.business.monitor.mapper;

import org.apache.ibatis.annotations.Param;
import org.whut.monitor.business.monitor.entity.WarnRecord;
import org.whut.platform.fundamental.orm.mapper.AbstractMapper;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: yangyang
 * Date: 14-7-30
 * Time: 下午3:03
 * To change this template use File | Settings | File Templates.
 */
public interface WarnRecordMapper extends AbstractMapper {

    public List<WarnRecord> getListByAppId(@Param("groupName")String groupName,@Param("areaName")String areaName,@Param("collectorName")String collectorName,
                                           @Param("sensorName")String sensorName,@Param("number")String number,@Param("appId")long appId);
}
