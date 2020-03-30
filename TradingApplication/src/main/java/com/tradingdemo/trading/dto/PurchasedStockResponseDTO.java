package com.tradingdemo.trading.dto;

/**
 * DTO to be used as a response to see the stocked purchased by a user.
 * @author Ruchi
 */
import java.util.ArrayList;
import java.util.List;

public class PurchasedStockResponseDTO {
	
	private Long userId;
	private String userName;
	private List<PurchasedStockDTO> purchasedStockDTOs = new ArrayList<PurchasedStockDTO>();

	public PurchasedStockResponseDTO() {

	}

	public PurchasedStockResponseDTO(Long userId, String userName, List<PurchasedStockDTO> purchasedStockDTOs) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.purchasedStockDTOs = purchasedStockDTOs;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<PurchasedStockDTO> getPurchasedStockDTOs() {
		return purchasedStockDTOs;
	}

	public void setPurchasedStockDTOs(List<PurchasedStockDTO> purchasedStockDTOs) {
		this.purchasedStockDTOs = purchasedStockDTOs;
	}

}
