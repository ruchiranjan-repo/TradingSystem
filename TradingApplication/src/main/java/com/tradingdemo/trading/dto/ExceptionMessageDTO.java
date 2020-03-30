package com.tradingdemo.trading.dto;
/**
 * Exception message DtO to be used to send  exception details.
 * @author Ruchi
 *
 */

public class ExceptionMessageDTO {

	private String message;
	private Integer errorCode;

	public ExceptionMessageDTO() {

	}

	public ExceptionMessageDTO(String message, Integer errorCode) {
		super();
		this.message = message;
		this.errorCode = errorCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}

}
