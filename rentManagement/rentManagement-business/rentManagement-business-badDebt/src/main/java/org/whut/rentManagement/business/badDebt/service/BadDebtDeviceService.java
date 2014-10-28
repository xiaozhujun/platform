package org.whut.rentManagement.business.badDebt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.whut.rentManagement.business.badDebt.entity.BadDebtDevice;
import org.whut.rentManagement.business.badDebt.mapper.BadDebtDeviceMapper;

/**
 * Created with IntelliJ IDEA.
 * User: cwb
 * Date: 14-10-11
 * Time: 上午10:48
 * To change this template use File | Settings | File Templates.
 */
public class BadDebtDeviceService {
    @Autowired
    private BadDebtDeviceMapper badDebtDeviceMapper;

    public void add(BadDebtDevice badDebtDevice){
        badDebtDeviceMapper.add(badDebtDevice);
    }

    public void update(BadDebtDevice badDebtDevice){
        badDebtDeviceMapper.update(badDebtDevice);
    }

    public void delete(BadDebtDevice badDebtDevice){
        badDebtDeviceMapper.delete(badDebtDevice);
    }

    public int deleteByBadDebtId(long badDebtId){
        return badDebtDeviceMapper.deleteByBadDebtId(badDebtId);
    }
}
