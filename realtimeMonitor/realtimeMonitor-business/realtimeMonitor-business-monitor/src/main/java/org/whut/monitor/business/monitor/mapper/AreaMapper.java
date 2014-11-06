package org.whut.monitor.business.monitor.mapper;

import org.apache.ibatis.annotations.Param;
import org.whut.monitor.business.monitor.entity.Area;
import org.whut.platform.fundamental.orm.mapper.AbstractMapper;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-7-15
 * Time: 下午3:12
 * To change this template use File | Settings | File Templates.
 */
public interface AreaMapper extends AbstractMapper<Area> {
    public List<Map<String,String>> getListByAppId(long appId);
    public List<Area> getAreaByGroupId(long groupId);
    public List<String> getAreaNames(long groupId);
    public long getIdByNameAndGroupIdAndAppId(@Param("name")String name,@Param("groupId")long groupId,@Param("appId")long appId);
    public List<Map<String,String>> getAreaNameListByAppId(long appId);
    public String getAreaNameBySNum(String sNum);
}
