package com.tradingdemo.trading.dto;
/**
 * DTO to be used to display the stock details
 * @author Ruchi
 */
import java.math.BigDecimal;
import java.time.LocalDate;

public class StockDetailsDTO {

	private Long id;

	private String name;

	private BigDecimal unitPrice;

	private Long quantity;

	private LocalDate date;

	public StockDetailsDTO() {

	}

	public StockDetailsDTO(Long id, String name, BigDecimal unitPrice, Long quantity, LocalDate date) {
		super();
		this.id = id;
		this.name = name;
		this.unitPrice = unitPrice;
		this.quantity = quantity;
		this.date = date;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

}
