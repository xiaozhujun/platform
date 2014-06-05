package org.whut.platform.fundamental.message.relay;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.whut.platform.fundamental.logger.PlatformLogger;
import org.whut.platform.fundamental.message.api.*;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PlatformMessageRelayImpl implements PlatformMessageRelay,
        PlatformMessageRelayRegistry {
	private List<RelayAddressInfo> addressInfoList = new ArrayList<RelayAddressInfo>();

	private static final PlatformLogger LOGGER = PlatformLogger
			.getLogger(PlatformMessageRelayImpl.class);

	private PlatformMessageProducer platformMessageProducer;

	public void setPeixunMessageProducer(
			PlatformMessageProducer platformMessageProducer) {
		this.platformMessageProducer = platformMessageProducer;
	}

	@Override
	public boolean transmit(Message message, String msgName) {
		RelayAddressInfo distributeAddress = findDistributeAddress(msgName);
		if (distributeAddress == null
				|| !distributeAddress.getOriAddress().equals(msgName)) {
			return false;
		}
		String dis = distributeAddress.getDisAddress();
		int type = distributeAddress.getType();

		Destination destination = null;
		if (type == 1) {
			destination = new ActiveMQQueue(dis);
		} else if (type == 2) {
			destination = new ActiveMQTopic(dis);
		}

		LOGGER.info("do transmit: from {} to {}",
				new Object[] { distributeAddress.getOriAddress(),
						distributeAddress.getDisAddress(), message });
		platformMessageProducer.sendMessage(destination, message);

		return true;
	}

	private RelayAddressInfo findDistributeAddress(String msgName) {
		if (addressInfoList == null) {
			return null;
		}
		//这个其实用get index的方式遍历也许更快.
		for (Iterator<RelayAddressInfo> iterator = addressInfoList.iterator(); iterator
				.hasNext();) {
			RelayAddressInfo addressInfo = iterator.next();
			if (addressInfo.getOriAddress().equals(msgName)) {
				return addressInfo;
			}
		}
		return null;
	}

	@Override
	public void registerAddress(Message message) {
		MapMessage map = (MapMessage) message;
		try {
			LOGGER.info("receive transmit register msg:{}",
					new Object[] { message });
			String ori = map.getString(MessageConst.RELAY_MSG_ORI_ADDR);
			String dis = map.getString(MessageConst.RELAY_MSG_TRS_ADDR);
			int type = map.getInt(MessageConst.RELAY_MSG_TYPE);

			RelayAddressInfo addressInfo = new RelayAddressInfo(ori, dis, type);
			synchronized (addressInfoList) {
				addressInfoList.add(addressInfo);
			}
		} catch (JMSException e) {
			LOGGER.error("error:", e);
		}
	}

}
