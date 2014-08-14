package org.whut.inspectManagement.business.device.mapper;

import org.apache.ibatis.annotations.Param;
import org.whut.inspectManagement.business.device.entity.InspectTag;
import org.whut.platform.fundamental.orm.mapper.AbstractMapper;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-5-19
 * Time: 上午11:42
 * To change this template use File | Settings | File Templates.
 */
public interface InspectTagMapper extends AbstractMapper<InspectTag> {
    public List<InspectTag> findByCondition(Map<String,Object> map);
    public long getIdByDeviceNumAndAreaId(@Param("deviceNumber") String deviceNumber,@Param("inspectAreaId")long inspectAreaId,@Param("appId") long appId);
    public List<InspectTag> getListByAppId(long appId);
    public String getIdByName(String name);
    public long getIdByNumber(@Param("number") String number,@Param("appId") long appId);
}
