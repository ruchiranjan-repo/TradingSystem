package com.tradingdemo.trading.exception;

/**
 * Exception occurs when stock with given stock id not found.
 * 
 * @author Ruchi
 *
 */
public class StockNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;
	private  final Integer errorCode;

	public StockNotFoundException(String  message, Integer errorCode) {
		super(message);
		this.errorCode = errorCode;

	}

	public Integer getErrorCode() {
		return errorCode;
	}

}
