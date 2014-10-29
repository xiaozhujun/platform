package org.whut.rentManagement.business.stock.mapper;

import org.apache.ibatis.annotations.Param;
import org.whut.platform.fundamental.orm.mapper.AbstractMapper;
import org.whut.rentManagement.business.stock.entity.Stock_out_device;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-10-10
 * Time: 下午8:33
 * To change this template use File | Settings | File Templates.
 */
public interface Stock_out_deviceMapper extends AbstractMapper<Stock_out_device> {
   public Long getIdByStockOutIdAndDeviceId(@Param("stockOutId") long stockOutId, @Param("deviceId") long deviceId);
   // public Long getStockOutIdById(@Param("Id") Long Id);
    //public List<Map<>> getListByStockOutId()
   // public Long getIdByNumber(@Param("number") String number, @Param("appId") long appId);
  //  public List<Map<Long,Long>> getListByStockOutId(@Param("deviceType") String deviceType,@Param("deviceNumber") String deviceNumber,@Param("tagName") String tagName,@Param("appId") long appId);
   // public List<Map<String,Object>> getListByTagId(long tagId);
   // public String getNameById(long id);
   // public List<Stock_out_device> getListByAppId(long appId);
   // public Long getIdByName(@Param("name") String name, @Param("appId") long appId);
  //  public List<Stock_out_device> getInfoByCondition(@Param("name")String name,@Param("number")String number,@Param("deviceTypeId")long deviceTypeId,@Param("appId")long appId);
}
