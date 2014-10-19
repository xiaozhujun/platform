package org.whut.rentManagement.business.contract.mapper;

import org.whut.platform.fundamental.orm.mapper.AbstractMapper;
import org.whut.rentManagement.business.contract.entity.SelfInspect;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-10-14
 * Time: 下午5:09
 * To change this template use File | Settings | File Templates.
 */
public interface SelfInspectMapper  extends AbstractMapper<SelfInspect> {
    public List<SelfInspect> getListByAppId(long appId);
    public List<SelfInspect> findByCondition(Map<String, Object> map);
    public long getContractIdById(long contractId);
    //public String getNameById(long id);
    //public long getIdByName(@Param("name") String name, @Param("appId") long appId);
}