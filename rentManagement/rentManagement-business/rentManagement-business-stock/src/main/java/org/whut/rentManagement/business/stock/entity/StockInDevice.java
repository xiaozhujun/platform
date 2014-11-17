package org.whut.rentManagement.business.stock.entity;

/**
 * Created with IntelliJ IDEA.
 * User: xiaozhujun
 * Date: 14-11-16
 * Time: 上午11:36
 * To change this template use File | Settings | File Templates.
 */
public class StockInDevice {
    private long id;
    private long stockInId;
    private long deviceId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getStockInId() {
        return stockInId;
    }

    public void setStockInId(long stockInId) {
        this.stockInId = stockInId;
    }

    public long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(long deviceId) {
        this.deviceId = deviceId;
    }
}
