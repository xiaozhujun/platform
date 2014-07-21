package org.whut.inspectManagement.business.inspectResult.mapper;

import org.whut.inspectManagement.business.inspectResult.entity.InspectItemRecord;
import org.whut.inspectManagement.business.inspectResult.entity.SearchBean;
import org.whut.platform.fundamental.orm.mapper.AbstractMapper;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: John
 * Date: 14-5-13
 * Time: 上午10:17
 * To change this template use File | Settings | File Templates.
 */
public interface InspectItemRecordMapper extends AbstractMapper<InspectItemRecord>{
    public long getIdByName(String name);
    public List<SearchBean> findByCondition(Map<String,Object> map);
}
