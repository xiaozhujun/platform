package org.whut.platform.fundamental.message.api;

public final class MessageConst {
	
	private MessageConst(){
		throw new Error("Utility classes should not instantiated!");
	}
	public static final String REGIST_RELAY_TOPIC = "relay.topic.name";
	public static final String RELAY_MSG_ORI_ADDR = "ORI_ADDR";
	public static final String RELAY_MSG_TRS_ADDR = "TRS_ADDR";
	public static final String RELAY_MSG_TYPE = "TYPE";
}
