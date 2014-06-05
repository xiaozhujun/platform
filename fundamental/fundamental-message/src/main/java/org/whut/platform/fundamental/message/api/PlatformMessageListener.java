package org.whut.platform.fundamental.message.api;

import javax.jms.MessageListener;

public interface PlatformMessageListener extends MessageListener {

	/**
	 * 返回需要监听的消息名称
	 * 
	 * @return
	 */
	String getMessageName();
}
