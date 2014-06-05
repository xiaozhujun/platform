package org.whut.platform.fundamental.message.api;

/**
 * 分发信息
 * 
 * @author quanxiwei
 * 
 */
public class RelayAddressInfo {

	private String oriAddress;// 原始的消息地址
	private String disAddress;// 转发出去的消息地址
	private int type;// 消息类型，topic=1，queue=2

	public RelayAddressInfo(String oriAddress, String disAddress, int type) {
		this.oriAddress = oriAddress;
		this.disAddress = disAddress;
		this.type = type;
	}

	public String getOriAddress() {
		return oriAddress;
	}

	public void setOriAddress(String oriAddress) {
		this.oriAddress = oriAddress;
	}

	public String getDisAddress() {
		return disAddress;
	}

	public void setDisAddress(String disAddress) {
		this.disAddress = disAddress;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "RelayAddressInfo [oriAddress=" + oriAddress + ", disAddress="
				+ disAddress + ", type=" + type + "]";
	}

}
