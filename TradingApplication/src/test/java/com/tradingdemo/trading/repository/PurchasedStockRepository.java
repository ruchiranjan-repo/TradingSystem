package com.tradingdemo.trading.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.util.CollectionUtils;

import com.tradingdemo.trading.data.TestData;
import com.tradingdemo.trading.entity.PurchasedStock;
import com.tradingdemo.trading.entity.Stock;
import com.tradingdemo.trading.entity.User;

@DataJpaTest
public class PurchasedStockRepository {

	@Autowired
	PurchaseStockRepository purchaseStockRepository;

	@Autowired
	TestEntityManager testEntityManager;

	
	User user;
	Stock stock;
	PurchasedStock purchasedStock, purchasedStock1;

	@BeforeEach
	public void setup() {
		user = new User();
		user.setName(TestData.USER_NAME);
		user.setPassword(TestData.USER_PASSWORD);
		user.setEmail(TestData.USER_EMAIL);

		stock = new Stock();
		stock.setName(TestData.STOCK_NAME);
		stock.setQuantity(TestData.STOCK_QUANTITY);
		stock.setUnitPrice(TestData.STOCK_PRICE);

		purchasedStock = new PurchasedStock(stock, TestData.STOCK_PRICE, TestData.PURCHASE_QUANTITY, LocalDateTime.now(), user);
		purchasedStock1 = new PurchasedStock(stock, TestData.STOCK_PRICE, TestData.PURCHASE_QUANTITY, LocalDateTime.now(), user);
	}

	@Test
	public void testFindByUserId() {
		User savedUser = testEntityManager.persist(user);
		testEntityManager.persist(stock);
		testEntityManager.persist(purchasedStock);
		testEntityManager.persist(purchasedStock1);

		List<PurchasedStock> stocks = purchaseStockRepository.findByUserId(savedUser.getId());
		assertFalse(CollectionUtils.isEmpty(stocks));
		assertThat(stocks.size()).isEqualTo(2);
	}

	@Test
	public void testFindByUserIdNotFound() {	

		List<PurchasedStock> stocks = purchaseStockRepository.findByUserId(1L);
		assertTrue(CollectionUtils.isEmpty(stocks));

	}
}
