package org.whut.platform.fundamental.message.relay.usage;

import org.springframework.beans.factory.annotation.Autowired;
import org.whut.platform.fundamental.message.api.MessageConst;
import org.whut.platform.fundamental.message.api.PlatformMessageRelayRegistry;
import org.whut.platform.fundamental.message.impl.PlatformMessageListenerBase;

import javax.jms.Message;

/**
 * 这个类监听topic。收到消息，将消息放到PeixunMessageRelayRegistry的addressInfoList中。这样，
 * 当fundament的message收到任何消息时
 * ，distributer会调用PeixunMessageRelayRegistry，查看有无转发的list 有则从list中查找本消息是否需要转发<br/>
 * 用法： 将本类暴露成PeixunMessageListener的服务。<br/>
 * <blockquote>
 * 
 * <pre>
 * <osgi:service ref="realyRegistTopicListener"
 * interface="org.whut.platform.fundamental.message.api.PlatformMessageListener" /> <br/>
 * </pre>
 * 
 * </blockquote> <br/>
 * 暴露成服务，才可能被distribute通知，监听到消息
 * 
 * @author quanxiwei
 * 
 */
public class RelayRegisterTopicListener extends PlatformMessageListenerBase {

	@Autowired
	private PlatformMessageRelayRegistry messageRelayRegistry;

	@Override
	public void onMessage(Message message) {
		messageRelayRegistry.registerAddress(message);
	}

	@Override
	public String getMessageName() {
		return MessageConst.REGIST_RELAY_TOPIC;
	}

}
