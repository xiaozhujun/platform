package org.whut.inspectManagement.business.inspectTable.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.whut.inspectManagement.business.inspectTable.mapper.InspectVersionMapper;

/**
 * Created with IntelliJ IDEA.
 * User: yangyang
 * Date: 14-9-10
 * Time: 下午4:56
 * To change this template use File | Settings | File Templates.
 */
public class InspectVersionService {
    @Autowired
    private InspectVersionMapper inspectVersionMapper;

    public Long getVersionCode(long id,long appId) {
        return inspectVersionMapper.getVersionCodeByIdAndAppId(id, appId);
    }

    public String getAddress(long id,long appId) {
        return inspectVersionMapper.getAddressByIdAndAppId(id, appId);
    }
}
