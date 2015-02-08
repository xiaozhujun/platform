package org.whut.trackSystem.business.group.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.whut.trackSystem.business.group.entity.GroupUser;
import org.whut.trackSystem.business.group.mapper.GroupUserMapper;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: yangyang
 * Date: 15-2-3
 * Time: 下午4:21
 * To change this template use File | Settings | File Templates.
 */
public class GroupUserService {
    @Autowired
    private GroupUserMapper groupUserMapper;

    public void add(GroupUser groupUser) {
        groupUserMapper.add(groupUser);
    }

    public int update(GroupUser groupUser) {
        return groupUserMapper.update(groupUser);
    }

    public int delete(GroupUser groupUser) {
        return groupUserMapper.delete(groupUser);
    }

    public List<GroupUser> getListByAppId(Long appId) {
        return groupUserMapper.getListByAppId(appId);
    }

    public Long getGroupIdByUserId(Long userId,Long appId) {
        return groupUserMapper.getGroupIdByUserId(userId,appId);
    }

    public List<Map<String,String>> findByCondition(Long groupId,Long appId) {
        return groupUserMapper.findByCondition(groupId, appId);
    }

    public Map<String,String> getExtraInfo(String deviceNum,Long appId) {
        return groupUserMapper.getExtraInfo(deviceNum, appId);
    }

    public List<Map<String,String>> getUserByGroupId(Long groupId,Long appId) {
        return groupUserMapper.getUserByGroupIdAndAppId(groupId,appId);
    }

    public List<Map<String,String>> getListByCondition(Long groupId,Long userId,Long appId) {
        return groupUserMapper.getListByCondition(groupId,userId,appId);
    }
}
