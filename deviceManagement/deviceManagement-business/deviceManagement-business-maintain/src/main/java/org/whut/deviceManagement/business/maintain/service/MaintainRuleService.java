package org.whut.deviceManagement.business.maintain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.whut.deviceManagement.business.maintain.entity.MaintainRule;
import org.whut.deviceManagement.business.maintain.mapper.MaintainRuleMapper;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: xiaozhujun
 * Date: 15-4-25
 * Time: 下午11:59
 * To change this template use File | Settings | File Templates.
 */
public class MaintainRuleService {

    @Autowired
    private MaintainRuleMapper maintainRuleMapper;

    public void add(MaintainRule maintainRule){
        maintainRuleMapper.add(maintainRule);
    }

    public int delete(MaintainRule maintainRule){
        return maintainRuleMapper.delete(maintainRule);
    }

    public int update(MaintainRule maintainRule){
        return maintainRuleMapper.update(maintainRule);
    }

    public int deleteById(long id){
        return maintainRuleMapper.deleteById(id);
    }

    public List<Map<String,String>> getListByAppId(long appId) {
        return maintainRuleMapper.getListByAppId(appId);
    }
}
