package org.whut.platform.fundamental.activemq.api;

import javax.jms.Session;

/**
 * Created with IntelliJ IDEA.
 * User: YangRichard
 * Date: 15-4-15
 * Time: 下午10:00
 * To change this template use File | Settings | File Templates.
 */
public interface PooledSessionManager {
    public Session acquireSession();
    public void releaseSession(Session session);
}
