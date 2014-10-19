package org.whut.rentManagement.business.stock.mapper;

import org.apache.ibatis.annotations.Param;
import org.whut.platform.fundamental.orm.mapper.AbstractMapper;
import org.whut.rentManagement.business.stock.entity.Stock_out_sheet;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-10-10
 * Time: 上午10:32
 * To change this template use File | Settings | File Templates.
 */
public interface Stock_out_sheetMapper extends AbstractMapper<Stock_out_sheet> {
    public List<Stock_out_sheet> findByCondition(@Param("appId") long appId, @Param("customerId") long customerId, @Param("contractId") long contractId, @Param("storehouseId") long storehouseId);
    public List<Stock_out_sheet> getListByAppId(long appId);
    public List<Map<String,String>> getListByCondition(@Param("appId") long appId, @Param("customerId") long customerId, @Param("contractId") long contractId, @Param("storehouseId") long storehouseId);
    public long getIdByCustomerIdAndContractId(@Param("customerId") long customerId, @Param("contractId") long contractId);
//    public Long getStockOutIdById(@Param("Id")Long Id);
//    //public List<Map<>> getListByStockOutId()
//    public Long getIdByNumber(@Param("number") String number,@Param("appId") long appId);
//    public List<Map<Long,Long>> getListByStockOutId(@Param("deviceType") String deviceType,@Param("deviceNumber") String deviceNumber,@Param("tagName") String tagName,@Param("appId") long appId);
//    public List<Map<String,Object>> getListByTagId(long tagId);
//    public String getNameById(long id);
//    public List<Stock_out_device> getListByAppId(long appId);
//    public Long getIdByName(@Param("name") String name, @Param("appId") long appId);
//    public List<Stock_out_device> getInfoByCondition(@Param("name")String name,@Param("number")String number,@Param("deviceTypeId")long deviceTypeId,@Param("appId")long appId);
}
