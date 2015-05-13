package org.whut.platform.fundamental.activemq.test;

import org.whut.platform.fundamental.activemq.consumer.PooledSessionListenerBase;
import org.whut.platform.fundamental.logger.PlatformLogger;

import javax.jms.Message;

/**
 * Created with IntelliJ IDEA.
 * User: yangyang
 * Date: 15-5-7
 * Time: 下午4:18
 * To change this template use File | Settings | File Templates.
 */
public class TestSessionListener extends PooledSessionListenerBase {
    private static final PlatformLogger LOG = PlatformLogger.getLogger(TestSessionListener.class);

    @Override
    public void onMessage(Message message) {
        LOG.info("original:" + message);
        super.onMessage(message);    //To change body of overridden methods use File | Settings | File Templates.
    }
}
