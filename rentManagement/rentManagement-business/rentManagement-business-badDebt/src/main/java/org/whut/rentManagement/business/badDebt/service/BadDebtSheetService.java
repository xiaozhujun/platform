package org.whut.rentManagement.business.badDebt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.whut.rentManagement.business.badDebt.entity.BadDebtSheet;
import org.whut.rentManagement.business.badDebt.mapper.BadDebtSheetMapper;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: cwb
 * Date: 14-10-9
 * Time: 下午4:56
 * To change this template use File | Settings | File Templates.
 */
public class BadDebtSheetService {
    @Autowired
    private BadDebtSheetMapper badDebtSheetMapper;     //面向接口编程

    public void add(BadDebtSheet badDebtSheet){
        badDebtSheetMapper.add(badDebtSheet);
    }

    public void update(BadDebtSheet badDebtSheet){
        badDebtSheetMapper.update(badDebtSheet);
    }

    public void delete(BadDebtSheet badDebtSheet){
        badDebtSheetMapper.delete(badDebtSheet);
    }

    public List<Map<String,Object>> getListByAppId(long appId){
        return badDebtSheetMapper.getListByAppId(appId);
    }

    public Long getBadDebtId(String number,String carNumber,long customerId,long contractId,long storehouseId,long appId){
        return badDebtSheetMapper.getBadDebtId(number,carNumber,customerId,contractId,storehouseId,appId);
    }

    public BadDebtSheet getById(long id){
        return badDebtSheetMapper.getById(id);
    }
}
