package de.hs.da.hskleinanzeigen.domain;

import java.time.LocalDateTime;
import java.util.Optional;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.*;

@SpringBootTest
public class UserTest {

  @Test
  public void testUserConstructor() {
    // Create a new User object
    User user = new User();

    // Verify that the object was created successfully and that all fields are set to their default values
    assertNotNull(user);
    assertEquals(null, user.getId());
    assertNull(user.getEmail());
    assertNull(user.getPassword());
    assertNull(user.getFirstName());
    assertNull(user.getLastName());
    assertNull(user.getPhone());
    assertNull(user.getLocation());
    assertNull(user.getCreated());
  }

  @Test
  public void testSettersAndGetters() {
    User user = new User();
    user.setId(1);
    user.setEmail("test@example.com");
    user.setPassword("password");
    user.setFirstName("Test");
    user.setLastName("User");
    user.setPhone("123-456-7890");
    user.setLocation("Test City");
    user.setCreated(LocalDateTime.now());

    assertEquals(1, user.getId().intValue());
    assertEquals("test@example.com", user.getEmail());
    assertEquals("password", user.getPassword());
    assertEquals("Test", user.getFirstName());
    assertEquals("User", user.getLastName());
    assertEquals("123-456-7890", user.getPhone());
    assertEquals("Test City", user.getLocation());
    assertNotNull(user.getCreated());
  }

  @Test
  public void testEquals() {
    User user1 = new User();
    user1.setId(1);
    user1.setEmail("test@example.com");
    user1.setPassword("password");
    user1.setFirstName("Test");
    user1.setLastName("User");
    user1.setPhone("123-456-7890");
    user1.setLocation("Test City");


    User user2 = new User();
    user2.setId(1);
    user2.setEmail("test@example.com");
    user2.setPassword("password");
    user2.setFirstName("Test");
    user2.setLastName("User");
    user2.setPhone("123-456-7890");
    user2.setLocation("Test City");

    assertTrue(user1.equals(user2));
  }

  @Test
  public void testNotEquals() {
    User user1 = new User();
    user1.setId(1);
    user1.setEmail("test@example.com");
    user1.setPassword("password");
    user1.setFirstName("Test");
    user1.setLastName("User");
    user1.setPhone("123-456-7890");
    user1.setLocation("Test City");
    user1.setCreated(LocalDateTime.now());

    User user2 = new User();
    user2.setId(2);
    user2.setEmail("test@example.com");
    user2.setPassword("password");
    user2.setFirstName("Test");
    user2.setLastName("User");
    user2.setPhone("123-456-7890");
    user2.setLocation("Test City");
    user2.setCreated(LocalDateTime.now());

    assertFalse(user1.equals(user2));
  }

  @Test
  public void testHashCode() {
    User user1 = new User();
    user1.setId(1);
    user1.setEmail("test@example.com");
    user1.setPassword("password");
    user1.setFirstName("Test");
    user1.setLastName("User");
    user1.setPhone("123-456-7890");
    user1.setLocation("Test City");

    User user2 = new User();
    user2.setId(1);
    user2.setEmail("test@example.com");
    user2.setPassword("password");
    user2.setFirstName("Test");
    user2.setLastName("User");
    user2.setPhone("123-456-7890");
    user2.setLocation("Test City");


    assertEquals(user1.hashCode(), user2.hashCode());
  }
}
