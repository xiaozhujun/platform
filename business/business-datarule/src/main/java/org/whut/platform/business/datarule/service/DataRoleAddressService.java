package org.whut.platform.business.datarule.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.whut.platform.business.datarule.entity.DataRoleAddress;

/**
 * Created with IntelliJ IDEA.
 * User: John
 * Date: 14-4-21
 * Time: 下午2:41
 * To change this template use File | Settings | File Templates.
 */

public class DataRoleAddressService {
    @Autowired
    private org.whut.platform.business.datarule.mapper.DataRoleAddressMapper dataRoleAddressMapper;
    public void add(DataRoleAddress dataRoleAddress){
       dataRoleAddressMapper.add(dataRoleAddress);
    }

}
