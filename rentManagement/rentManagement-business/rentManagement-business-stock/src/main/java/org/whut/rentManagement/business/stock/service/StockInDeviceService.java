package org.whut.rentManagement.business.stock.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.whut.rentManagement.business.stock.entity.StockInDevice;
import org.whut.rentManagement.business.stock.mapper.StockInDeviceMapper;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: xiaozhujun
 * Date: 14-11-16
 * Time: 上午11:48
 * To change this template use File | Settings | File Templates.
 */
public class StockInDeviceService {

    @Autowired
    private StockInDeviceMapper stockInDeviceMapper;
    public void add(StockInDevice stockInDevice){
        stockInDeviceMapper.add(stockInDevice);
    }
    public List<Map<String,String>> getListByAppId(long appId){
        return stockInDeviceMapper.getListByAppId(appId);
    }
    public int update(StockInDevice stockInDevice){
        return stockInDeviceMapper.update(stockInDevice);
    }
    public int delete(StockInDevice stockInDevice){
        return stockInDeviceMapper.delete(stockInDevice);
    }
    public List<Map<String,Object>> listByStockInId(Map<String,Object> condition){
        return stockInDeviceMapper.listByStockInId(condition);
    }
    public void deleteByStockInId(StockInDevice stockInDevice){
        stockInDeviceMapper.deleteByStockInId(stockInDevice);
    }
}
