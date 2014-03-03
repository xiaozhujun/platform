package org.whut.platform.business.address.mapper;

import org.whut.platform.business.address.entity.Address;
import org.whut.platform.fundamental.orm.mapper.AbstractMapper;

/**
 * Created with IntelliJ IDEA.
 * User: xiaozhujun
 * Date: 14-2-6
 * Time: 下午9:30
 * To change this template use File | Settings | File Templates.
 */
public interface AddressMapper extends AbstractMapper {
    public Address findById(long id);
    public Address findByArea(String area);

}
