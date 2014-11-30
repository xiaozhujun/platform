package org.whut.rentManagement.business.contract.mapper;

import org.apache.ibatis.annotations.Param;
import org.whut.platform.fundamental.orm.mapper.AbstractMapper;
import org.whut.rentManagement.business.contract.entity.Remove;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-10-14
 * Time: 下午5:08
 * To change this template use File | Settings | File Templates.
 */
public interface RemoveMapper extends AbstractMapper<Remove> {


    public List<Map<String,String>> getListByAppId(long appId);
    public List<Map<String,String>> getListByContractId(long contractId);
    public List<Map<String,String>> getListByDeviceId(long deviceId);
    public long getIdByContractIdAndDeviceIdAndAppId(@Param("contractId") long contractId, @Param("deviceId") long deviceId, @Param("appId") long appId);

    public long getRemoveDeviceIdById(long removeDeviceId);
    public List<Map<String,String>> findByCondition(Map<String,Object> condition);
    public Map<String,Object> getInfo(Map<String,Object> condition);
    public List<Map<String,Object>> getListByContractId(@Param("appId") Long appId,@Param("contractId") Long contractId);


}
