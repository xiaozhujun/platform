package org.whut.platform.fundamental.message.factory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.FactoryBean;

import org.whut.platform.fundamental.config.Constants;
import org.whut.platform.fundamental.config.FundamentalConfigProvider;
import org.whut.platform.fundamental.logger.PlatformLogger;

public class ActiveMQConnectionFactoryBean implements
		FactoryBean<ActiveMQConnectionFactory> {

    private static final PlatformLogger logger = PlatformLogger.getLogger(ActiveMQConnectionFactoryBean.class);

	@Override
	public ActiveMQConnectionFactory getObject() throws Exception {
		String brokerURL = FundamentalConfigProvider
				.get(Constants.JMS_BROKER_URL);
        logger.info("jms broker url: "+Constants.JMS_BROKER_URL);
        System.out.println("jms broker url: "+Constants.JMS_BROKER_URL);
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
