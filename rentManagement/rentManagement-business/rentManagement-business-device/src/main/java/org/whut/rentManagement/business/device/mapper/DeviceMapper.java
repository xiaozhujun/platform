package org.whut.rentManagement.business.device.mapper;

import org.apache.ibatis.annotations.Param;
import org.whut.platform.fundamental.orm.mapper.AbstractMapper;
import org.whut.rentManagement.business.device.entity.Device;

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
    public int deleteById(@Param("id") long id);
    public Long getIdByNumber(@Param("number") String number, @Param("appId") Long appId);
    public List<Map<String,String>> getMainDeviceListByAppId(long appId);
    public List<Map<String,Object>> findByCondition(Map<String,Object> condition);
    public ArrayList<Long> findMainDeviceList(@Param("appId") long appId,@Param("deviceIdList") List<String> deviceIdList);
    public void installDevice(@Param("appId") long appId,@Param("mainDeviceId") long mainDeviceId,@Param("deviceIdList") List<String> deviceIdList);
    public void removeDevice(@Param("appId") long appId,@Param("deviceIdList") List<String> deviceIdList);
    public void transportDevice(@Param("appId") long appId,@Param("deviceIdList") List<String> deviceIdList);
    public void stockIn(@Param("appId") long appId,@Param("deviceIdList") List<String> deviceIdList);
    public void stockOut(@Param("appId") long appId,@Param("deviceIdList") List<String> deviceIdList);
    public Map<String,Object> getMainDeviceInfo(Map<String,Object> condition);
    public List<Map<String,Object>> listByMainDeviceId(Map<String,Object> condition);
    public List<Map<String,Object>> findMainDeviceByCondition(Map<String,Object> condition);
}
