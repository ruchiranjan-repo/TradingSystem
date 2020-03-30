package com.tradingdemo.trading.repository;

/**
 * Repository for user.
 * @author Ruchi
 */

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.tradingdemo.trading.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findById(Long id);

}
