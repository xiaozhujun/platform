package org.whut.rentManagement.business.contract.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.whut.rentManagement.business.contract.entity.Remove;
import org.whut.rentManagement.business.contract.mapper.RemoveMapper;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-10-14
 * Time: 下午5:09
 * To change this template use File | Settings | File Templates.
 */
public class RemoveService {
    @Autowired
    private RemoveMapper removeMapper;
    public void add(Remove remove){
        removeMapper.add(remove);
    }
    public int delete(Remove remove){
        return removeMapper.delete(remove);
    }
    public int update(Remove remove){
        return removeMapper.update(remove);
    }

    public List<Map<String,String>> getListByAppId(long appId){
        return removeMapper.getListByAppId(appId);
    }

    public List<Map<String,String>> getListByContractId(long contractId){
        return removeMapper.getListByContractId(contractId);
    }
    public List<Map<String,String>> getListByDeviceId(long deviceId){
        return removeMapper.getListByDeviceId(deviceId);
    }
    public long getIdByContractIdAndDeviceIdAndAppId(long contractId,long deviceId,long appId) {
        return removeMapper.getIdByContractIdAndDeviceIdAndAppId(contractId,deviceId,appId);
    }
    public long getRemoveDeviceIdById(long removeDeviceId){
        return  removeMapper.getRemoveDeviceIdById(removeDeviceId);
    }

    public List<Map<String,String>> getRemoveList(Map<String,Object> condition){
        return removeMapper.findByCondition(condition);
    }

    public Map<String,Object> getInfo(Map<String,Object> condition){
        return removeMapper.getInfo(condition);
    }
    public List<Map<String,Object>> getListByContractId(Long appId,Long contractId){
        return removeMapper.getListByContractId(appId,contractId);
    }

}
