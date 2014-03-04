package org.whut.platform.fundamental.exception;
/**
 * 重复异常，对应Code.DUPLICATE
 * @author quanxiwei
 *
 */
public class DuplicateException extends RuntimeException {

	private static final long serialVersionUID = -322684921370765557L;

	public DuplicateException() {
		super();
	}

	public DuplicateException(String message, Throwable throwable) {
		super(message, throwable);
	}

	public DuplicateException(Throwable throwable) {
		super(throwable);
	}
}
