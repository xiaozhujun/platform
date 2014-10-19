package org.whut.rentManagement.business.contract.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.whut.rentManagement.business.contract.entity.Installation;
import org.whut.rentManagement.business.contract.entity.PreBury;
import org.whut.rentManagement.business.contract.mapper.InstallationMapper;
import org.whut.rentManagement.business.contract.mapper.PreBuryMapper;

import javax.ws.rs.Path;
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
    public List<Map<String,String>> getListByAppId(long appId){
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
}
