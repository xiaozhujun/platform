package org.whut.trackSystem.business.group.mapper;

import org.apache.ibatis.annotations.Param;
import org.whut.platform.fundamental.orm.mapper.AbstractMapper;
import org.whut.trackSystem.business.group.entity.GroupUser;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: yangyang
 * Date: 15-2-3
 * Time: 下午4:21
 * To change this template use File | Settings | File Templates.
 */
public interface GroupUserMapper extends AbstractMapper<GroupUser> {
    public List<GroupUser> getListByAppId(Long appId);
    public Long getGroupIdByUserId(@Param("userId")Long userId,@Param("appId")Long appId);
    public List<Map<String,String>> findByCondition(@Param("groupId")Long groupId,@Param("appId")Long appId);
    public Map<String,String> getExtraInfo(@Param("deviceNum")String deviceNum,@Param("appId")Long appId);
    public List<Map<String,String>> getUserByGroupIdAndAppId(@Param("groupId")Long groupId,@Param("appId")Long appId);
    public List<Map<String,String>> getListByCondition(@Param("groupId")Long groupId,@Param("userId")Long userId,@Param("appId")Long appId);
}
