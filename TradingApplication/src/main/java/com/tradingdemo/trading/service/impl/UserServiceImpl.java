package com.tradingdemo.trading.service.impl;

/**
 * Immplementation class  user service.
 * @author Ruchi
 */
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.tradingdemo.trading.dto.PurchaseStockRequestDTO;
import com.tradingdemo.trading.dto.PurchasedStockDTO;
import com.tradingdemo.trading.dto.PurchasedStockResponseDTO;
import com.tradingdemo.trading.dto.SuccessMessageDTO;
import com.tradingdemo.trading.entity.PurchasedStock;
import com.tradingdemo.trading.entity.Stock;
import com.tradingdemo.trading.entity.User;
import com.tradingdemo.trading.exception.ExceptionMessageProprties;
import com.tradingdemo.trading.exception.StockNotAvailableException;
import com.tradingdemo.trading.exception.StockNotFoundException;
import com.tradingdemo.trading.exception.UserNotFoundException;
import com.tradingdemo.trading.repository.PurchaseStockRepository;
import com.tradingdemo.trading.repository.StockRepository;
import com.tradingdemo.trading.repository.UserRepository;
import com.tradingdemo.trading.service.api.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	StockRepository stockRepository;

	@Autowired
	PurchaseStockRepository purchaseStockRepository;

	@Autowired
	ExceptionMessageProprties exceptionMessageProprties;

	@Value("${com.trading.successmessage}")
	private String successMessage;
	@Value("${com.trading.successcode}")
	private Integer successCode;

	Logger log = Logger.getLogger(UserServiceImpl.class);

	/**
	 * Method use to purchase the stock.
	 * 
	 * @param userId                  userId
	 * @param PurchaseStockRequestDTO purchaseStockRequestDTO
	 * @throws UserNotFoundException      userNotFoundException
	 * @throws StockNotFoundException     stockNotFoundException
	 * @throws StockNotAvailableException stockNotAvailableException
	 * 
	 */

	@Override
	public SuccessMessageDTO purchaseStock(Long userId, PurchaseStockRequestDTO purchaseStockRequestDTO)
			throws UserNotFoundException, StockNotFoundException, StockNotAvailableException {
		User user = getUserById(userId);
		Stock stock = getStockById(purchaseStockRequestDTO.getStockId());

		if (stock.getQuantity() < purchaseStockRequestDTO.getQuantity()) {
			log.warn("Stock with provided quantity not available");
			throw new StockNotAvailableException(exceptionMessageProprties.getStockNotAvailableMessage(),
					exceptionMessageProprties.getStockNotAvailableErrorCode());
		}

		PurchasedStock purchasedStock = new PurchasedStock(stock, stock.getUnitPrice(),
				purchaseStockRequestDTO.getQuantity(), LocalDateTime.now(), user);

		stock.setQuantity(stock.getQuantity() - purchaseStockRequestDTO.getQuantity());
		purchaseStockRepository.save(purchasedStock);
		stockRepository.save(stock);
		log.info("Stock purchased successfully");
		return new SuccessMessageDTO(successMessage, successCode);

	}

	/**
	 * Method to get all the stock purchase deatils of a user .
	 * 
	 * @param userId userId	  
	 * @throws UserNotFoundException userNotFoundException	 
	 * 
	 */
	@Override
	public PurchasedStockResponseDTO getPurchasedStock(Long userId) throws UserNotFoundException {
		User user = getUserById(userId);
		List<PurchasedStock> purchasedStocks = purchaseStockRepository.findByUserId(userId);
		List<PurchasedStockDTO> purchasedStockDTOs = new ArrayList<PurchasedStockDTO>();
		if (!CollectionUtils.isEmpty(purchasedStocks)) {

			for (PurchasedStock purchasedStock : purchasedStocks) {
				purchasedStockDTOs.add(new PurchasedStockDTO(purchasedStock.getId(), purchasedStock.getStock().getId(),
						purchasedStock.getStock().getName(), purchasedStock.getUnitPrice(),
						purchasedStock.getQuantity(),
						purchasedStock.getUnitPrice().multiply(new BigDecimal(purchasedStock.getQuantity())),
						purchasedStock.getPurchaseDateTime()));
			}
			log.info("Number of stocks purchased by user with user id " + userId + " is " + purchasedStocks.size());

		} else {
			log.info("No stock purchased by user with user id " + userId);
		}

		return new PurchasedStockResponseDTO(userId, user.getName(), purchasedStockDTOs);

	}

	private User getUserById(Long userId) throws UserNotFoundException {
		Optional<User> user = userRepository.findById(userId);
		if (!user.isPresent()) {
			log.warn("User not found with user id: " + userId);
			throw new UserNotFoundException(
					new StringBuffer(exceptionMessageProprties.getUserNotFoundMessage()).append(userId).toString(),
					exceptionMessageProprties.getUserNotFoundErrorCode());

		}
		log.info("User found with user id: " + userId);
		return user.get();
	}

	private Stock getStockById(Long stockId) throws StockNotFoundException {
		Optional<Stock> stock = stockRepository.findById(stockId);
		if (!stock.isPresent()) {
			log.warn("Stock not found with stock id: " + stockId);
			throw new StockNotFoundException(
					new StringBuffer(exceptionMessageProprties.getStockNotFoundMessage()).append(stockId).toString(),
					exceptionMessageProprties.getStockNotFoundErrorCode());

		}
		log.info("Stock found with stock id: " + stockId);
		return stock.get();
	}

}
