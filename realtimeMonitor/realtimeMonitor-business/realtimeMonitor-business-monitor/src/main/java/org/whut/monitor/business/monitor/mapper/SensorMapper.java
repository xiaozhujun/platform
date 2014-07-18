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
    public long getSensorIdByNameAndNumber(@Param("name") String name,@Param("number") String number,@Param("appId") long appId);
    public List<Map<String,String>> list(long appId);
    public int deleteById(long id);
}
