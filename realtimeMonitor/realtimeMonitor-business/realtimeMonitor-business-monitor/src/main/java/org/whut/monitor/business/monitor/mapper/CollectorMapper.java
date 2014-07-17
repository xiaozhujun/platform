package org.whut.monitor.business.monitor.mapper;

import org.apache.ibatis.annotations.Param;
import org.whut.monitor.business.monitor.entity.Collector;
import org.whut.platform.fundamental.orm.mapper.AbstractMapper;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-7-15
 * Time: 下午8:54
 * To change this template use File | Settings | File Templates.
 */
public interface CollectorMapper extends AbstractMapper<Collector>{
    public List<Map<String,String>> getListByAppId(long appId);
    public long getCollectorId(@Param("name") String name,@Param("number") String number,@Param("appId")long appId);
    public List<Collector> getCollectorByAreaId(long areaId);
    public long getIdByNameAndAppId(@Param("name") String name,@Param("appId") long appId);
}
