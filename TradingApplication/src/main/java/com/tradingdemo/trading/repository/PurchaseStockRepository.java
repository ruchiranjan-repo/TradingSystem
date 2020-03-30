package com.tradingdemo.trading.repository;
/**
 * Repository interface for PurchaseStock.
 * @author Ruchi
 */
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tradingdemo.trading.entity.PurchasedStock;
@Repository
public interface PurchaseStockRepository extends JpaRepository<PurchasedStock, Long> {

	List<PurchasedStock> findByUserId(Long id);
}
