package org.whut.rentManagement.business.customer.mapper;

import org.apache.ibatis.annotations.Param;
import org.whut.platform.fundamental.orm.mapper.AbstractMapper;
import org.whut.rentManagement.business.customer.entity.Customer;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: xiaoshou
 * Date: 14-10-10
 * Time: 下午5:07
 * To change this template use File | Settings | File Templates.
 */
public interface CustomerMapper extends AbstractMapper<Customer> {
    public long getIdByName(@Param("name") String name, @Param("appId") long appId);
    public String getNameById(@Param("id") long id, @Param("appId") long appId);
    public long getIdByTelephone(@Param("telephone") String telephone);
    public List<Customer> findByCondition(@Param("appId") long appId, @Param("name") String name, @Param("description") String description,
                                          @Param("address") String address, @Param("linkman") String linkman, @Param("telephone") String telephone,
                                          @Param("email") String email, @Param("qq") String qq, @Param("bank") String bank,
                                          @Param("bankAccount") String bankAccount, @Param("createTime") String createTime);
    public List<Customer> getByNameAndLinkman(@Param("name") String name, @Param("linkman") String linkman, @Param("appId") long appId);
}
