package com.tradingdemo.trading.restController;
/**
 * Rest controller class for user.
 * @author Ruchi
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tradingdemo.trading.dto.PurchaseStockRequestDTO;
import com.tradingdemo.trading.dto.PurchasedStockResponseDTO;
import com.tradingdemo.trading.dto.SuccessMessageDTO;
import com.tradingdemo.trading.exception.StockNotAvailableException;
import com.tradingdemo.trading.exception.StockNotFoundException;
import com.tradingdemo.trading.exception.UserNotFoundException;
import com.tradingdemo.trading.service.api.UserService;

@RestController
@RequestMapping(value="/users/{userId}/stocks")
public class UserRestController {
	
	@Autowired
	UserService userService;
	
	@PostMapping("")
	public ResponseEntity<SuccessMessageDTO> purchaseStock(@PathVariable("userId") Long userId, @RequestBody PurchaseStockRequestDTO purchaseStockRequestDTO) throws UserNotFoundException, StockNotFoundException, StockNotAvailableException
	{
		SuccessMessageDTO successMessageDTO = userService.purchaseStock(userId, purchaseStockRequestDTO);
		return new ResponseEntity<SuccessMessageDTO>(successMessageDTO,HttpStatus.CREATED);
		
	}
	
	
	@GetMapping("")
	public ResponseEntity<PurchasedStockResponseDTO> getPurchasedStock(@PathVariable("userId")Long userId) throws UserNotFoundException
	{
		PurchasedStockResponseDTO purchasedStockResponseDTO=userService.getPurchasedStock(userId);
		return new ResponseEntity<PurchasedStockResponseDTO>(purchasedStockResponseDTO,HttpStatus.OK);
		
	}

}
