package org.whut.deviceManagement.business.device.mapper;

import org.apache.ibatis.annotations.Param;
import org.whut.deviceManagement.business.device.entity.Device;
import org.whut.platform.fundamental.orm.mapper.AbstractMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Steven
 * Date: 14-10-16
 * Time: 下午5:10
 * To change this template use File | Settings | File Templates.
 */
public interface DeviceMapper extends AbstractMapper<Device> {
    public List<Map<String,String>> getListByAppId(long appId);
    public Map<String,String> detailInfo(@Param("deviceId") Long deviceId,@Param("appId") Long appId);
    public int deleteById(@Param("id") long id);
    public Long getIdByNumber(@Param("number") String number,@Param("appId") Long appId);
    public List<Map<String,Object>> findByCondition(Map<String,Object> condition);

}
