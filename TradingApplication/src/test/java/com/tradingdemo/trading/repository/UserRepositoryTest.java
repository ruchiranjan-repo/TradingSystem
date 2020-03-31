package com.tradingdemo.trading.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.tradingdemo.trading.data.TestData;
import com.tradingdemo.trading.entity.User;

@DataJpaTest
public class UserRepositoryTest {

	@Autowired
	TestEntityManager testEntityManager;

	@Autowired
	UserRepository userRepository;

	User user;

	@BeforeEach
	public void setUp() {

		user = new User();
		user.setName(TestData.USER_NAME);
		user.setPassword(TestData.USER_PASSWORD);
		user.setEmail(TestData.USER_EMAIL);

	}

	@Test
	public void testFindById() {
		User persistedUser = testEntityManager.persist(user);
		Optional<User> user = userRepository.findById(persistedUser.getId());

		assertTrue(user.isPresent());

		assertThat(persistedUser.getId()).isEqualTo(user.get().getId());
		assertThat(persistedUser.getName()).isEqualTo(user.get().getName());
		assertThat(persistedUser.getEmail()).isEqualTo(user.get().getEmail());
		assertThat(persistedUser.getPassword()).isEqualTo(user.get().getPassword());

	}

	@Test
	public void testFindByIdNotFound() {
		Optional<User> user = userRepository.findById(4L);

		assertFalse(user.isPresent());

	}

}
