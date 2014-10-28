package org.whut.rentManagement.business.badDebt.mapper;

import org.apache.ibatis.annotations.Param;
import org.whut.platform.fundamental.orm.mapper.AbstractMapper;
import org.whut.rentManagement.business.badDebt.entity.BadDebtSheet;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: cwb
 * Date: 14-10-9
 * Time: 下午4:36
 * To change this template use File | Settings | File Templates.
 */
public interface BadDebtSheetMapper extends AbstractMapper<BadDebtSheet> {
    //public List<BadDebtSheet> getBadDebtSheetListByAppId(long appId);//方法名与Mapper.xml中的id值相同
    public List<Map<String,Object>> getListByAppId(long appId);
    public Long getBadDebtId(@Param("number") String number,@Param("carNumber") String carNumber,@Param("customerId") long customerId,@Param("contractId") long contractId,@Param("storehouseId")long storehouseId,@Param("appId")long appId);
    public BadDebtSheet getById(long id);
}
