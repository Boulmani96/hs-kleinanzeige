package de.hs.da.hskleinanzeigen.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.hs.da.hskleinanzeigen.domain.User;
import de.hs.da.hskleinanzeigen.dtos.CreationUserDTO;
import de.hs.da.hskleinanzeigen.dtos.UserDTO;
import de.hs.da.hskleinanzeigen.mappers.UserMapper;
import de.hs.da.hskleinanzeigen.repository.UserRepository;
import de.hs.da.hskleinanzeigen.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.*;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(UserController.class)
@WithMockUser(username = "user", password = "user", roles = "user")
class UserControllerIT {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private UserService userService;

  @MockBean
  private UserMapper userMapper;

  @MockBean
  private UserRepository userRepository;

  @Autowired
  private ObjectMapper objectMapper;

  CreationUserDTO mockCreationUserDTO;

  @BeforeEach
  void init() {
     mockCreationUserDTO = new CreationUserDTO("test@example.com","password","Test","User","123-456-7890","Darmstadt");
  }

  @Test
  void testAddNewUser_Success() throws Exception {
    // mock the service and mapper methods
    User mockUser1 = new User();
    mockUser1.setId(1);
    mockUser1.setEmail("test@example.com");
    mockUser1.setFirstName("Test");
    mockUser1.setLastName("User");
    mockUser1.setPassword("password");
    mockUser1.setPhone("123-456-7890");
    mockUser1.setLocation("Darmstadt");

    mockUser1.setCreated(LocalDateTime.now());

    when(userMapper.creationUserDTOToUser(mockCreationUserDTO)).thenReturn(mockUser1);

    when(userService.findByEmail("test@example.com")).thenReturn(null);

    when(userService.saveUser(mockUser1)).thenReturn(mockUser1);

    UserDTO mockUserDTO = new UserDTO(1,"test@example.com","Test","User","123-456-7890","Darmstadt");

    when(userMapper.userToUserDTO(mockUser1)).thenReturn(mockUserDTO);

    // perform the request and assert the response
    mockMvc.perform(post("/api/users").with(csrf())
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(mockCreationUserDTO)))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.email").value("test@example.com"))
        .andExpect(jsonPath("$.firstName").value("Test"))
        .andExpect(jsonPath("$.lastName").value("User"))
        .andExpect(jsonPath("$.phone").value("123-456-7890"))
        .andExpect(jsonPath("$.location").value("Darmstadt"));
  }

  @Test
  void testAddNewUserWithNullEmailBAD_REQUEST() throws Exception {
    // mock the service and mapper methods
    this.mockCreationUserDTO.setEmail(null);
    /* setup mock */
    when(userMapper.creationUserDTOToUser(mockCreationUserDTO)).thenReturn(null);

    // perform the request and assert the response
    mockMvc.perform(post("/api/users").with(csrf())
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(mockCreationUserDTO)))
        .andExpect(status().isBadRequest());
  }

  @Test
  void testAddNewUserWithExistingUserCONFLICT() throws Exception {
    // mock the service and mapper methods

    when(userMapper.creationUserDTOToUser(mockCreationUserDTO)).thenReturn(null);

    when(userService.findByEmail(mockCreationUserDTO.getEmail())).thenReturn(new User());

    // perform the request and assert the response
    mockMvc.perform(post("/api/users").with(csrf())
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(mockCreationUserDTO)))
        .andExpect(status().isConflict());
  }

  @Test
  void testGetUserById() throws Exception {
    // create a User object using the constructor
    User mockUser = new User();
    mockUser.setId(1);
    mockUser.setEmail("test@example.com");
    mockUser.setFirstName("Test");
    mockUser.setLastName("User");
    mockUser.setPassword("password");
    mockUser.setPhone("123-456-7890");
    mockUser.setLocation("Darmstadt");

    // mock the service and mapper methods
    when(userService.findUserById(mockUser.getId())).thenReturn(Optional.of(mockUser));

    when(userMapper.userToUserDTO(mockUser)).thenReturn(new UserDTO(mockUser.getId(), mockUser.getEmail(), mockUser.getFirstName(), mockUser.getLastName(), mockUser.getPhone(), mockUser.getLocation()));

    // perform the request and assert the response
    mockMvc.perform(get("/api/users/{id}", mockUser.getId())
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id", is(mockUser.getId())))
        .andExpect(jsonPath("$.email", is(mockUser.getEmail())))
        .andExpect(jsonPath("$.firstName", is(mockUser.getFirstName())))
        .andExpect(jsonPath("$.lastName", is(mockUser.getLastName())))
        .andExpect(jsonPath("$.phone", is(mockUser.getPhone())))
        .andExpect(jsonPath("$.location", is(mockUser.getLocation())));
  }

  @Test
  void testGetUserByIdNOT_FOUND() throws Exception {
    // create a User object using the constructor
    int userId = 1;

    // mock the service and mapper methods
    when(userService.findUserById(userId)).thenReturn(Optional.empty());

    // perform the request and assert the response
    mockMvc.perform(get("/api/users/{id}", userId)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound());
  }

  @Test
  void testGetUserByEmailSuccess() throws Exception {
    // create a User object using the constructor
    User mockUser = new User();
    mockUser.setId(1);
    mockUser.setEmail("test@example.com");
    mockUser.setFirstName("Test");
    mockUser.setLastName("User");
    mockUser.setPassword("password");
    mockUser.setPhone("123-456-7890");
    mockUser.setLocation("Darmstadt");
    // mock the service and mapper methods
    when(userService.findByEmail(mockUser.getEmail())).thenReturn(mockUser);
    when(userMapper.userToUserDTO(mockUser)).thenReturn(new UserDTO(1, mockUser.getEmail(), mockUser.getFirstName(), mockUser.getLastName(), mockUser.getPhone(), mockUser.getLocation()));

    // perform the request and assert the response
    mockMvc.perform(get("/api/users/email")
        .param("email", mockUser.getEmail())
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id", is(1)))
        .andExpect(jsonPath("$.email", is(mockUser.getEmail())))
        .andExpect(jsonPath("$.firstName", is(mockUser.getFirstName())))
        .andExpect(jsonPath("$.lastName", is(mockUser.getLastName())))
        .andExpect(jsonPath("$.phone", is(mockUser.getPhone())))
        .andExpect(jsonPath("$.location", is(mockUser.getLocation())));
  }

  @Test
  void testGetUserByEmailNOT_FOUND() throws Exception {
    // create a User object using the constructor
    String email = "test@example.com";

    // mock the service and mapper methods
    when(userService.findByEmail(email)).thenReturn(null);

    // perform the request and assert the response
    mockMvc.perform(get("/api/users/email")
        .param("email", email)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound());
  }

  @Test
  void testGetUsers_Success() throws Exception {
    // create a list of User objects using the constructor
    User user1 = new User();
    user1.setId(1);
    user1.setEmail("test@example.com");
    user1.setFirstName("Test");
    user1.setLastName("User1");
    user1.setPassword("password");
    user1.setPhone("123-456-7890");
    user1.setLocation("Darmstadt");

    User user2 = new User();
    user2.setId(2);
    user2.setEmail("test2@example.com");
    user2.setFirstName("Test");
    user2.setLastName("User2");
    user2.setPassword("password");
    user2.setPhone("123-456-7890");
    user2.setLocation("Darmstadt");

    List<User> mockUsers = Arrays.asList(user1, user2);
    Pageable pageable = PageRequest.of(0, mockUsers.size());
    Page<User> page = new PageImpl<>(mockUsers, pageable, mockUsers.size());

    when(userRepository.findAll()).thenReturn(mockUsers);

    when(userService.findAll(PageRequest.of(0, 2, Sort.by("created")))).thenReturn(page);

    when(userMapper.userToUserDTO(mockUsers.get(0))).thenReturn(
        new UserDTO(1, "test1@example.com", "Test", "User1", "123-456-7890", "Darmstadt"));

    when(userMapper.userToUserDTO(mockUsers.get(1))).thenReturn(
        new UserDTO(2, "test2@example.com", "Test", "User2", "123-456-7891", "Darmstadt"));

    // perform the request and assert the response
    mockMvc.perform(get("/api/users")
        .param("pageStart", "0")
        .param("pageSize", "2")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }

  @Test
  void testGetUsersWithInvalideParametersBAD_REQUEST() throws Exception {
    // perform the request and assert the response
    mockMvc.perform(get("/api/users")
        .param("pageStart", "-1")
        .param("pageSize", "3")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest());
  }
}