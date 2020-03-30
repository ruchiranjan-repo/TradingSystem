package com.tradingdemo.trading.service.api;
/**
 * Interface for user service.
 * @author Ruchi
 */
import com.tradingdemo.trading.dto.PurchaseStockRequestDTO;
import com.tradingdemo.trading.dto.PurchasedStockResponseDTO;
import com.tradingdemo.trading.dto.SuccessMessageDTO;
import com.tradingdemo.trading.exception.StockNotAvailableException;
import com.tradingdemo.trading.exception.StockNotFoundException;
import com.tradingdemo.trading.exception.UserNotFoundException;

public interface UserService {

	SuccessMessageDTO purchaseStock(Long userId, PurchaseStockRequestDTO purchaseStockRequestDTO)
			throws UserNotFoundException, StockNotFoundException, StockNotAvailableException;

	PurchasedStockResponseDTO getPurchasedStock(Long userId) throws UserNotFoundException;

}
