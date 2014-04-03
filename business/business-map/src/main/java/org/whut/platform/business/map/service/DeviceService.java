package org.whut.platform.business.map.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.whut.platform.business.map.entity.Device;
import org.whut.platform.business.map.mapper.DeviceMapper;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: zhuzhenhua
 * Date: 14-3-24
 * Time: 下午3:59
 * To change this template use File | Settings | File Templates.
 */
public class DeviceService {
    @Autowired
    private DeviceMapper mapper;
    public List<Device> findDeviceInfoById(long id){
        return mapper.findDeviceInfoById(id);
    }
}
