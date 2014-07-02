package org.whut.inspectManagement.business.task.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.whut.inspectManagement.business.task.entity.InspectPlan;
import org.whut.inspectManagement.business.task.mapper.InspectPlanMapper;

import java.util.HashMap;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: xiaozhujun
 * Date: 14-6-30
 * Time: 下午4:26
 * To change this template use File | Settings | File Templates.
 */
public class InspectPlanService {
    @Autowired
    private InspectPlanMapper inspectPlanMapper;

    public void add(InspectPlan inspectPlan){
        inspectPlanMapper.add(inspectPlan);
    }

    public List<InspectPlan> list(){
        return  inspectPlanMapper.findByCondition(new HashMap<String, Object>());
    }

    public List<InspectPlan> getListByAppId(long appId)
    {
        return inspectPlanMapper.getListByAppId(appId);
    }

    public void update(InspectPlan inspectPlan){
        inspectPlanMapper.update(inspectPlan);
    }

    public void delete(InspectPlan inspectPlan){
        inspectPlanMapper.delete(inspectPlan);
    }

    public long getIdByName(String name,long appId){
        return inspectPlanMapper.getIdByName(name,appId);
    }

    public List<InspectPlan> query(HashMap<String, Object> params){
        return  inspectPlanMapper.findByCondition(params);
    }

    public boolean isExist(String name,long appId){
        long id = inspectPlanMapper.getIdByName(name,appId);
        if(id==0){
            return false;
        }
        return true;
    }

}
