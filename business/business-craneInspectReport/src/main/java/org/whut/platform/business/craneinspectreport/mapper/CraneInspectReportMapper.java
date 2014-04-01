package org.whut.platform.business.craneinspectreport.mapper;

import org.apache.ibatis.annotations.Param;
import org.whut.platform.business.craneinspectreport.entity.CraneInspectReport;
import org.whut.platform.fundamental.orm.mapper.AbstractMapper;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ThinkPad
 * Date: 14-3-17
 * Time: 下午10:39
 * To change this template use File | Settings | File Templates.
 */
public interface CraneInspectReportMapper extends AbstractMapper {
     public void insert(CraneInspectReport craneInspectReport);
     public List<CraneInspectReport> getInfoByAddressId(Long addressId);
     public List<CraneInspectReport> getInfoByUnitAddress(String unitAddress);
     public List<CraneInspectReport> getCraneInspectReportInfoByAddressAndEquipment(@Param("unitAddress") String unitAddress,@Param("equipmentVariety") String equipmentVariety);
}
