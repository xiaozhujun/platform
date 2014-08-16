package org.whut.inspectManagement.business.inspectResult.mapper;

import org.apache.ibatis.annotations.Param;
import org.whut.inspectManagement.business.inspectResult.entity.InspectTableRecord;
import org.whut.platform.fundamental.orm.mapper.AbstractMapper;

/**
 * Created with IntelliJ IDEA.
 * User: John
 * Date: 14-5-13
 * Time: 下午2:08
 * To change this template use File | Settings | File Templates.
 */
public interface InspectTableRecordMapper extends AbstractMapper<InspectTableRecord> {
    public int updateTableRecord(@Param("exceptionCount") int exceptionCount,@Param("tableId") long tableId,@Param("createTime") String createTime,@Param("appId") long appId);
    public Long getInspectTableId(@Param("inspectTime") String inspectTime,@Param("inspectTableId") long inspectTableId,@Param("appId") long appId);
    public Long getIdByCondition(@Param("inspectTableId")long inspectTableId,@Param("userId")long userId,@Param("mongoId")String mongoId,@Param("deviceId")long deviceId,@Param("appId")long appId);
    public Long getTableIdByTableRecordId(long tableRecordId);
}
