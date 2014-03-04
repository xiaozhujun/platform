package org.whut.platform.fundamental.exception;

/**
 * 未找到对象异常，对应 CODE.NOTFOUND
 * 
 * @author quanxiwei
 * 
 */
public class NotFoundException extends RuntimeException {

	private static final long serialVersionUID = -322684921370765557L;

	public NotFoundException() {
		super();
	}

	public NotFoundException(String message, Throwable throwable) {
		super(message, throwable);
	}

	public NotFoundException(Throwable throwable) {
		super(throwable);
	}

}
