package de.hs.da.hskleinanzeigen.mappers;

import static org.junit.jupiter.api.Assertions.*;

import de.hs.da.hskleinanzeigen.dtos.CreationUserDTO;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import de.hs.da.hskleinanzeigen.domain.User;
import de.hs.da.hskleinanzeigen.dtos.UserDTO;
import de.hs.da.hskleinanzeigen.mappers.UserMapper;
import de.hs.da.hskleinanzeigen.mappers.UserMapperImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserMapperImplTest {
  private UserMapper userMapper = new UserMapperImpl();

  @Test
  public void testUserToUserDTO() {
    User user = new User();
    user.setId(1);
    user.setEmail("user@example.com");
    user.setFirstName("First");
    user.setLastName("Last");
    user.setPhone("1234567890");
    user.setLocation("Location");

    UserDTO userDTO = userMapper.userToUserDTO(user);

    assertEquals(1, userDTO.getId());
    assertEquals("user@example.com", userDTO.getEmail());
    assertEquals("First", userDTO.getFirstName());
    assertEquals("Last", userDTO.getLastName());
    assertEquals("1234567890", userDTO.getPhone());
    assertEquals("Location", userDTO.getLocation());
  }

  @Test
  public void testUserDTOtoUser() {
    // Set up the input and expected values
    UserDTO userDTO = new UserDTO(1, "john@example.com", "John", "Doe", "12345678", "Berlin");
    User expectedUser = new User();
    expectedUser.setId(1);
    expectedUser.setEmail( "john@example.com");
    expectedUser.setFirstName("John");
    expectedUser.setLastName("Doe");
    expectedUser.setPhone("12345678");
    expectedUser.setLocation("Berlin");
    expectedUser.setCreated(LocalDateTime.now());
    // Call the method under test
    UserMapper userMapper = new UserMapperImpl();
    User actualUser = userMapper.userDTOtoUser(userDTO);

    // Assert that the actual values match the expected values
    assertEquals(expectedUser.getId(), actualUser.getId());
    assertEquals(expectedUser.getEmail(), actualUser.getEmail());
    assertEquals(expectedUser.getFirstName(), actualUser.getFirstName());
    assertEquals(expectedUser.getLastName(), actualUser.getLastName());
    assertEquals(expectedUser.getPhone(), actualUser.getPhone());
    assertEquals(expectedUser.getLocation(), actualUser.getLocation());
  }


  @Test
  public void testUserToCreationUserDTO() {
    // Create a User object with sample data
    User user = new User();
    user.setEmail("john@example.com");
    user.setPassword("password123");
    user.setFirstName("John");
    user.setLastName("Doe");
    user.setPhone("1234567890");
    user.setLocation("New York");

    // Create a UserMapper instance
    UserMapper userMapper = new UserMapperImpl();

    // Call the userToCreationUserDTO() method
    CreationUserDTO creationUserDTO = userMapper.userToCreationUserDTO(user);

    // Assert that the returned CreationUserDTO object has the same data as the original User object
    assertEquals(user.getEmail(), creationUserDTO.getEmail());
    assertEquals(user.getPassword(), creationUserDTO.getPassword());
    assertEquals(user.getFirstName(), creationUserDTO.getFirstName());
    assertEquals(user.getLastName(), creationUserDTO.getLastName());
    assertEquals(user.getPhone(), creationUserDTO.getPhone());
    assertEquals(user.getLocation(), creationUserDTO.getLocation());
  }


  @Test
  public void testCreationUserDTOToUser() {
    // Setup
    CreationUserDTO creationUserDTO = new CreationUserDTO("test@email.com", "testPassword", "testFirstName", "testLastName", "123456", "testLocation");

    // Execute
    User result = userMapper.creationUserDTOToUser(creationUserDTO);

    // Verify
    assertEquals("test@email.com", result.getEmail());
    assertEquals("testPassword", result.getPassword());
    assertEquals("testFirstName", result.getFirstName());
    assertEquals("testLastName", result.getLastName());
    assertEquals("123456", result.getPhone());
    assertEquals("testLocation", result.getLocation());
  }


  @Test
  public void testListUserToListUserDTO() {
    // Create a list of User objects
    List<User> userList = new ArrayList<>();
    User user1 = new User();
    User user2 = new User();
    user1.setId(1);
    user1.setEmail("user1@example.com");
    user1.setFirstName("First1");
    user1.setLastName("Last1");
    user1.setPhone("12345678901");
    user1.setLocation("Location1");
    user2.setId(2);
    user2.setEmail("user2@example.com");
    user2.setFirstName("First1");
    user2.setLastName("Last1");
    user2.setPhone("12345678902");
    user2.setLocation("Location2");
    userList.add(user1);
    userList.add(user2);

    // Convert the list of User objects to a list of UserDTO objects
    List<UserDTO> userDTOList = userMapper.listUserToListUserDTO(userList);

    // Assert that the size of the list is correct
    assertEquals(2, userDTOList.size());

    // Assert that the values in the UserDTO objects are correct
    assertEquals(1, userDTOList.get(0).getId());
    assertEquals(2, userDTOList.get(1).getId());
    assertEquals("user1@example.com", userDTOList.get(0).getEmail());
  }
}