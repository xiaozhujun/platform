package org.whut.rentManagement.business.contract.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.whut.rentManagement.business.contract.entity.PreBury;
import org.whut.rentManagement.business.contract.mapper.PreBuryMapper;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Aaron
 * Date: 14-10-18
 * Time: 下午4:32
 * To change this template use File | Settings | File Templates.
 */

public class PreBuryService {
    @Autowired
    private PreBuryMapper preBuryMapper;
    public void add(PreBury preBury){
        preBuryMapper.add(preBury);
    }
    public List<Map<String,Object>> getListByAppId(long appId){
        return preBuryMapper.getListByAppId(appId);
    }
    public int update(PreBury preBury){
        return preBuryMapper.update(preBury);
    }
    public int delete(PreBury preBury){
        return preBuryMapper.delete(preBury);
    }
    public long getIdByContractId(long contractId){
        return preBuryMapper.getIdByContractId(contractId);
    }

    public List<Map<String,Object>> getPreburyList(Map<String,Object> condition){
        return preBuryMapper.getPreburyList(condition);
    }
    public List<Map<String,Object>> findByCondition(Map<String,Object> condition){
        return preBuryMapper.findByCondition(condition);
    }
    public List<Map<String,Object>> getListByContractId(Long appId,Long contractId){
        return preBuryMapper.getListByContractId(appId,contractId);
    }
}
