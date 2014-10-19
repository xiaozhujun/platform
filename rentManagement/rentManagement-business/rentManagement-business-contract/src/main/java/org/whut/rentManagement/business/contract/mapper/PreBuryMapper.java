package org.whut.rentManagement.business.contract.mapper;

import org.apache.ibatis.annotations.Param;
import org.whut.platform.fundamental.orm.mapper.AbstractMapper;
import org.whut.rentManagement.business.contract.entity.PreBury;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Aaron
 * Date: 14-10-18
 * Time: 下午4:31
 * To change this template use File | Settings | File Templates.
 */
public interface PreBuryMapper extends AbstractMapper<PreBury>{
    public List<Map<String,String>> getListByAppId(long appId);
    public long getIdByContractId(@Param("contractId") long contractId);
}
