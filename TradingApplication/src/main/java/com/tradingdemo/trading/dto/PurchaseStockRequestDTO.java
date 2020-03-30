package com.tradingdemo.trading.dto;

import javax.validation.constraints.NotNull;

/**
 * Request DTO for purchasing stock.
 * 
 * @author Ruchi
 *
 */
public class PurchaseStockRequestDTO {

	@NotNull
	private Long stockId;
	
	@NotNull
	private Long quantity;

	public Long getStockId() {
		return stockId;
	}

	public void setStockId(Long stockId) {
		this.stockId = stockId;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

}
