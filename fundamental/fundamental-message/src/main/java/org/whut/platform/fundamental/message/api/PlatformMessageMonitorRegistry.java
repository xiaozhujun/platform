package org.whut.platform.fundamental.message.api;

import javax.jms.Destination;

/**
 * 本接口用于其他bundle调用，注册监听的队列
 * 
 * @author quanxiwei
 * 
 */
public interface PlatformMessageMonitorRegistry {

	/**
	 * 监听消息
	 * 
	 * @param destination
	 */
	void registerMonitor(Destination destination);

	/**
	 * 取消监听
	 * 
	 * @param destination
	 */
	void removeMonitor(Destination destination);
}
