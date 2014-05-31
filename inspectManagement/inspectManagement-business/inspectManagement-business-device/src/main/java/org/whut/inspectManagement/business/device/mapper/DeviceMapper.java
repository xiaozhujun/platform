package org.whut.inspectManagement.business.device.mapper;

import org.apache.ibatis.annotations.Param;
import org.whut.inspectManagement.business.device.entity.Device;
import org.whut.inspectManagement.business.device.entity.DeviceTypeTag;
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
    public long getIdByNumber(String number);
    public List<Map<String,String>> getListByCondition(@Param("deviceType") String deviceType,@Param("deviceNumber") String deviceNumber,@Param("tagName") String tagName,@Param("appId") long appId);
    public List<Map<String,String>> getListByTagId(long tagId);
}
