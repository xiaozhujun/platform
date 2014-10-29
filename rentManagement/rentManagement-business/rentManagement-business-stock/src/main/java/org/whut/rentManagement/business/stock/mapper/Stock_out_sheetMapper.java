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
    public long getIdByCustomerIdAndContractId(@Param("customerId") String customerId, @Param("contractId") String contractId);
    public Long getDeviceId(@Param("number") String number,@Param("carNumber") String carNumber,@Param("customerId") long customerId,@Param("contractId") long contractId,@Param("storehouseId")long storehouseId,@Param("appId")long appId);
}
