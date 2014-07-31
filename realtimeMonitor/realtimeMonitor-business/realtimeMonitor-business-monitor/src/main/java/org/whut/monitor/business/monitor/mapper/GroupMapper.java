package org.whut.monitor.business.monitor.mapper;

import org.apache.ibatis.annotations.Param;
import org.whut.monitor.business.monitor.entity.Group;
import org.whut.platform.fundamental.orm.mapper.AbstractMapper;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: yangyang
 * Date: 14-7-15
 * Time: 下午3:07
 * To change this template use File | Settings | File Templates.
 */
public interface GroupMapper extends AbstractMapper<Group> {
    public long getIdByNameAndAppId(@Param("name") String name, @Param("appId") long appId);
    public List<Group> getListByAppId(long appId);
    public String getNameById(long id);
}
