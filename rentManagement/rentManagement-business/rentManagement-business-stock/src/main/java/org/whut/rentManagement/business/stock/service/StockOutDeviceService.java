package org.whut.rentManagement.business.stock.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.whut.rentManagement.business.stock.entity.StockOutDevice;
import org.whut.rentManagement.business.stock.mapper.StockOutDeviceMapper;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: xiaozhujun
 * Date: 14-11-16
 * Time: 上午11:48
 * To change this template use File | Settings | File Templates.
 */
public class StockOutDeviceService {

    @Autowired
    private StockOutDeviceMapper stockOutDeviceMapper;
    public void add(StockOutDevice stockOutDevice){
        stockOutDeviceMapper.add(stockOutDevice);
    }
    public List<Map<String,String>> getListByAppId(long appId){
        return stockOutDeviceMapper.getListByAppId(appId);
    }
    public int update(StockOutDevice stockOutDevice){
        return stockOutDeviceMapper.update(stockOutDevice);
    }
    public int delete(StockOutDevice stockOutDevice){
        return stockOutDeviceMapper.delete(stockOutDevice);
    }
    public List<Map<String,Object>> listByStockOutId(Map<String,Object> condition){
        return stockOutDeviceMapper.listByStockOutId(condition);
    }
    public void deleteByStockOutId(StockOutDevice stockOutDevice){
        stockOutDeviceMapper.deleteByStockOutId(stockOutDevice);
    }
}
