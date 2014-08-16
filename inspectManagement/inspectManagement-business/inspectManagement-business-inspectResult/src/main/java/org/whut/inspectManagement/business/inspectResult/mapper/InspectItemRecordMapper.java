package org.whut.inspectManagement.business.inspectResult.mapper;

import org.apache.ibatis.annotations.Param;
import org.whut.inspectManagement.business.inspectResult.entity.InspectItemRecord;
import org.whut.inspectManagement.business.inspectResult.entity.SearchBean;
import org.whut.platform.fundamental.orm.mapper.AbstractMapper;

import java.util.Date;
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
    public void updateMaintainIdAndSuggest(Map<String,Object> map); //新增更新
    public Long getIdByCondition(@Param("inspectTableId")long inspectTableId,@Param("inspectTagId")long inspectTagId,@Param("inspectItemId")long inspectItemId,
                                 @Param("inspectChoiceId")long inspectChoiceId,@Param("date")Date date,@Param("userId")long userId,
                                 @Param("deviceId")long deviceId,@Param("appId")long appId);
    public void updateTableRecordId(@Param("tableRecordId")long tableRecordId,@Param("id")long id);
}
