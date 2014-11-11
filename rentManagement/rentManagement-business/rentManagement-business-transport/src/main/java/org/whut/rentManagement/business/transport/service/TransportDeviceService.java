package org.whut.rentManagement.business.transport.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.whut.rentManagement.business.transport.entity.TransportDevice;
import org.whut.rentManagement.business.transport.mapper.TransportDeviceMapper;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: xiaozhujun
 * Date: 14-11-11
 * Time: 上午12:05
 * To change this template use File | Settings | File Templates.
 */
public class TransportDeviceService {
    @Autowired
    private TransportDeviceMapper transportDeviceMapper;
    public void add(TransportDevice TransportDevice){
        transportDeviceMapper.add(TransportDevice);
    }
    public List<Map<String,String>> getListByAppId(long appId){
        return transportDeviceMapper.getListByAppId(appId);
    }
    public int update(TransportDevice TransportDevice){
        return transportDeviceMapper.update(TransportDevice);
    }
    public int delete(TransportDevice TransportDevice){
        return transportDeviceMapper.delete(TransportDevice);
    }
    public List<Map<String,Object>> listByTransportId(Map<String,Object> condition){
        return transportDeviceMapper.listByTransportId(condition);
    }
    public void deleteByTransportId(TransportDevice transportDevice){
        transportDeviceMapper.deleteByTransportId(transportDevice);
    }
}
