package org.whut.rentManagement.business.stock.entity;

/**
 * Created with IntelliJ IDEA.
 * User: xiaozhujun
 * Date: 14-11-16
 * Time: 上午11:37
 * To change this template use File | Settings | File Templates.
 */
public class StockOutDevice {
    private long id;
    private long stockOutId;
    private long deviceId;

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
