package org.whut.rentManagement.business.contract.mapper;

import org.apache.ibatis.annotations.Param;
import org.whut.platform.fundamental.orm.mapper.AbstractMapper;
import org.whut.rentManagement.business.contract.entity.Installation;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Aaron
 * Date: 14-10-12
 * Time: 下午4:32
 * To change this template use File | Settings | File Templates.
 */
public interface InstallationMapper extends AbstractMapper<Installation> {
    public List<Map<String,String>> getListByAppId(long appId);
    public List<Map<String,String>> findByCondition(Map<String,Object> condition);
    public Map<String,Object> getInfo(Map<String,Object> condition);
    public List<Map<String,Object>> getListByContractId(@Param("appId") Long appId,@Param("contractId") Long contractId);
}