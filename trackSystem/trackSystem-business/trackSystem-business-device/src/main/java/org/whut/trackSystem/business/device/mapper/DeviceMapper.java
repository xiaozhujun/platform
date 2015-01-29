package org.whut.trackSystem.business.device.mapper;

import org.whut.platform.fundamental.orm.mapper.AbstractMapper;
import org.whut.trackSystem.business.device.entity.Device;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: yangyang
 * Date: 14-12-15
 * Time: 下午4:14
 * To change this template use File | Settings | File Templates.
 */
public interface DeviceMapper extends AbstractMapper<Device> {
    public List<Device> getListByAppId(Long appId);
    public Long getAppIdByDeviceNum(String number);
    public Long getIdByNumber(String number,Long appId);
}
