package com.tradingdemo.trading.service.impl;
/**
 * Implementation class for stock service.
 * @author Ruchi
 */
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.tradingdemo.trading.dto.StockDTO;
import com.tradingdemo.trading.dto.StockDetailsDTO;
import com.tradingdemo.trading.entity.Stock;
import com.tradingdemo.trading.exception.ExceptionMessageProprties;
import com.tradingdemo.trading.exception.StockNotFoundException;
import com.tradingdemo.trading.repository.StockRepository;
import com.tradingdemo.trading.service.api.StockService;

@Service
public class StockServiceImpl implements StockService {

	@Autowired
	StockRepository stockRepository;

	@Autowired
	ExceptionMessageProprties exceptionMessageProprties;

	Logger log = Logger.getLogger(StockServiceImpl.class);

	/**
	 * Method to get list of stocks by using stock name.
	 * 
	 * @param stockName StockName
	 * @throws StockNotFoundException stockNotFoundException
	 * @return list of StockDTO
	 */
	@Override
	public List<StockDTO> getStockByStockName(String stockName) throws StockNotFoundException {
		List<Stock> stocks = stockRepository.findByNameContains(stockName);
		if (CollectionUtils.isEmpty(stocks)) {
			log.warn("Stock with stock name " + stockName + " not found.");
			throw new StockNotFoundException(new StringBuffer(exceptionMessageProprties.getStockNameNotFoundMessage())
					.append(stockName).toString(), exceptionMessageProprties.getStockNameNotFoundErrorCode());
		}
		List<StockDTO> stockDtos = stocks.stream().map(s -> new StockDTO(s.getId(), s.getName()))
				.collect(Collectors.toList());
		log.info("Found " + stocks.size() + " stocks with stock name " + stockName);
		return stockDtos;
	}
	
	/**
	 * Method to get details of stock using stockId
	 * @param stockId stockId
	 * 
	 * @throws StockNotFoundException stockNotFoundException
	 * 
	 * @return StockDetailsDTO stockDetailsDTO
	 */

	@Override
	public StockDetailsDTO getAvailableStockDetails(Long stockId) throws StockNotFoundException {
		Optional<Stock> stock = stockRepository.findById(stockId);
		if (!stock.isPresent()) {
			log.warn("Stock not found. stock id: " + stockId);
			throw new StockNotFoundException(
					new StringBuffer(exceptionMessageProprties.getStockNotFoundMessage()).append(stockId).toString(),
					exceptionMessageProprties.getStockNotFoundErrorCode());

		}
		log.info("Stock found with stock id :" + stockId);
		return new StockDetailsDTO(stock.get().getId(), stock.get().getName(), stock.get().getUnitPrice(),
				stock.get().getQuantity(), stock.get().getDate());
	}

}
