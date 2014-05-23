package org.whut.inspectManagement.business.device.mapper;

import org.whut.inspectManagement.business.device.entity.Device;
import org.whut.platform.fundamental.orm.mapper.AbstractMapper;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-5-15
 * Time: 下午3:41
 * To change this template use File | Settings | File Templates.
 */
public interface DeviceMapper extends AbstractMapper<Device> {
    public List<Device> findByCondition(Map<String,Object> map);
    public long getIdByNumber(long number);
}
