package org.whut.trackSystem.business.group.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.whut.trackSystem.business.group.entity.Group;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: yangyang
 * Date: 15-1-31
 * Time: 下午4:17
 * To change this template use File | Settings | File Templates.
 */
public class GroupService {
    @Autowired
    private org.whut.trackSystem.business.group.mapper.GroupMapper groupMapper;

    public void add(Group group) {
        groupMapper.add(group);
    }

    public int update(Group group) {
        return groupMapper.update(group);
    }

    public int delete(Group group) {
        return groupMapper.delete(group);
    }

    public List<Group> getListByAppId(Long appId) {
        return groupMapper.getListByAppId(appId);
    }

    public Long getIdByNumber(String number,Long appId) {
        return groupMapper.getIdByNumberAndAppId(number,appId);
    }
}
