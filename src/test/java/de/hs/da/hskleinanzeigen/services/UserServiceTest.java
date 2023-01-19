package de.hs.da.hskleinanzeigen.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import de.hs.da.hskleinanzeigen.domain.User;
import de.hs.da.hskleinanzeigen.repository.UserRepository;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
  @Mock
  private UserRepository mockRepo;

  @InjectMocks
  private UserService userService;

  private User sampleUser;

  @BeforeEach
  void setUp() {
    // Create a sample sampleUser to use in the tests
    sampleUser = new User();
    sampleUser.setFirstName("John");
    sampleUser.setLastName("Doe");
    sampleUser.setPassword("password123");
    sampleUser.setCreated(LocalDateTime.now());
    sampleUser.setEmail("john@example.com");
  }

  @Test
  void testFindByEmail() {
    // Set up the mock to return the sample user when findByEmail is called
    when(mockRepo.findByEmail("john@example.com")).thenReturn(sampleUser);

    // Call the findByEmail method on the UserService and store the result
    User result = userService.findByEmail("john@example.com");

    // Make assertions about the returned value
    assertNotNull(result);
    assertEquals("john@example.com", result.getEmail());
    assertEquals("John", result.getFirstName());
    assertEquals("Doe", result.getLastName());
  }

  @Test
  void testSaveUser() {
    // Call the saveUser method on the UserService
    userService.saveUser(sampleUser);

    // Verify that the save method on the mock Repository was called
    verify(mockRepo, times(1)).save(sampleUser);
  }

  @Test
  void testFindUserById() throws Exception {
    // Set up the mock to return the sample user when findById is called
    when(mockRepo.findById(1)).thenReturn(Optional.of(sampleUser));

    // Call the findUserById method on the UserService and store the result
    User result = userService.findUserById(1).get();

    // Make assertions about the returned value
    assertNotNull(result);
    assertEquals("john@example.com", result.getEmail());
    assertEquals("John", result.getFirstName());
    assertEquals("Doe", result.getLastName());
  }

  @Test
  void testFindAll() {
    // Create a Page of sample users to return from the mock Repository
    Page<User> samplePage = new PageImpl<>(Arrays.asList(sampleUser));

    // Set up the mock to return the sample page when findAll is called
    when(mockRepo.findAll(any(PageRequest.class))).thenReturn(samplePage);

    // Call the findAll method on the UserService and store the result
    Page<User> result = userService.findAll(PageRequest.of(0, 10));

    // Make assertions about the returned value
    assertNotNull(result);
    assertEquals(1, result.getTotalElements());
    assertEquals("john@example.com", result.getContent().get(0).getEmail());
    assertEquals("John", result.getContent().get(0).getFirstName());
    assertEquals("Doe", result.getContent().get(0).getLastName());
  }
}