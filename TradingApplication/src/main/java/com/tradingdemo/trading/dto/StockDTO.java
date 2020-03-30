package com.tradingdemo.trading.dto;

/**
 * Stock DTO response
 * @author Ruchi
 *
 */
public class StockDTO {

	private Long id;
	private String stockName;

	public StockDTO() {

	}

	public StockDTO(Long id, String stockName) {
		super();
		this.id = id;
		this.stockName = stockName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

}
