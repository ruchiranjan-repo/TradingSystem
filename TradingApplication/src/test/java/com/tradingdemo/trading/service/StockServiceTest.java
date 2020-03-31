package com.tradingdemo.trading.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.util.CollectionUtils;

import com.tradingdemo.trading.data.TestData;
import com.tradingdemo.trading.dto.StockDTO;
import com.tradingdemo.trading.dto.StockDetailsDTO;
import com.tradingdemo.trading.entity.Stock;
import com.tradingdemo.trading.exception.StockNotFoundException;
import com.tradingdemo.trading.repository.StockRepository;
import com.tradingdemo.trading.service.api.StockService;

@SpringBootTest
public class StockServiceTest {

	@MockBean
	StockRepository stockRepository;

	@Autowired
	StockService stockService;

	Stock stock;
	StockDTO stockDTO;
	

	@BeforeEach
	public void setup() {

		stock = new Stock();
		stock.setId(2L);
		stock.setName(TestData.STOCK_NAME);
		stock.setQuantity(TestData.STOCK_QUANTITY);
		stock.setUnitPrice(TestData.STOCK_PRICE);
		stock.setDate(TestData.STOCK_DATE);

		stockDTO = new StockDTO(2l, TestData.STOCK_NAME);
		
	
	}

	@Test
	public void testGetStockByStockName() throws StockNotFoundException {
		
		List<Stock> stocks = new ArrayList<Stock>();
		stocks.add(stock);
		when(stockRepository.findByNameContains(TestData.STOCK_NAME)).thenReturn(stocks);

		List<StockDTO> stockList = stockService.getStockByStockName(TestData.STOCK_NAME);

		assertFalse(CollectionUtils.isEmpty(stockList));
		assertThat(stockList.size()).isEqualTo(stocks.size());
		assertThat(stockList.get(0).getStockName()).isEqualTo(stocks.get(0).getName());
	}

	@Test
	public void testGetStockByStockNameStockNotFoundEx() throws StockNotFoundException {

		when(stockRepository.findByNameContains(TestData.STOCK_NAME)).thenReturn(new ArrayList<>());

		assertThrows(StockNotFoundException.class, () -> {
			stockService.getStockByStockName(TestData.STOCK_NAME);
		});

		
	}
	
	@Test
	public void testGetAvailableStockDetails() throws StockNotFoundException
	{
		when(stockRepository.findById(2L)).thenReturn(Optional.of(stock));
		
		StockDetailsDTO result=  stockService.getAvailableStockDetails(2L);
		
		assertThat(result.getId()).isEqualTo(stock.getId());
		assertThat(result.getDate()).isEqualTo(stock.getDate());
		assertThat(result.getName()).isEqualTo(stock.getName());
		assertThat(result.getUnitPrice()).isEqualTo(stock.getUnitPrice());
	}
	
	

	@Test
	public void testGetAvailableStockStockNotFound() throws StockNotFoundException
	{
		when(stockRepository.findById(2L)).thenReturn(Optional.empty());
		
		assertThrows(StockNotFoundException.class, ()->{stockService.getAvailableStockDetails(2L);});
		
	
	}
}
