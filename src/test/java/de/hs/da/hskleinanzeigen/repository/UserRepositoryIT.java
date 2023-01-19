package de.hs.da.hskleinanzeigen.repository;

import static org.junit.Assert.*;

import de.hs.da.hskleinanzeigen.domain.User;

import java.time.LocalDateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryIT {

  @Autowired
  private UserRepository userRepository;

  @Test
  public void testFindByEmail() {
    // Save a user to the database
    User user = new User();
    user.setFirstName("John");
    user.setLastName("Doe");
    user.setPassword("password123");
    user.setCreated(LocalDateTime.now());
    user.setEmail("test@example.com");
    userRepository.save(user);

    // Find the user by email
    User foundUser = userRepository.findByEmail("test@example.com");

    // Verify that the user was found
    assertNotNull(foundUser);
    assertEquals("test@example.com", foundUser.getEmail());
    userRepository.delete(user);
  }
}

