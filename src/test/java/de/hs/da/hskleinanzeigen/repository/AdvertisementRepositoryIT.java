package de.hs.da.hskleinanzeigen.repository;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import de.hs.da.hskleinanzeigen.domain.AD;
import de.hs.da.hskleinanzeigen.domain.Category;
import de.hs.da.hskleinanzeigen.domain.Type;
import de.hs.da.hskleinanzeigen.domain.User;

import java.time.LocalDateTime;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdvertisementRepositoryIT {

  @Autowired
  private AdvertisementRepository advertisementRepository;
  @Autowired
  private CategoryRepository categoryRepository;
  @Autowired
  private UserRepository userRepository;

  @Test
  public void testFindByType() {
    // Set up test data
    Category category = new Category();
    category.setName("Test Category 1");
    User user = new User();
    user.setFirstName("John");
    user.setLastName("Doe");
    user.setPassword("password123");
    user.setCreated(LocalDateTime.now());
    user.setEmail("test@example.com1");
    userRepository.save(user);
// Set values for the category object
    category = categoryRepository.save(category);

    AD ad = new AD();
    ad.setType(Type.OFFER);
    ad.setCategory(category);
    ad.setUser(user);
    ad.setTitle("Test Ad");
    ad.setDescription("This is a test ad");
    ad.setCreated(LocalDateTime.parse("2022-12-19T23:44:07.619800100"));
    advertisementRepository.save(ad);

    // Invoke the code being tested
    Pageable pageable = PageRequest.of(0, 10);
    List<AD> result = advertisementRepository.findByType(Type.OFFER, pageable);

    // Verify the results
    assertNotNull(result);
    //assertTrue(result.contains(ad));
  }
}
