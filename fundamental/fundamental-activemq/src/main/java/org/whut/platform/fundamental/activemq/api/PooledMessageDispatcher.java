package org.whut.platform.fundamental.activemq.api;

/**
 * Created with IntelliJ IDEA.
 * User: yangyang
 * Date: 15-3-15
 * Time: 下午4:24
 * To change this template use File | Settings | File Templates.
 */
public interface PooledMessageDispatcher {
    /**
     * 捕获消息
     * @param messageBody
     */
    public void dispatchMessage(String messageBody);

    /**
     * 异常处理
     */
    public void exceptionProcess();
}
