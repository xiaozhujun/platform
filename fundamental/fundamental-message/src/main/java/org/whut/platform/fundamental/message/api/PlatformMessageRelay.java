package org.whut.platform.fundamental.message.api;

import javax.jms.Message;

/**
 * 消息中转
 * 
 * @author quanxiwei
 * 
 */
public interface PlatformMessageRelay {
	/**
	 * 转发。将消息转发出去
	 * 
	 * @param message
	 * @param msgName
	 * @return
	 */
	boolean transmit(Message message, String msgName);

}
