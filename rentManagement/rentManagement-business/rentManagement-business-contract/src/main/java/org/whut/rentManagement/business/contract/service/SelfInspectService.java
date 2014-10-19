package org.whut.rentManagement.business.contract.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.whut.rentManagement.business.contract.entity.SelfInspect;
import org.whut.rentManagement.business.contract.mapper.SelfInspectMapper;

import java.util.HashMap;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-10-14
 * Time: 下午5:09
 * To change this template use File | Settings | File Templates.
 */
public class SelfInspectService {
    @Autowired
    private SelfInspectMapper selfInspectMapper;
    public void add(SelfInspect selfInspect){
        selfInspectMapper.add(selfInspect);
    }
    public List<SelfInspect> getListByAppId(long appId){
        return selfInspectMapper.getListByAppId(appId);
    }
    public int delete(SelfInspect selfInspect){
        return selfInspectMapper.delete(selfInspect);
    }
    public int update(SelfInspect selfInspect){
        return selfInspectMapper.update(selfInspect);
    }
    public List<SelfInspect> list(){
        return selfInspectMapper.findByCondition(new HashMap<String, Object>());
    }

    public long getContractIdById(long contractId){
        return  selfInspectMapper.getContractIdById(contractId);
    }


}
