package org.whut.inspectManagement.business.device.mapper;

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
    public Long FindByDNumberAndAreaId(long deviceNumber,long areaId);
}
