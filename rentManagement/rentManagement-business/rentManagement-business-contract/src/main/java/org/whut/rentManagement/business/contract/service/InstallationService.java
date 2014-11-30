package org.whut.rentManagement.business.contract.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.whut.rentManagement.business.contract.entity.Installation;
import org.whut.rentManagement.business.contract.mapper.InstallationMapper;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Aaron
 * Date: 14-10-12
 * Time: 下午4:35
 * To change this template use File | Settings | File Templates.
 */
public class InstallationService {
    @Autowired
    private InstallationMapper installationmapper;
    public void add(Installation Installation){
        installationmapper.add(Installation);
    }
    public List<Map<String,String>> getListByAppId(long appId){
        return installationmapper.getListByAppId(appId);
    }
    public int update(Installation installation){
        return installationmapper.update(installation);
    }
    public int delete(Installation installation){
        return installationmapper.delete(installation);
    }
    public List<Map<String,String>> findByCondition(Map<String,Object> condition){
        return installationmapper.findByCondition(condition);
    }

    public Map<String,Object> getInfo(Map<String,Object> condition){
        return installationmapper.getInfo(condition);
    }
    public List<Map<String,Object>> getListByContractId(Long appId,Long contractId){
        return installationmapper.getListByContractId(appId,contractId);
    }
}
