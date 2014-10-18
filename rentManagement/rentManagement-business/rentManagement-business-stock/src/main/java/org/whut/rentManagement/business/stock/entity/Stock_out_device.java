package org.whut.rentManagement.business.stock.entity;
/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-10-10
 * Time: 上午10:05
 * To change this template use File | Settings | File Templates.
 */
public class Stock_out_device {
    private long id;
    private long  stockOutId;
    private long  deviceId;
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getStockOutId() {
        return stockOutId;
    }

    public void setStockOutId(long stockOutId) {
        this.stockOutId = stockOutId;
    }

    public long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(long deviceId) {
        this.deviceId = deviceId;
    }


}
