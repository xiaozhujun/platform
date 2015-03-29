package org.whut.platform.fundamental.activemq.broker;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: yangyang
 * Date: 15-3-29
 * Time: 下午5:51
 * To change this template use File | Settings | File Templates.
 */
public class BrokerConfigBean {
    private List<BrokerInfo> brokerInfos;

    public List<BrokerInfo> getBrokerInfos() {
        return brokerInfos;
    }

    public void setBrokerInfos(List<BrokerInfo> brokerInfos) {
        this.brokerInfos = brokerInfos;
    }
}
