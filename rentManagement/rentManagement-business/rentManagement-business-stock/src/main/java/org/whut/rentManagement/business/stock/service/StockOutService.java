package org.whut.rentManagement.business.stock.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.whut.rentManagement.business.stock.entity.StockOut;
import org.whut.rentManagement.business.stock.mapper.StockOutMapper;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: xiaozhujun
 * Date: 14-11-16
 * Time: 上午11:48
 * To change this template use File | Settings | File Templates.
 */
public class StockOutService {
    @Autowired
    private StockOutMapper stockOutMapper;
    public void add(StockOut StockOut){
        stockOutMapper.add(StockOut);
    }
    public List<Map<String,String>> getListByAppId(long appId){
        return stockOutMapper.getListByAppId(appId);
    }
    public int update(StockOut stockOut){
        return stockOutMapper.update(stockOut);
    }
    public int delete(StockOut stockOut){
        return stockOutMapper.delete(stockOut);
    }
    public List<Map<String,String>> findByCondition(Map<String,Object> condition){
        return stockOutMapper.findByCondition(condition);
    }

    public Map<String,Object> getInfo(Map<String,Object> condition){
        return stockOutMapper.getInfo(condition);
    }
    public List<Map<String,Object>> getListByContractId(Long appId,Long contractId){
        return stockOutMapper.getListByContractId(appId,contractId);
    }
}
