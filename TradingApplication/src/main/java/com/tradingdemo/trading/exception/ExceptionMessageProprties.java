package com.tradingdemo.trading.exception;
/**
 *  class for reading the exception message and error code from property file
 */

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:messages.properties")
public class ExceptionMessageProprties {

	@Value("${com.trading.exception.usernotfound.message}")
	private String userNotFoundMessage;

	@Value("${com.trading.exception.usernotfound.errorcode}")
	private Integer userNotFoundErrorCode;

	@Value("${com.trading.exception.stocknotfound.message}")
	private String stockNotFoundMessage;

	@Value("${com.trading.exception.stocknotfound.errorcode}")
	private Integer stockNotFoundErrorCode;

	@Value("${com.trading.exception.stocknotavailable.message}")
	private String stockNotAvailableMessage;

	@Value("${com.trading.exception.stocknotavailable.errorcode}")
	private Integer stockNotAvailableErrorCode;

	@Value("${com.trading.exception.stocknamenotfound.message}")
	private String stockNameNotFoundMessage;
	
	@Value("${com.trading.exception.stocknamenotfound.errorcode}")
	private Integer stockNameNotFoundErrorCode;

	public String getUserNotFoundMessage() {
		return userNotFoundMessage;
	}

	public Integer getUserNotFoundErrorCode() {
		return userNotFoundErrorCode;
	}

	public String getStockNotFoundMessage() {
		return stockNotFoundMessage;
	}

	public Integer getStockNotFoundErrorCode() {
		return stockNotFoundErrorCode;
	}

	public String getStockNotAvailableMessage() {
		return stockNotAvailableMessage;
	}

	public Integer getStockNotAvailableErrorCode() {
		return stockNotAvailableErrorCode;
	}

	public String getStockNameNotFoundMessage() {
		return stockNameNotFoundMessage;
	}

	public Integer getStockNameNotFoundErrorCode() {
		return stockNameNotFoundErrorCode;
	}
	

}
