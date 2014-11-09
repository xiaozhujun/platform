package org.whut.rentManagement.business.stock.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.whut.rentManagement.business.stock.entity.Stock_out_sheet;
import org.whut.rentManagement.business.stock.mapper.Stock_out_sheetMapper;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-10-10
 * Time: 下午8:34
 * To change this template use File | Settings | File Templates.
 */
public class Stock_out_sheetService {
    @Autowired
   private Stock_out_sheetMapper stock_out_sheetMapper;
    public void add(Stock_out_sheet stock_out_sheet){
        stock_out_sheetMapper.add(stock_out_sheet);
    }
    public int update(Stock_out_sheet stock_out_sheet){
        return stock_out_sheetMapper.update(stock_out_sheet);
    }
    public int delete(Stock_out_sheet stock_out_sheet){
        return stock_out_sheetMapper.delete(stock_out_sheet);
    }
    public long getIdByCustomerIdAndContractId(String customerId,String contractId){
        return stock_out_sheetMapper.getIdByCustomerIdAndContractId(customerId,contractId);
    }
    public List<Stock_out_sheet> list(long appId,long customerId,long contractId,long storehouseId){
        return stock_out_sheetMapper.findByCondition(appId, customerId, contractId,storehouseId);
    }
    public Long getStockOutId(String number,String carNumber,long customerId,long contractId,long storehouseId,long appId){
        return stock_out_sheetMapper.getDeviceId(number,carNumber,customerId,contractId,storehouseId,appId);
    }
    public List<Stock_out_sheet> getListByAppId(long appId){
        return stock_out_sheetMapper.getListByAppId(appId);
    }
//    public List<Map<String,String>> getListByCondition(long appId,long customerId,long contractId,long storehouseId){
//        return stock_out_sheetMapper.getListByCondition(appId,customerId,contractId,storehouseId);
//    }


    public List<Map<String,String>> getOutStockList(Map<String,Object> condition){
        return stock_out_sheetMapper.getOutStockList(condition);
    }
}
