package com.tradingdemo.trading.service.api;
/**
 * Interface for stock service.
 * @author Ruchi
 */
import java.util.List;

import com.tradingdemo.trading.dto.StockDTO;
import com.tradingdemo.trading.dto.StockDetailsDTO;
import com.tradingdemo.trading.exception.StockNotFoundException;

public interface StockService {

	List<StockDTO> getStockByStockName(String stockName)  throws StockNotFoundException;

	StockDetailsDTO getAvailableStockDetails(Long stockId)  throws StockNotFoundException;

}
