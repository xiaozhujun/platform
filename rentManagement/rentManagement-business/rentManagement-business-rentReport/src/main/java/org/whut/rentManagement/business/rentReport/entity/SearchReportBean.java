package org.whut.rentManagement.business.rentReport.entity;

/**
 * Created with IntelliJ IDEA.
 * User: cwb
 * Date: 14-11-21
 * Time: 下午11:45
 * To change this template use File | Settings | File Templates.
 */
public class SearchReportBean {
    private String devnum;
    private String devname;
    private String devtype;
    private String price;
    private String priceunit;
    private String producetime;
    private String devstatus;
    private String devaddress;

    public String getDevnum() {
        return devnum;
    }

    public void setDevnum(String devnum) {
        this.devnum = devnum;
    }

    public String getDevname() {
        return devname;
    }

    public void setDevname(String devname) {
        this.devname = devname;
    }

    public String getDevtype() {
        return devtype;
    }

    public void setDevtype(String devtype) {
        this.devtype = devtype;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPriceunit() {
        return priceunit;
    }

    public void setPriceunit(String priceunit) {
        this.priceunit = priceunit;
    }

    public String getProducetime() {
        return producetime;
    }

    public void setProducetime(String producetime) {
        this.producetime = producetime;
    }

    public String getDevstatus() {
        return devstatus;
    }

    public void setDevstatus(String devstatus) {
        this.devstatus = devstatus;
    }

    public String getDevaddress() {
        return devaddress;
    }

    public void setDevaddress(String devaddress) {
        this.devaddress = devaddress;
    }
}
