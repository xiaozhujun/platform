package org.whut.platform.fundamental.message.api;

import javax.jms.Message;

public interface PlatformMessageRelayRegistry {
	/**
	 * 给中继器添加转发映射
	 * 
	 * @param message
	 */
	void registerAddress(Message message);
}
