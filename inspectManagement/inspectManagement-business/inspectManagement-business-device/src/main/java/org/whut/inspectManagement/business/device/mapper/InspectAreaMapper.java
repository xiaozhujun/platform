package org.whut.inspectManagement.business.device.mapper;

import org.whut.inspectManagement.business.device.entity.InspectArea;
import org.whut.platform.fundamental.orm.mapper.AbstractMapper;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-5-19
 * Time: 上午11:39
 * To change this template use File | Settings | File Templates.
 */
public interface InspectAreaMapper extends AbstractMapper<InspectArea> {
    public List<InspectArea> findByCondition(Map<String,Object> map);
}