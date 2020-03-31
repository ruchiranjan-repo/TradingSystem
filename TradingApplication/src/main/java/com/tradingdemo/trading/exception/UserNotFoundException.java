package com.tradingdemo.trading.exception;

/**
 * Exception occurs when user with given user id not found.
 * 
 * @author Ruchi
 *
 */
public class UserNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;
	private final Integer errorCode;

	public UserNotFoundException(String  message, Integer errorCode) {
		super(message);
		this.errorCode = errorCode;

	}

	public Integer getErrorCode() {
		return errorCode;
	}

}
