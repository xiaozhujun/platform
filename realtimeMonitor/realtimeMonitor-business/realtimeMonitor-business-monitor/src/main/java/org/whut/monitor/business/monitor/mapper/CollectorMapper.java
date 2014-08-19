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
    public List<Collector> getCollector();
    public List<String> getCollectorNames(long appId);
    public long getIdByNumberAndAppId(@Param("number")String number,@Param("appId")long appId);
    public List<String> getCollectorNameListByAppId(long appId);
    public void deleteById(long Id);
    public List<Map<String,String>> getCollectorNameByAppId(long appId);
    public String getCollectNameById(long id);
    public String getCollectNumberBySensorNumber(String number);
    public  void updateStatusByNumber(@Param("number")String number,@Param("status")String status);
    public  void  updateTimeByNumber(@Param("number")String number,@Param("lastMessageTime")String lastMessageTime);
}
