package org.whut.rentManagement.business.transport.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.whut.rentManagement.business.transport.entity.Transport;
import org.whut.rentManagement.business.transport.mapper.TransportMapper;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: xiaozhujun
 * Date: 14-11-9
 * Time: 下午10:57
 * To change this template use File | Settings | File Templates.
 */
public class TransportService {
    @Autowired
    private TransportMapper transportMapper;
    public void add(Transport Transport){
        transportMapper.add(Transport);
    }
    public List<Map<String,String>> getListByAppId(long appId){
        return transportMapper.getListByAppId(appId);
    }
    public int update(Transport transport){
        return transportMapper.update(transport);
    }
    public int delete(Transport transport){
        return transportMapper.delete(transport);
    }

    public List<Map<String,String>> findByCondition(Map<String,Object> condition){
        return transportMapper.findByCondition(condition);
    }

    public Map<String,Object> getInfo(Map<String,Object> condition){
        return transportMapper.getInfo(condition);
    }
}
