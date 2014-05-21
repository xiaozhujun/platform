package org.whut.platform.business.riskmodel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.whut.platform.business.riskmodel.entity.RiskModel;
import org.whut.platform.business.riskmodel.mapper.RiskModelMapper;

import java.util.HashMap;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ThinkPad
 * Date: 14-5-15
 * Time: 下午12:50
 * To change this template use File | Settings | File Templates.
 */
public class RiskModelService {
    @Autowired
    private RiskModelMapper mapper;

    public List<RiskModel> findByName(String name){
        return mapper.findByName(name);
    };
    public void add(RiskModel riskModel){
        mapper.add(riskModel);
    }
    public List<RiskModel>list(){
        return mapper.findByCondition(new HashMap<String,Object>());
    }
    public int update(RiskModel riskModel){
        return mapper.update(riskModel);
    }
    public int delete(RiskModel riskModel){
        return mapper.delete(riskModel);
    }
}
