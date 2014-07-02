package org.whut.inspectManagement.business.task.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.whut.inspectManagement.business.task.entity.UserInspectPlan;
import org.whut.inspectManagement.business.task.mapper.UserInspectPlanMapper;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: xiaozhujun
 * Date: 14-6-30
 * Time: 下午4:26
 * To change this template use File | Settings | File Templates.
 */
public class UserInspectPlanService {
    @Autowired
    private UserInspectPlanMapper userInspectPlanMapper;

    public void add(UserInspectPlan inspectPlan){
        userInspectPlanMapper.add(inspectPlan);
    }

    public List<UserInspectPlan> getListByAppId(long appId)
    {
        return userInspectPlanMapper.getListByAppId(appId);
    }

    public void update(UserInspectPlan inspectPlan){
        userInspectPlanMapper.update(inspectPlan);
    }

    public void delete(UserInspectPlan inspectPlan){
        userInspectPlanMapper.delete(inspectPlan);
    }

    public List<UserInspectPlan> query(UserInspectPlan userInspectPlan){
        return  userInspectPlanMapper.findByCondition(userInspectPlan);
    }

    public List<UserInspectPlan> getListByUserId(long userId,long appId){
        return userInspectPlanMapper.getListByUserId(userId, appId);
    }

}
