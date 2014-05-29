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
    public Long getIdByDeviceNumAndAreaId(@Param("deviceNumber") String deviceNumber,@Param("inspectAreaId")long inspectAreaId);
}
