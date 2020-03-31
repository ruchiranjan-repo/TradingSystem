package com.tradingdemo.trading.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.util.CollectionUtils;

import com.tradingdemo.trading.data.TestData;
import com.tradingdemo.trading.entity.Stock;

@DataJpaTest
public class StockRepositoryTest {

	@Autowired
	StockRepository stockRepository;

	@Autowired
	TestEntityManager testEntityManager;
	
	Stock stock;

	@BeforeEach
	public void setUp() {
		stock = new Stock();
		stock.setName(TestData.STOCK_NAME);
		stock.setQuantity(TestData.STOCK_QUANTITY);
		stock.setUnitPrice(TestData.STOCK_PRICE);
	}

	@Test
	public void testFindById() {

		Stock persistedStock = testEntityManager.persist(stock);
		Optional<Stock> stock = stockRepository.findById(persistedStock.getId());

		assertThat(persistedStock.getId()).isEqualTo(stock.get().getId());
		assertThat(persistedStock.getName()).isEqualTo(stock.get().getName());
		assertThat(persistedStock.getQuantity()).isEqualTo(stock.get().getQuantity());
		assertThat(persistedStock.getUnitPrice()).isEqualTo(stock.get().getUnitPrice());

	}

	@Test
	public void testFindByIdNotFound() {

		Optional<Stock> stock = stockRepository.findById(1L);

		assertFalse(stock.isPresent());

	}

	@Test
	public void testFindByName() {

		Stock persistedStock = testEntityManager.persist(stock);
		List<Stock> stocks = stockRepository.findByNameContains(persistedStock.getName());

		assertFalse(CollectionUtils.isEmpty(stocks));
		assertThat(persistedStock.getId()).isEqualTo(stocks.get(0).getId());
		assertThat(persistedStock.getName()).isEqualTo(stocks.get(0).getName());
		assertThat(persistedStock.getQuantity()).isEqualTo(stocks.get(0).getQuantity());
		assertThat(persistedStock.getUnitPrice()).isEqualTo(stocks.get(0).getUnitPrice());

	}

	@Test
	public void testFindByNameNotFound() {

		List<Stock> stocks = stockRepository.findByNameContains(TestData.STOCK_NAME);

		assertTrue(CollectionUtils.isEmpty(stocks));

	}

}
