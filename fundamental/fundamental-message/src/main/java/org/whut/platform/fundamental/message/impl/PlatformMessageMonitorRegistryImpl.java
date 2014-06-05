package org.whut.platform.fundamental.message.impl;

import org.whut.platform.fundamental.logger.PlatformLogger;
import org.whut.platform.fundamental.message.api.PlatformMessageMonitorRegistry;

import javax.jms.Destination;

public class PlatformMessageMonitorRegistryImpl implements PlatformMessageMonitorRegistry {
	private static PlatformLogger logger = PlatformLogger
			.getLogger(PlatformMessageMonitorRegistryImpl.class);
	
	private PlatformMessageConsumerImpl consumer;

	public void setConsumer(PlatformMessageConsumerImpl consumer) {
		this.consumer = consumer;
	}

	@Override
	public void registerMonitor(Destination destination) {
		if (consumer == null) {
			logger.error("consumer is null,can't registerMonitor");
			return;
		}
		consumer.doMonitor(destination);
	}

	@Override
	public void removeMonitor(Destination destination) {
		if (consumer == null){			
			return;
		}
		consumer.removeMonitor(destination);
	}

}
