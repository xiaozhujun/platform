package org.whut.platform.business.craneinspectreport.mapper;

<<<<<<< HEAD

=======
import org.apache.ibatis.annotations.Param;
>>>>>>> 23dd51744e660700d6196a2d52cb2394d49b9f1c
import org.whut.platform.business.craneinspectreport.entity.CraneInspectReport;
import org.whut.platform.fundamental.orm.mapper.AbstractMapper;

import java.util.List;
<<<<<<< HEAD
import java.util.Map;
=======
>>>>>>> 23dd51744e660700d6196a2d52cb2394d49b9f1c

/**
 * Created with IntelliJ IDEA.
 * User: ThinkPad
 * Date: 14-3-17
 * Time: 下午10:39
 * To change this template use File | Settings | File Templates.
 */
<<<<<<< HEAD
public interface CraneInspectReportMapper extends AbstractMapper<CraneInspectReport> {
    public void insert(CraneInspectReport craneInspectReport);
    public List<CraneInspectReport> findByCondition(Map<String,Object> map);
    public String getReportNumber(String reportNumber);
=======
public interface CraneInspectReportMapper extends AbstractMapper {
     public void insert(CraneInspectReport craneInspectReport);
     public List<CraneInspectReport> getInfoByAddressId(Long addressId);
     public List<CraneInspectReport> getInfoByUnitAddress(String unitAddress);
     public List<CraneInspectReport> getCraneInspectReportInfoByAddressAndEquipment(@Param("unitAddress") String unitAddress,@Param("equipmentVariety") String equipmentVariety);
    public List<CraneInspectReport> getUnitaddressByArea(@Param("province") String province,@Param("city") String city,@Param("area") String area);
>>>>>>> 23dd51744e660700d6196a2d52cb2394d49b9f1c
}
