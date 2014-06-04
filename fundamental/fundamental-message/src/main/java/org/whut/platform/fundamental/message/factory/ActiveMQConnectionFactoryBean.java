package org.whut.platform.fundamental.message.factory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.FactoryBean;

import org.whut.platform.fundamental.config.Constants;
import org.whut.platform.fundamental.config.FundamentalConfigProvider;

public class ActiveMQConnectionFactoryBean implements
		FactoryBean<ActiveMQConnectionFactory> {

	@Override
	public ActiveMQConnectionFactory getObject() throws Exception {
		String brokerURL = FundamentalConfigProvider
				.get(Constants.JMS_BROKER_URL);
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
				brokerURL);
		return connectionFactory;
	}

	@Override
	public Class<ActiveMQConnectionFactory> getObjectType() {
		return ActiveMQConnectionFactory.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

}
