package org.whut.rentManagement.business.supplier.mapper;

import org.apache.ibatis.annotations.Param;
import org.whut.platform.fundamental.orm.mapper.AbstractMapper;
import org.whut.rentManagement.business.supplier.entity.Supplier;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: xiaoshou
 * Date: 14-10-18
 * Time: 下午8:09
 * To change this template use File | Settings | File Templates.
 */
public interface SupplierMapper extends AbstractMapper<Supplier> {
    public long getIdByName(@Param("name") String name, @Param("appId") long appId);
    public long getIdByTelephone(@Param("telephone") String telephone);
    public List<Supplier> findByCondition(@Param("appId") long appId, @Param("name") String name, @Param("description") String description,
                                          @Param("address") String address, @Param("linkman") String linkman, @Param("telephone") String telephone,
                                          @Param("email") String email, @Param("qq") String qq, @Param("createTime") String createTime);
    public List<Supplier> getByNameAndLinkman(@Param("name") String name, @Param("linkman") String linkman, @Param("appId") long appId);
}
