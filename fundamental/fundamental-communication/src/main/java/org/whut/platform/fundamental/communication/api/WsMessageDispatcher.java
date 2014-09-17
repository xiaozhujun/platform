package org.whut.platform.fundamental.communication.api;

/**
 * Created with IntelliJ IDEA.
 * User: tgq
 * Date: 14-9-3
 * Time: 上午10:08
 * To change this template use File | Settings | File Templates.
 */
public interface WsMessageDispatcher {
    void dispatchMessage(String messageBody);
    void exceptionProcess();
}
