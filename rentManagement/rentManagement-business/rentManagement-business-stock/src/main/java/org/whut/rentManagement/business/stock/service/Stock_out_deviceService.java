package org.whut.rentManagement.business.stock.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.whut.rentManagement.business.stock.entity.Stock_out_device;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-10-10
 * Time: 上午10:32
 * To change this template use File | Settings | File Templates.
 */
public class Stock_out_deviceService {
    @Autowired
    private org.whut.rentManagement.business.stock.mapper.Stock_out_deviceMapper stock_out_deviceMapper;
    public void add(Stock_out_device stock_out_device){
        stock_out_deviceMapper.add(stock_out_device);
    }
    public long getIdByStockOutIdAndDeviceId(long stockOutId,long deviceId){
        return stock_out_deviceMapper.getIdByStockOutIdAndDeviceId(stockOutId,deviceId);

     }
    public int update(Stock_out_device stock_out_device){
        return stock_out_deviceMapper.update(stock_out_device);
    }
    public int delete(Stock_out_device stock_out_device){
        return stock_out_deviceMapper.delete(stock_out_device);
    }
}
