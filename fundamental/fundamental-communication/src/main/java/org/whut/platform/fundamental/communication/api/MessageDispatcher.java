package org.whut.platform.fundamental.communication.api;

/**
 * Created with IntelliJ IDEA.
 * User: xiaozhujun
 * Date: 14-6-5
 * Time: 下午6:19
 * To change this template use File | Settings | File Templates.
 */
public interface MessageDispatcher {
    void dispatchMessage(String messageBody);
    void exceptionProcess();
}
