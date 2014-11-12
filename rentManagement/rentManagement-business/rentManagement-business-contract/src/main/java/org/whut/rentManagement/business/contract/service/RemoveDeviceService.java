package org.whut.rentManagement.business.contract.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.whut.rentManagement.business.contract.entity.RemoveDevice;
import org.whut.rentManagement.business.contract.mapper.RemoveDeviceMapper;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: xiaozhujun
 * Date: 14-11-12
 * Time: 下午6:50
 * To change this template use File | Settings | File Templates.
 */
public class RemoveDeviceService {

    @Autowired
    private RemoveDeviceMapper removeDeviceMapper;
    public void add(RemoveDevice removeDevice){
        removeDeviceMapper.add(removeDevice);
    }
    public List<Map<String,String>> getListByAppId(long appId){
        return removeDeviceMapper.getListByAppId(appId);
    }
    public int update(RemoveDevice removeDevice){
        return removeDeviceMapper.update(removeDevice);
    }
    public int delete(RemoveDevice removeDevice){
        return removeDeviceMapper.delete(removeDevice);
    }
    public List<Map<String,Object>> listByRemoveId(Map<String,Object> condition){
        return removeDeviceMapper.listByRemoveId(condition);
    }
    public void deleteByRemoveId(RemoveDevice removeDevice){
        removeDeviceMapper.deleteByRemoveId(removeDevice);
    }
}
