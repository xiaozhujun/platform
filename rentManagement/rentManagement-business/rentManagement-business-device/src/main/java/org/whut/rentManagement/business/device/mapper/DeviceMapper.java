package org.whut.rentManagement.business.device.mapper;

import org.whut.platform.fundamental.orm.mapper.AbstractMapper;
import org.whut.rentManagement.business.device.entity.Device;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Steven
 * Date: 14-10-16
 * Time: 下午5:10
 * To change this template use File | Settings | File Templates.
 */
public interface DeviceMapper extends AbstractMapper<Device> {
    public List<Device> getListByAppId(long appId);
    public long getIdByNumber(String number, long appId);
}
