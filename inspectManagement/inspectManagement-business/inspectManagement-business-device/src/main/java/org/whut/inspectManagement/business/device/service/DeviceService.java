package org.whut.inspectManagement.business.device.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.whut.inspectManagement.business.device.entity.Device;
import org.whut.inspectManagement.business.device.mapper.DeviceMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-5-15
 * Time: 下午3:42
 * To change this template use File | Settings | File Templates.
 */
public class DeviceService {
    @Autowired
    private DeviceMapper deviceMapper;

    public void add(Device device){
        deviceMapper.add(device);
    }

    public List<Device> list(){
      return  deviceMapper.findByCondition(new HashMap<String, Object>());
    }

    public void update(Device device){
        deviceMapper.update(device);
    }

    public void delete(Device device){
        deviceMapper.delete(device);
    }

}
