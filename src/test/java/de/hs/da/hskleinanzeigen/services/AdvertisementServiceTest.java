package de.hs.da.hskleinanzeigen.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import de.hs.da.hskleinanzeigen.domain.AD;
import de.hs.da.hskleinanzeigen.domain.Category;
import de.hs.da.hskleinanzeigen.domain.Type;
import de.hs.da.hskleinanzeigen.domain.User;
import de.hs.da.hskleinanzeigen.repository.AdvertisementRepository;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;

@ExtendWith(MockitoExtension.class)
class AdvertisementServiceTest {
  @Mock
  private AdvertisementRepository mockRepo;

  @InjectMocks
  private AdvertisementService advertisementService;

  private AD sampleAdvertisement;

  @BeforeEach
  void setUp() {
    Category sampleCategory = new Category();
    sampleCategory.setName("Electronics");
    sampleCategory.setId(1);
    User sampleUser = new User();
    sampleUser.setFirstName("John");
    sampleUser.setLastName("Doe");
    sampleUser.setPassword("password123");
    sampleUser.setCreated(LocalDateTime.now());
    sampleUser.setEmail("john@example.com");
    // Create a sample advertisement to use in the tests
    sampleAdvertisement = new AD();
    sampleAdvertisement.setId(1);
    sampleAdvertisement.setDescription("Test Ad Description");
    sampleAdvertisement.setType( Type.OFFER);
    sampleAdvertisement.setPrice(100);
    sampleAdvertisement.setCategory(sampleCategory);
    sampleAdvertisement.setUser(sampleUser);
    sampleAdvertisement.setCreated(LocalDateTime.now());
    sampleAdvertisement.setTitle("Test Ad");
  }

  @Test
  void testSaveAdvertisement() {
    // Call the saveAdvertisement method on the AdvertisementService
    advertisementService.saveAdvertisement(sampleAdvertisement);

    // Verify that the save method on the mock repository was called
    verify(mockRepo).save(sampleAdvertisement);
  }

  @Test
  void testFindADById() {
    // Set up the mock to return the sample advertisement when findById is called
    when(mockRepo.findById(1)).thenReturn(java.util.Optional.of(sampleAdvertisement));

    // Call the findADById method on the AdvertisementService and store the result
    AD result = advertisementService.findADById(1).get();

    // Make assertions about the returned value
    assertNotNull(result);
    assertEquals(1, result.getId());
    assertEquals("Test Ad", result.getTitle());
    assertEquals(Type.OFFER, result.getType());
    assertEquals(100, result.getPrice());
    assertEquals(1, result.getCategory().getId());
  }

  @Test
  void testFindAllAD() {
    // Set up the mock to return a list of advertisements when findAll is called
    when(mockRepo.findAll()).thenReturn(Arrays.asList(sampleAdvertisement));

    // Call the findAllAD method on the AdvertisementService and store the result
    List<AD> result = advertisementService.findAllAD();

    // Make assertions about the returned list
    assertNotNull(result);
    assertEquals(1, result.size());
    assertEquals(1, result.get(0).getId());
    assertEquals("Test Ad", result.get(0).getTitle());
    assertEquals(Type.OFFER, result.get(0).getType());
    assertEquals(100, result.get(0).getPrice());
    assertEquals(1, result.get(0).getCategory().getId());
  }

  @Test
  void testFindByType() {
    // Set up the mock to return a list of advertisements when findByType is called
    when(mockRepo.findByType(Type.OFFER, PageRequest.of(0, 10))).thenReturn(Arrays.asList(sampleAdvertisement));

    // Call the findByType method on the AdvertisementService and store the result
    List<AD> result = advertisementService.findByType(Type.OFFER, PageRequest.of(0, 10));

    // Make assertions about the returned list
    assertNotNull(result);
    assertEquals(1, result.size());
    assertEquals(1, result.get(0).getId());
    assertEquals("Test Ad", result.get(0).getTitle());
    assertEquals(Type.OFFER, result.get(0).getType());
    assertEquals(100, result.get(0).getPrice());
    assertEquals(1, result.get(0).getCategory().getId());
  }


  @Test
  void testFindByCategory_id() {
    // Set up the mock to return a list of advertisements when findByCategory_id is called
    when(mockRepo.findByCategory_id(1, PageRequest.of(0, 10))).thenReturn(Arrays.asList(sampleAdvertisement));

    // Call the findByCategory_id method on the AdvertisementService and store the result
    List<AD> result = advertisementService.findByCategory_id(1, PageRequest.of(0, 10));

    // Make assertions about the returned list
    assertNotNull(result);
    assertEquals(1, result.size());
    assertEquals(1, result.get(0).getId());
    assertEquals("Test Ad", result.get(0).getTitle());
    assertEquals(Type.OFFER, result.get(0).getType());
    assertEquals(100, result.get(0).getPrice());
    assertEquals(1, result.get(0).getCategory().getId());
  }


  @Test
  void testFindByPriceFromTo() {
    // Set up the mock to return a list of advertisements when findByPriceFromTo is called
    when(mockRepo.findByPriceFromTo(50, 150, PageRequest.of(0, 10))).thenReturn(Arrays.asList(sampleAdvertisement));

    // Call the findByPriceFromTo method on the AdvertisementService and store the result
    List<AD> result = advertisementService.findByPriceFromTo(50, 150, PageRequest.of(0, 10));

    // Make assertions about the returned list
    assertNotNull(result);
    assertEquals(1, result.size());
    assertEquals(1, result.get(0).getId());
    assertEquals("Test Ad", result.get(0).getTitle());
    assertEquals(Type.OFFER, result.get(0).getType());
    assertEquals(100, result.get(0).getPrice());
    assertEquals(1, result.get(0).getCategory().getId());
  }

}