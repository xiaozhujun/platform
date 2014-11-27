package org.whut.rentManagement.business.rentReport.mapper;

import org.apache.ibatis.annotations.Param;
import org.whut.platform.fundamental.orm.mapper.AbstractMapper;
import org.whut.rentManagement.business.rentReport.entity.RentReport;
import org.whut.rentManagement.business.rentReport.entity.SearchReportBean;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: cwb
 * Date: 14-11-21
 * Time: 下午3:15
 * To change this template use File | Settings | File Templates.
 */
public interface RentReportMapper extends AbstractMapper{
    public List<Map<String,String>> getRentTableRecordList(@Param("deviceType")String deviceType,@Param("deviceStatus")String deviceStatus,@Param("sTime")String sTime,@Param("eTime")String eTime,@Param("appId")long appId);
   /* public Map<String,String> aaa(@Param("appId")long appId);*/
   public List<SearchReportBean>getRentTableRecordByBean(@Param("deviceType")String deviceType,@Param("deviceStatus")String deviceStatus,@Param("sTime")String sTime,@Param("eTime")String eTime,@Param("appId")long appId);
}
