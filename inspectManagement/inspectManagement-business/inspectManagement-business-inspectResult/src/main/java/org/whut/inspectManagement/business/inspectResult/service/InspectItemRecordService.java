package org.whut.inspectManagement.business.inspectResult.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.whut.inspectManagement.business.inspectResult.entity.SearchBean;
import org.whut.inspectManagement.business.inspectResult.mapper.InspectItemRecordMapper;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: John
 * Date: 14-5-13
 * Time: 下午2:08
 * To change this template use File | Settings | File Templates.
 */
public class InspectItemRecordService {

    @Autowired
    private InspectItemRecordMapper inspectItemRecordMapper;

    public List<SearchBean> findByCondition(Map<String,Object> map){
        return inspectItemRecordMapper.findByCondition(map);
    }

    public void updateMaintainIdAndSuggest(Map<String,Object>map){
        inspectItemRecordMapper.updateMaintainIdAndSuggest(map);
    }

    public long getTableRecordIdByItemRecordId(long itemRecordId) {
        return inspectItemRecordMapper.getTableRecordIdByItemRecordId(itemRecordId);
    }
}
