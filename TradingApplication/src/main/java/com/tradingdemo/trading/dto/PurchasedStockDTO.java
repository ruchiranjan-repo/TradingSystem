package com.tradingdemo.trading.dto;
/**
 * DTO returning the purchased stock details.
 * @author Ruchi
 */
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PurchasedStockDTO {
	
	private Long id;
	private Long stockId;
	private String stockName;
	private BigDecimal unitPrice;
	private Long quantity;
	private BigDecimal totalAmount;
	private LocalDateTime purchaseDateTime;
	
	public PurchasedStockDTO()
	{
		
	}

	public PurchasedStockDTO(Long id, Long stockId, String stockName, BigDecimal unitPrice, Long quantity,
			BigDecimal totalAmount, LocalDateTime purchaseDateTime) {
		super();
		this.id = id;
		this.stockId = stockId;
		this.stockName = stockName;
		this.unitPrice = unitPrice;
		this.quantity = quantity;
		this.totalAmount = totalAmount;
		this.purchaseDateTime = purchaseDateTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getStockId() {
		return stockId;
	}

	public void setStockId(Long stockId) {
		this.stockId = stockId;
	}

	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public LocalDateTime getPurchaseDateTime() {
		return purchaseDateTime;
	}

	public void setPurchaseDateTime(LocalDateTime purchaseDateTime) {
		this.purchaseDateTime = purchaseDateTime;
	}

}
