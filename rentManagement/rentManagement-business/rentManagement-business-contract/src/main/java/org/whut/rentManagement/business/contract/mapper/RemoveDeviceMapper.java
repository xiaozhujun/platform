package org.whut.rentManagement.business.contract.mapper;

import org.whut.platform.fundamental.orm.mapper.AbstractMapper;
import org.whut.rentManagement.business.contract.entity.RemoveDevice;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: xiaozhujun
 * Date: 14-11-12
 * Time: 下午3:47
 * To change this template use File | Settings | File Templates.
 */
public interface RemoveDeviceMapper  extends AbstractMapper<RemoveDevice> {
    public List<Map<String,String>> getListByAppId(long appId);
    public List<Map<String,Object>> listByRemoveId(Map<String,Object> condition);
    public void deleteByRemoveId(RemoveDevice removeDevice);
}