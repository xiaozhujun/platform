package org.whut.monitor.business.monitor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.whut.monitor.business.monitor.entity.Group;
import org.whut.monitor.business.monitor.mapper.GroupMapper;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: yangyang
 * Date: 14-7-15
 * Time: 下午3:07
 * To change this template use File | Settings | File Templates.
 */
public class GroupService {
    @Autowired
    private GroupMapper groupMapper;

    public void add(Group group) {
        groupMapper.add(group);
    }

    public int delete(Group group) {
        return groupMapper.delete(group);
    }

    public int update(Group group) {
        return groupMapper.update(group);
    }

    public List<Group> getListByAppId(long appId) {
        return groupMapper.getListByAppId(appId);
    }

    public long getIdByNameAndAppId(String name,long appId) {
        return groupMapper.getIdByNameAndAppId(name,appId);
    }

    public String getNameById(long id) {
        return groupMapper.getNameById(id);
    }

}
