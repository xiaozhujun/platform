package org.whut.platform.business.address.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.whut.platform.business.address.entity.Address;
import org.whut.platform.business.address.mapper.AddressMapper;

/**
 * Created with IntelliJ IDEA.
 * User: xiaozhujun
 * Date: 14-2-6
 * Time: 下午9:31
 * To change this template use File | Settings | File Templates.
 */
public class AddressService {

    @Autowired
    private AddressMapper mapper;

    public Address findById(long id){
        return mapper.findById(id);
    }
}
