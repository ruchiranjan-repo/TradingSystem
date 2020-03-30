package com.tradingdemo.trading.repository;
/**
 * Repository interface for stock.
 * @author Ruchi
 */
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tradingdemo.trading.entity.Stock;

@Repository
public interface StockRepository extends JpaRepository<Stock,Long>{
	
	Optional<Stock> findById(Long id);
	
	List<Stock> findByNameContains(String name);

}
