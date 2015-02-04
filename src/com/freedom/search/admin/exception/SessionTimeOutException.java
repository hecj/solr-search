package com.freedom.search.admin.exception;
/**
 * 未登陆异常
 */
public class SessionTimeOutException extends RuntimeException {

	/**
	 * @Fields serialVersionUID : TODO
	 */
	
	private static final long serialVersionUID = 1L;

	public SessionTimeOutException() {
		super();
	}

	public SessionTimeOutException(String message, Throwable cause) {
		super(message, cause);
	}

	public SessionTimeOutException(String message) {
		super(message);
	}

	public SessionTimeOutException(Throwable cause) {
		super(cause);
	}

	
}
