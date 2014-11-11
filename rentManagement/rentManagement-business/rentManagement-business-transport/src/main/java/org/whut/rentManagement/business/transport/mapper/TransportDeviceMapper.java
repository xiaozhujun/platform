package org.whut.rentManagement.business.transport.mapper;

import org.whut.platform.fundamental.orm.mapper.AbstractMapper;
import org.whut.rentManagement.business.transport.entity.TransportDevice;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: xiaozhujun
 * Date: 14-11-11
 * Time: 上午12:04
 * To change this template use File | Settings | File Templates.
 */
public interface TransportDeviceMapper  extends AbstractMapper<TransportDevice> {
    public List<Map<String,String>> getListByAppId(long appId);
    public List<Map<String,Object>> listByTransportId(Map<String,Object> condition);
    public void deleteByTransportId(TransportDevice transportDevice);
}