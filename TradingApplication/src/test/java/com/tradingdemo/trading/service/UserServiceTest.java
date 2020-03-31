package com.tradingdemo.trading.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.tradingdemo.trading.data.TestData;
import com.tradingdemo.trading.dto.PurchaseStockRequestDTO;
import com.tradingdemo.trading.dto.PurchasedStockDTO;
import com.tradingdemo.trading.dto.PurchasedStockResponseDTO;
import com.tradingdemo.trading.dto.SuccessMessageDTO;
import com.tradingdemo.trading.entity.PurchasedStock;
import com.tradingdemo.trading.entity.Stock;
import com.tradingdemo.trading.entity.User;
import com.tradingdemo.trading.exception.StockNotAvailableException;
import com.tradingdemo.trading.exception.StockNotFoundException;
import com.tradingdemo.trading.exception.UserNotFoundException;
import com.tradingdemo.trading.repository.PurchaseStockRepository;
import com.tradingdemo.trading.repository.StockRepository;
import com.tradingdemo.trading.repository.UserRepository;
import com.tradingdemo.trading.service.api.UserService;

@SpringBootTest
public class UserServiceTest {

	@MockBean
	UserRepository userRepository;

	@MockBean
	StockRepository stockRepository;

	@MockBean
	PurchaseStockRepository purchaseStockRepository;

	@Autowired
	UserService userService;

	User user;
	Stock stock;
	PurchasedStock purchasedStock, purchasedStock1;
	SuccessMessageDTO successMessageDTO;
	PurchaseStockRequestDTO purchaseStockRequestDTO;

	@BeforeEach
	public void setup() {
		user = new User();
		user.setId(1L);
		user.setName(TestData.USER_NAME);
		user.setPassword(TestData.USER_PASSWORD);
		user.setEmail(TestData.USER_EMAIL);

		stock = new Stock();
		stock.setId(2L);
		stock.setName(TestData.STOCK_NAME);
		stock.setQuantity(TestData.STOCK_QUANTITY);
		stock.setUnitPrice(TestData.STOCK_PRICE);

		purchasedStock = new PurchasedStock(stock, TestData.STOCK_PRICE, TestData.PURCHASE_QUANTITY,
				TestData.PURCHASED_DATETIME, user);
		purchasedStock.setId(3L);
		purchasedStock1 = new PurchasedStock(stock, TestData.STOCK_PRICE, TestData.PURCHASE_QUANTITY,
				TestData.PURCHASED_DATETIME, user);
		purchasedStock1.setId(4L);

		successMessageDTO = new SuccessMessageDTO("Stock purchased successfully.", 201);

		purchaseStockRequestDTO = new PurchaseStockRequestDTO();
		purchaseStockRequestDTO.setStockId(2L);
		purchaseStockRequestDTO.setQuantity(2L);
	}

	@Test
	public void testPurchaseStock() throws UserNotFoundException, StockNotFoundException, StockNotAvailableException {
		when(userRepository.findById(1L)).thenReturn(Optional.of(user));
		when(stockRepository.findById(2L)).thenReturn(Optional.of(stock));
		when(purchaseStockRepository.save(Mockito.any(PurchasedStock.class))).thenReturn(purchasedStock);

		Stock returnedStock = stock;
		;
		returnedStock.setQuantity(8L);
		when(stockRepository.save(Mockito.any(Stock.class))).thenReturn(returnedStock);

		SuccessMessageDTO result = userService.purchaseStock(1L, purchaseStockRequestDTO);

		assertThat(result.getMessage()).isEqualTo(successMessageDTO.getMessage());
		assertThat(result.getSuccessCode()).isEqualTo(successMessageDTO.getSuccessCode());
	}

	@Test
	public void testPurchaseStockUserNotFoundEx()
			throws UserNotFoundException, StockNotFoundException, StockNotAvailableException {
		when(userRepository.findById(1L)).thenReturn(Optional.empty());

		assertThrows(UserNotFoundException.class, () -> {
			userService.purchaseStock(1L, purchaseStockRequestDTO);
		});

	}

	@Test
	public void testPurchaseStockStockNotFoundEx()
			throws UserNotFoundException, StockNotFoundException, StockNotAvailableException {
		when(userRepository.findById(1L)).thenReturn(Optional.of(user));
		when(stockRepository.findById(2L)).thenReturn(Optional.empty());

		assertThrows(StockNotFoundException.class, () -> {
			userService.purchaseStock(1L, purchaseStockRequestDTO);
		});

	}

	@Test
	public void testPurchaseStockStockNotAvailableException()
			throws UserNotFoundException, StockNotFoundException, StockNotAvailableException {
		when(userRepository.findById(1L)).thenReturn(Optional.of(user));
		when(stockRepository.findById(2L)).thenReturn(Optional.of(stock));

		purchaseStockRequestDTO.setQuantity(20L);

		assertThrows(StockNotAvailableException.class, () -> {
			userService.purchaseStock(1L, purchaseStockRequestDTO);
		});

	}

	@Test
	public void testPurchasedProduct() throws UserNotFoundException {
		when(userRepository.findById(1L)).thenReturn(Optional.of(user));
		when(purchaseStockRepository.findByUserId(1L)).thenReturn(Arrays.asList(purchasedStock));
		PurchasedStockDTO purchasedStockDTOs = new PurchasedStockDTO(3L, 2L, TestData.STOCK_NAME, TestData.STOCK_PRICE,
				TestData.PURCHASE_QUANTITY, TestData.STOCK_PRICE.multiply(new BigDecimal(TestData.PURCHASE_QUANTITY)),
				TestData.PURCHASED_DATETIME);
		PurchasedStockResponseDTO purchasedStockResponseDTO = new PurchasedStockResponseDTO(1L, TestData.USER_NAME,
				Arrays.asList(purchasedStockDTOs));

		PurchasedStockResponseDTO result = userService.getPurchasedStock(1L);

		assertThat(result.getUserId()).isEqualTo(purchasedStockResponseDTO.getUserId());
		assertThat(result.getUserName()).isEqualTo(purchasedStockResponseDTO.getUserName());
		assertThat(result.getPurchasedStockDTOs().get(0).getId())
				.isEqualTo(purchasedStockResponseDTO.getPurchasedStockDTOs().get(0).getId());
		assertThat(result.getPurchasedStockDTOs().get(0).getStockId())
				.isEqualTo(purchasedStockResponseDTO.getPurchasedStockDTOs().get(0).getStockId());

	}

	@Test
	public void testPurchasedProductUserNotFoundEx() throws UserNotFoundException {
		when(userRepository.findById(1L)).thenReturn(Optional.empty());

		assertThrows(UserNotFoundException.class, () -> {
			userService.getPurchasedStock(1L);
		});

	}

}
