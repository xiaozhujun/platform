package org.whut.trackSystem.business.group.mapper;

import org.apache.ibatis.annotations.Param;
import org.whut.platform.fundamental.orm.mapper.AbstractMapper;
import org.whut.trackSystem.business.group.entity.Group;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: yangyang
 * Date: 15-1-31
 * Time: 下午4:17
 * To change this template use File | Settings | File Templates.
 */
public interface GroupMapper extends AbstractMapper<Group> {
    public List<Group> getListByAppId(Long appId);
    public Long getIdByNumberAndAppId(@Param("number") String number, @Param("appId") Long appId);
}
