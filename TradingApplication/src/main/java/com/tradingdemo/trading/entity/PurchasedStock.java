package com.tradingdemo.trading.entity;

/**
 * Entity class for purchased stock details
 * @author Ruchi
 */

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="PurchasedStock")
public class PurchasedStock implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@OneToOne
	@JoinColumn(name="stockId")
	private Stock stock;
	
	@Column(name="unitPrice")
	private BigDecimal unitPrice;
	
	@Column(name="quantity")
	private Long quantity;
	
	@Column(name="purchaseDateTime")
	private LocalDateTime purchaseDateTime;
	
	@OneToOne
	@JoinColumn(name="userId")
	private User user;
	
	public PurchasedStock() {
		
	}

	public PurchasedStock(Stock stock, BigDecimal unitPrice, Long quantity, LocalDateTime purchaseDateTime, User user) {
		super();
		this.stock = stock;
		this.unitPrice = unitPrice;
		this.quantity = quantity;
		this.purchaseDateTime = purchaseDateTime;
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
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

	public LocalDateTime getPurchaseDateTime() {
		return purchaseDateTime;
	}

	public void setPurchaseDateTime(LocalDateTime purchaseDateTime) {
		this.purchaseDateTime = purchaseDateTime;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
