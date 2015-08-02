package org.whut.platform.fundamental.activemq.config;

/**
 * Created with IntelliJ IDEA.
 * User: YangRichard
 * Date: 15-4-15
 * Time: 下午7:30
 * To change this template use File | Settings | File Templates.
 */
public class SessionFactoryConfig {
    public static final int MAX_SIZE = 50;
    public static final int MAX_SESSION = 30;
    public static final int BROKER_NUM = 3;
    public static final int LISTENER_NUM = 3;
    public String[] BROKER_NAMES = {"localhostA", "localhostB", "localhostC"};
    public String[] BROKER_URLS = {"tcp://localhost:61616?transport.useAsyncSend=true", "tcp://localhost:61617?transport.useAsyncSend=true", "tcp://localhost:61618?transport.useAsyncSend=true"};

    public String[] getBROKER_NAMES() {
        return BROKER_NAMES;
    }

    public void setBROKER_NAMES(String[] BROKER_NAMES) {
        this.BROKER_NAMES = BROKER_NAMES;
    }

    public String[] getBROKER_URLS() {
        return BROKER_URLS;
    }

    public void setBROKER_URLS(String[] BROKER_URLS) {
        this.BROKER_URLS = BROKER_URLS;
    }
}
