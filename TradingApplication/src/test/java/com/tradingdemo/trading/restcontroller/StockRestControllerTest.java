package com.tradingdemo.trading.restcontroller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;

import com.tradingdemo.trading.data.TestData;
import com.tradingdemo.trading.dto.StockDTO;
import com.tradingdemo.trading.dto.StockDetailsDTO;
import com.tradingdemo.trading.exception.StockNotFoundException;
import com.tradingdemo.trading.service.api.StockService;

@SpringBootTest
public class StockRestControllerTest {

	@MockBean
	StockService stockService;

	@Autowired
	StockRestController stockRestController;

	StockDetailsDTO stockDetailsDTO;
	StockDTO stockDTO;

	@BeforeEach
	public void setUp() {
		stockDetailsDTO = new StockDetailsDTO(2L, TestData.STOCK_NAME, TestData.STOCK_PRICE, TestData.STOCK_QUANTITY,
				TestData.STOCK_DATE);

		stockDTO = new StockDTO(1L, TestData.STOCK_NAME);

	}

	@Test
	public void testGetAvailableStockDetails() throws StockNotFoundException {
		when(stockService.getAvailableStockDetails(2L)).thenReturn(stockDetailsDTO);

		ResponseEntity<StockDetailsDTO> response = stockRestController.getAvailableStockDetails(2L);

		assertThat(response.getBody().getId()).isEqualTo(stockDetailsDTO.getId());
		assertThat(response.getBody().getName()).isEqualTo(stockDetailsDTO.getName());
		assertThat(response.getBody().getQuantity()).isEqualTo(stockDetailsDTO.getQuantity());

	}

	@Test
	public void testGetAvailableStockException() throws StockNotFoundException {
		when(stockService.getAvailableStockDetails(2L)).thenThrow(StockNotFoundException.class);

		assertThrows(StockNotFoundException.class, () -> {
			stockRestController.getAvailableStockDetails(2L);
		});

	}

	@Test
	public void testGetStockByStockName() throws StockNotFoundException {

		List<StockDTO> stocks = new ArrayList<StockDTO>();
		stocks.add(stockDTO);
		when(stockService.getStockByStockName(TestData.STOCK_NAME)).thenReturn(stocks);

		ResponseEntity<List<StockDTO>> response = stockRestController.getStockByStockName(TestData.STOCK_NAME);
		assertFalse(CollectionUtils.isEmpty(response.getBody()));
		assertThat(response.getBody().get(0).getId()).isEqualTo(stockDTO.getId());
		assertThat(response.getBody().get(0).getStockName()).isEqualTo(stockDTO.getStockName());
	}

	@Test
	public void testGetStockByStockNameStockNotFound() throws StockNotFoundException {

		when(stockService.getStockByStockName(TestData.STOCK_NAME)).thenThrow(StockNotFoundException.class);
		assertThrows(StockNotFoundException.class, () -> {
			stockRestController.getStockByStockName(TestData.STOCK_NAME);
		});

	}
}
