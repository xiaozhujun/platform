package org.whut.rentManagement.business.stock.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.whut.rentManagement.business.stock.entity.StockIn;
import org.whut.rentManagement.business.stock.mapper.StockInMapper;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: xiaozhujun
 * Date: 14-11-16
 * Time: 上午11:48
 * To change this template use File | Settings | File Templates.
 */
public class StockInService {
    @Autowired
    private StockInMapper stockInMapper;
    public void add(StockIn StockIn){
        stockInMapper.add(StockIn);
    }
    public List<Map<String,String>> getListByAppId(long appId){
        return stockInMapper.getListByAppId(appId);
    }
    public int update(StockIn stockIn){
        return stockInMapper.update(stockIn);
    }
    public int delete(StockIn stockIn){
        return stockInMapper.delete(stockIn);
    }
    public List<Map<String,String>> findByCondition(Map<String,Object> condition){
        return stockInMapper.findByCondition(condition);
    }

    public Map<String,Object> getInfo(Map<String,Object> condition){
        return stockInMapper.getInfo(condition);
    }
    public List<Map<String,Object>> getListByContractId(Long appId,Long contractId){
        return stockInMapper.getListByContractId(appId,contractId);
    }
}
