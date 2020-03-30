package com.tradingdemo.trading.exception;
/**
 * Exception to be thrown when the quanity user wants to purchase is not available.
 * @author Ruchi
 *
 */
public class StockNotAvailableException extends Exception {

	private static final long serialVersionUID = 1L;
	private Integer errorCode;

	public StockNotAvailableException(String message, Integer errorCode) {
		super(message);
		this.errorCode = errorCode;

	}

	public Integer getErrorCode() {
		return errorCode;
	}

}
