package org.whut.rentManagement.business.contract.mapper;

import org.apache.ibatis.annotations.Param;
import org.whut.platform.fundamental.orm.mapper.AbstractMapper;
import org.whut.rentManagement.business.contract.entity.Contract;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: cww
 * Date: 14-10-13
 * Time: 下午1:19
 * To change this template use File | Settings | File Templates.
 */
public interface ContractMapper extends AbstractMapper<Contract> {
    public List<Map<String,Object>> getListByAppId(@Param("appId")long appId) ;
    public Contract getContractById(@Param("id")long id,@Param("appId")long appId);
    public int deleteById(@Param("id")long id,@Param("appId")long appId);

    public List<Map<String,String>> getContractList(Map<String,Object> condition);
}
