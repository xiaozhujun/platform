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
    public Long getSensorId(@Param("number") String number, @Param("appId") long appId);
    public List<Map<String,String>> list(long appId);
    public List<Map<String,String>> getListByGroupAreaAndMonitor(@Param("appId") long appId, @Param("group") String group, @Param("areaName") String areaName, @Param("monitor") String monitor);
    public int deleteById(long id);
    public List<Map<String,String>> homePageList(@Param("fStatus") String fStatus, @Param("appId") long appId);
    public void updateWarnCount(@Param("warnCount") long warnCount, @Param("id") long id, @Param("appId") long appId);
    public List<Sensor> getSensorsByAreaId(@Param("areaId") long areaId, @Param("appId") long appId);
    public List<String> getNumberBySensorId(@Param("sensorId") long sensorId, @Param("appId") long appId);
    public Map getWarnConditionByNumber(@Param("number") String number);
    public void updateWarnCountByNumber(@Param("number") String number, @Param("warnCount") long warnCount);
    public Map findByNumber(String number);
    public List<Sensor> getSensorsByAreaName(@Param("areaName") String areaName, @Param("groupName") String groupName, @Param("appId") long appId);

    public List<Map<String,String>> getSensorIdAndNumbersByName(@Param("name") String name);   //名字可重复，故用复数
    public List<Map<String,String>> getCollectorNameBySensorNumber(@Param("number") long number);
    public Long getDataTypeByIdAndAppId(@Param("id") long id, @Param("appId") long appId);
    public String getCNumBySNum(String sNum);
    public List<String> getSensorNumByCNum(String CNum);
    public Long getAppIdBySNum(String sNum);
    public Long getGroupIdBySNum(String sNum);
    public List<Map<String,String>> getListByGroupName(@Param("appId") long appId, @Param("group") String group);
    public Long getDataTypeBySNumAndAppId(@Param("number") String number, @Param("appId") long appId);
}
