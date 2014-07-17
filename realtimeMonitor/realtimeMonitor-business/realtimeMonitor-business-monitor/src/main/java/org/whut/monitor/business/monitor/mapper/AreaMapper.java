package org.whut.monitor.business.monitor.mapper;

import org.apache.ibatis.annotations.Param;
import org.whut.monitor.business.monitor.entity.Area;
import org.whut.platform.fundamental.orm.mapper.AbstractMapper;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-7-15
 * Time: 下午3:12
 * To change this template use File | Settings | File Templates.
 */
public interface AreaMapper extends AbstractMapper<Area> {
    public List<Area> getListByAppId(long appId);
    public long getIdByNameAndAppId(@Param("name") String name,@Param("appId") long appId);
    public List<Area> getAreaByGroupId(long groupId);
    public long getGroupIdByNameAndAppId(@Param("name") String name,@Param("appId") long appId);
}
