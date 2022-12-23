package de.hs.da.hskleinanzeigen.mappers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import de.hs.da.hskleinanzeigen.domain.User;
import de.hs.da.hskleinanzeigen.dtos.CreationUserDTO;
import de.hs.da.hskleinanzeigen.dtos.UserDTO;
import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class UserMapperTest {

  @Mock
  private UserMapper userMapper;

  CreationUserDTO creationUserDTO;
  User newUser = new User();

  @BeforeEach
  public void setUp() {

    CreationUserDTO creationUserDTO = new CreationUserDTO("test@example.com","password","Test","User","123-456-7890","Testville");

    newUser.setId(1);
    newUser.setEmail("test@example.com");
    newUser.setFirstName("Test");
    newUser.setLastName("User");
    newUser.setPassword("password");
    newUser.setPhone("123-456-7890");
    newUser.setLocation("Testville");
    newUser.setCreated(LocalDateTime.now());  }

  @Test
  public void testUserToUserDTO() {
    UserDTO userDTO = new UserDTO(1,"test@example.com","Test","User","123-456-7890","Testville");
    // Stub the userMapper.userToUserDTO() method to return a UserDTO object
    when(userMapper.userToUserDTO(newUser)).thenReturn(userDTO);

    // Call the userMapper.userToUserDTO() method
    UserDTO result = userMapper.userToUserDTO(newUser);

    // Verify that the userMapper.userToUserDTO() method was called
    verify(userMapper).userToUserDTO(newUser);

    // Assert that the result is as expected
    assertEquals(1, result.getId());
    assertEquals("Test", result.getFirstName());
    assertEquals("test@example.com", result.getEmail());
  }

  @Test
  public void testUserDTOtoUser() {
    // Create a test UserDTO object
    UserDTO userDTO = new UserDTO(1,"test@example.com","Test","User","123-456-7890","Testville");

    // Stub the userMapper.userDTOtoUser() method to return a User object
    when(userMapper.userDTOtoUser(userDTO)).thenReturn(newUser);

    // Call the userMapper.userDTOtoUser() method
    User result = userMapper.userDTOtoUser(userDTO);

    // Verify that the userMapper.userDTOtoUser() method was called
    verify(userMapper).userDTOtoUser(userDTO);

    // Assert that the result is as expected
    assertEquals(1, result.getId());
    assertEquals("Test", result.getFirstName());
    assertEquals("test@example.com", result.getEmail());
  }

  @Test
  void userToCreationUserDTO() {
  }


  @Test
  public void testCreationUserDTOToUser() {

    // Stub the userMapper.creationUserDTOToUser() method to return a User object
    when(userMapper.creationUserDTOToUser(creationUserDTO)).thenReturn( newUser);

    // Call the userMapper.creationUserDTOToUser() method
    User result = userMapper.creationUserDTOToUser(creationUserDTO);

    // Verify that the userMapper.creationUserDTOToUser() method was called
    verify(userMapper).creationUserDTOToUser(creationUserDTO);

    // Assert that the result is as expected
    assertEquals(1, result.getId());
    assertEquals("Test", result.getFirstName());
    assertEquals("password", result.getPassword());
    assertEquals("test@example.com", result.getEmail());
  }

  @Test
  void listUserToListUserDTO() {
  }
}