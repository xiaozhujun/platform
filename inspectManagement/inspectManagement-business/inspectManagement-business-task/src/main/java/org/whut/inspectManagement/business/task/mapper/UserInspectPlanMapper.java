package org.whut.inspectManagement.business.task.mapper;

import org.apache.ibatis.annotations.Param;
import org.whut.inspectManagement.business.task.entity.InspectPlan;
import org.whut.inspectManagement.business.task.entity.UserInspectPlan;
import org.whut.platform.fundamental.orm.mapper.AbstractMapper;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: xiaozhujun
 * Date: 14-6-30
 * Time: 下午4:21
 * To change this template use File | Settings | File Templates.
 */
public interface UserInspectPlanMapper extends AbstractMapper<UserInspectPlan> {
    public List<InspectPlan> findByCondition(@Param("appId") long appId, @Param("name") String name, @Param("createtime") String createtime);
    public List<UserInspectPlan> getListByUserId(long userId,long appId);
    public List<UserInspectPlan> getListByAppId(long appId);
    public List<UserInspectPlan> findByCondition(UserInspectPlan userInspectPlan);
}
