package org.whut.inspectManagement.business.inspectReport.mapper;

import org.apache.ibatis.annotations.Param;
import org.whut.inspectManagement.business.inspectReport.entity.InspectReport;
import org.whut.inspectManagement.business.inspectReport.entity.SearchReportBean;
import org.whut.platform.fundamental.orm.mapper.AbstractMapper;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: ThinkPad
 * Date: 14-6-10
 * Time: 上午9:22
 * To change this template use File | Settings | File Templates.
 */
public interface InspectReportMapper extends AbstractMapper<InspectReport>{

     public List<Map<String,String>> getInspectTableRecordList(@Param("userId")String userId,@Param("deviceId")String deviceId,@Param("sTime")String sTime,@Param("eTime")String eTime,@Param("appId")long appId);

     public Map<String,String> getInfoByMongoDbObject(Map<String,String> map);

     public List<SearchReportBean>getInspectTableRecordListByBean(@Param("userId")String userId,@Param("deviceId")String deviceId,@Param("sTime")String sTime,@Param("eTime")String eTime,@Param("appId")long appId);

     public List<SearchReportBean>getDeviceHistoryData(@Param("sTime")String sTime,@Param("eTime")String eTime,@Param("deviceId")String deviceId,@Param("appId")long appId);
}
