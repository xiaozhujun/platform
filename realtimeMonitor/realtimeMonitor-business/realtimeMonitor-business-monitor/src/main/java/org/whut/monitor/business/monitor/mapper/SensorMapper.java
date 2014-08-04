package org.whut.monitor.business.monitor.mapper;

import org.apache.ibatis.annotations.Param;
import org.whut.monitor.business.monitor.entity.Sensor;
import org.whut.platform.fundamental.orm.mapper.AbstractMapper;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: John
 * Date: 14-7-16
 * Time: 下午3:06
 * To change this template use File | Settings | File Templates.
 */
public interface SensorMapper extends AbstractMapper<Sensor>{
    public long getSensorId(@Param("groupName") String groupName,@Param("areaName") String areaName,@Param("collectorName") String collectorName,@Param("name") String name,@Param("number") String number,@Param("appId") long appId);
    public List<Map<String,String>> list(long appId);
    public List<Map<String,String>> listByGroupCollectionAndMonitor(@Param("appId") long appId,@Param("group") String group,@Param("collector") String collector,@Param("monitor") String monitor);
    public int deleteById(long id);
    public List<Map<String,String>> homePageList(@Param("fStatus")String fStatus,@Param("appId")long appId);
    public void updateWarnCount(@Param("warnCount")long warnCount,@Param("id")long id,@Param("appId")long appId);
    public List<Sensor> getSensorsByCollectorId(@Param("collectorId") long collectorId,@Param("appId") long appId);
    public List<String> getNumberBySensorId(@Param("sensorId")long sensorId,@Param("appId")long appId);
    public Map getWarnConditionByNumber(@Param("number")String number);
    public void updateWarnCountByNumber(@Param("number")String number,@Param("warnCount")long warnCount);
    public Map findByNumber(String number);
}
