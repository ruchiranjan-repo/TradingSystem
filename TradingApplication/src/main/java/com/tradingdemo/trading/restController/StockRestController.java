package com.tradingdemo.trading.restController;
/**
 * Rest controller class for stock.
 * @author Ruchi
 */
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tradingdemo.trading.dto.StockDTO;
import com.tradingdemo.trading.dto.StockDetailsDTO;
import com.tradingdemo.trading.exception.StockNotFoundException;
import com.tradingdemo.trading.service.api.StockService;

@RestController
@RequestMapping(value = "/stocks")
public class StockRestController {

	@Autowired
	StockService stockService;

	@GetMapping("/{stockId}")
	public ResponseEntity<StockDetailsDTO> getAvailableStockDetails(@PathVariable("stockId") Long stockId)
			throws StockNotFoundException {
		StockDetailsDTO stockDetailsDTO = stockService.getAvailableStockDetails(stockId);
		return new ResponseEntity<StockDetailsDTO>(stockDetailsDTO, HttpStatus.OK);
	}

	@GetMapping("")
	public ResponseEntity<List<StockDTO>> getAvailableStockDetails(@RequestParam("stockName") String stockName)
			throws StockNotFoundException {
		List<StockDTO> stockDtos = stockService.getStockByStockName(stockName);
		return new ResponseEntity<List<StockDTO>>(stockDtos, HttpStatus.OK);
	}

}
