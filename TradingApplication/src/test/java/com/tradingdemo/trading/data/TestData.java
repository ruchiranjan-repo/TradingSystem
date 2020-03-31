package com.tradingdemo.trading.data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class TestData {

	public static final String USER_NAME = "Ruchi";
	public static final String USER_EMAIL = "ruchiranjan@outlook.com";
	public static final String USER_PASSWORD = "password";
	public static final String STOCK_NAME = "Stock1";
	public static final Long STOCK_QUANTITY = 10L;
	public static final BigDecimal STOCK_PRICE = new BigDecimal(200);
	public static final Long PURCHASE_QUANTITY = 2L;
	public static final LocalDateTime PURCHASED_DATETIME= LocalDateTime.of(2020, 03, 30, 13, 30, 30);
	public static final LocalDate STOCK_DATE= LocalDate.of(2020, 03, 30);

}
