package com.vidal.springboot;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.vidal.springboot.entities.User;
import com.vidal.springboot.interfaces.UserRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
@Rollback(false)
public class UserRepositoryTest {
	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private UserRepository userRepo;
	
	/**
	 * Test on user creation
	 */
	@Test
	public void testCreateUser() {
		User user = new User();
		user.setEmail("leovidal_23@hotmail.com");
		user.setPassword("12345");
		user.setFirstname("Leonardo");
		user.setLastname("Cruz Vidal");
		
		User storeUser = userRepo.save(user);
		User existsUser = entityManager.find(User.class, storeUser.getId());
		assertThat(user.getEmail()).isEqualTo(existsUser.getEmail());
	}
}
