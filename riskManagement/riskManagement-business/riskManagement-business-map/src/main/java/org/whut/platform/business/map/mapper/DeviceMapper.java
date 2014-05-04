package org.whut.platform.business.map.mapper;

import org.whut.platform.business.map.entity.Device;
import org.whut.platform.fundamental.orm.mapper.AbstractMapper;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: zhuzhenhua
 * Date: 14-3-24
 * Time: 下午3:58
 * To change this template use File | Settings | File Templates.
 */
public interface DeviceMapper extends AbstractMapper{
    public List<Device> findDeviceInfoById(long cid);
}
