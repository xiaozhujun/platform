package org.whut.platform.fundamental.activemq.broker;

/**
 * Created with IntelliJ IDEA.
 * User: yangyang
 * Date: 15-3-29
 * Time: 下午5:57
 * To change this template use File | Settings | File Templates.
 */
public class BrokerInfo {
    public static final String USERNAME = "admin";
    public static final String PASSWORD = "admin";
    public static final int MAX_CONNECTIONS = 500;
    public static final int MAXIMUN_Active = 50;
    public static final boolean BLOCKED = true;
    private String brokerAddr;
    private String brokerPort;
    private String brokerName;
    private String monitorPort;

    public String getBrokerAddr() {
        return brokerAddr;
    }

    public void setBrokerAddr(String brokerAddr) {
        this.brokerAddr = brokerAddr;
    }

    public String getBrokerPort() {
        return brokerPort;
    }

    public void setBrokerPort(String brokerPort) {
        this.brokerPort = brokerPort;
    }

    public String getBrokerName() {
        return brokerName;
    }

    public void setBrokerName(String brokerName) {
        this.brokerName = brokerName;
    }

    public String getMonitorPort() {
        return monitorPort;
    }

    public void setMonitorPort(String monitorPort) {
        this.monitorPort = monitorPort;
    }

}
