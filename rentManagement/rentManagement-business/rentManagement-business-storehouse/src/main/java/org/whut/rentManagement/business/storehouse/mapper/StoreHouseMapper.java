package org.whut.rentManagement.business.storehouse.mapper;

import org.apache.ibatis.annotations.Param;
import org.whut.platform.fundamental.orm.mapper.AbstractMapper;
import org.whut.rentManagement.business.storehouse.entity.StoreHouse;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Steven
 * Date: 14-10-10
 * Time: 下午1:04
 * To change this template use File | Settings | File Templates.
 */
public interface StoreHouseMapper extends AbstractMapper<StoreHouse> {
    public long getIdByNameAndAppId(@Param("name") String name, @Param("appId") long appId);
    public int deleteById(long id);
    public List<StoreHouse> getListByAppId(long appId);
}
