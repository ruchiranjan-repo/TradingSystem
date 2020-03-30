package com.tradingdemo.trading.exception;

/**
 * Exception handler to handle the exceptions occur in Trading application
 * @author Ruchi
 *
 */

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.tradingdemo.trading.dto.ExceptionMessageDTO;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ExceptionMessageDTO> userNotFoundExceptionHandler(
			UserNotFoundException userNotFoundException) {
		ExceptionMessageDTO exceptionMessageDTO = new ExceptionMessageDTO(userNotFoundException.getMessage(),
				userNotFoundException.getErrorCode());
		return new ResponseEntity<>(exceptionMessageDTO, HttpStatus.NOT_FOUND);
	}
	

	@ExceptionHandler(StockNotAvailableException.class)
	public ResponseEntity<ExceptionMessageDTO> stockNotAvailableExceptionHandler(
			StockNotAvailableException stockNotAvailableException) {
		ExceptionMessageDTO exceptionMessageDTO = new ExceptionMessageDTO(stockNotAvailableException.getMessage(),
				stockNotAvailableException.getErrorCode());
		return new ResponseEntity<>(exceptionMessageDTO, HttpStatus.NOT_FOUND);
	}
	
	

	@ExceptionHandler(StockNotFoundException.class)
	public ResponseEntity<ExceptionMessageDTO> stockNotFoundExceptionHandler(
			StockNotFoundException stockNotFoundException) {
		ExceptionMessageDTO exceptionMessageDTO = new ExceptionMessageDTO(stockNotFoundException.getMessage(),
				stockNotFoundException.getErrorCode());
		return new ResponseEntity<>(exceptionMessageDTO, HttpStatus.NOT_FOUND);
	}

}
