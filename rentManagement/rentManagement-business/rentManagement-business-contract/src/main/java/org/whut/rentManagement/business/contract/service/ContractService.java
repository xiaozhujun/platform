package org.whut.rentManagement.business.contract.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.whut.rentManagement.business.contract.entity.Contract;
import org.whut.rentManagement.business.contract.mapper.ContractMapper;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: cww
 * Date: 14-10-13
 * Time: 下午1:20
 * To change this template use File | Settings | File Templates.
 */
public class ContractService {
    @Autowired
    private ContractMapper contractMapper;

    public void add(Contract contract){
        contractMapper.add(contract);
    }

    public int delete(Contract contract){
        return contractMapper.delete(contract);
    }

    public int update(Contract contract){
        return contractMapper.update(contract);
    }

    public int deleteById(long id,long appId){
        return contractMapper.deleteById(id,appId);
    }

    public List<Map<String,Object>> getListByAppId(long appId){
        return contractMapper.getListByAppId(appId);
    }

    public Contract getContractById(long id,long appId){
        return contractMapper.getContractById(id,appId);
    }

    public List<Map<String,String>> getContractList(Map<String,Object> condition){
        return contractMapper.getContractList(condition);
    }


}
