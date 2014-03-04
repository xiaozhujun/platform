package org.whut.platform.fundamental.exception;

/**
 * business 统一异常。
 * 
 * @author quanxiwei
 * 
 */
public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = -6569214816469303340L;

	public BusinessException() {
		super();
	}

	public BusinessException(String message, Throwable throwable) {
		super(message, throwable);
	}

	public BusinessException(Throwable throwable) {
		super(throwable);
	}

}
