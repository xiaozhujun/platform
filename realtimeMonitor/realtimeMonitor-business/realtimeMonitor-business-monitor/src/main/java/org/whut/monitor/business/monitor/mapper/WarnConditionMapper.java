package org.whut.monitor.business.monitor.mapper;

import org.apache.ibatis.annotations.Param;
import org.whut.monitor.business.monitor.entity.WarnCondition;
import org.whut.platform.fundamental.orm.mapper.AbstractMapper;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: yangyang
 * Date: 14-7-31
 * Time: 上午9:12
 * To change this template use File | Settings | File Templates.
 */
public interface WarnConditionMapper extends AbstractMapper<WarnCondition>{
    public List<WarnCondition> getListByAppId(@Param("warnType")String warnType,@Param("groupName")String groupName,@Param("areaName")String areaName,@Param("collectorName")String collectorName,@Param("sensorName")String sensorName,
                                              @Param("number")String number,@Param("sTime")String sTime,@Param("eTime")String eTime);
}
