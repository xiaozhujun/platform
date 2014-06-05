package org.whut.platform.fundamental.message.relay.usage;

import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import org.whut.platform.fundamental.config.FundamentalConfigProvider;
import org.whut.platform.fundamental.message.api.MessageConst;
import org.whut.platform.fundamental.message.api.PlatformMessageMonitorRegistry;

/**
 * 成为转发者，需要此类。此类在受spring托管之后，将自动注册对中转topic的监听。这样，包含这个类的par包，就具备监听topic的功能，
 * 也就可以成为转发者
 * 
 * @author quanxiwei
 * 
 */
public class BeForwarderRegistry implements InitializingBean {
	@Autowired
	private PlatformMessageMonitorRegistry platformMessageMonitorRegistry;

	@Override
	public void afterPropertiesSet() throws Exception {
		ActiveMQTopic topic = new ActiveMQTopic(
				FundamentalConfigProvider.get(MessageConst.REGIST_RELAY_TOPIC));
        platformMessageMonitorRegistry.registerMonitor(topic);
	}

}
