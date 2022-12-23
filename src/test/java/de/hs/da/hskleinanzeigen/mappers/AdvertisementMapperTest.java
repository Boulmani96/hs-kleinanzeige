package de.hs.da.hskleinanzeigen.mappers;

import static org.junit.jupiter.api.Assertions.*;

import de.hs.da.hskleinanzeigen.domain.AD;
import de.hs.da.hskleinanzeigen.domain.Category;
import de.hs.da.hskleinanzeigen.domain.User;
import de.hs.da.hskleinanzeigen.dtos.AdDTO;
import de.hs.da.hskleinanzeigen.dtos.CategoryDTO;
import de.hs.da.hskleinanzeigen.dtos.CreationAdDTO;
import de.hs.da.hskleinanzeigen.dtos.UserDTO;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AdvertisementMapperTest {
  @Mock
  private AdvertisementMapper advertisementMapper;
  @Test
  public void testAdToAdDTO_Success() {
    // Create an AD domain object with test values
    AD ad = new AD();
    ad.setId(1);
    ad.setTitle("Test Ad");
    ad.setDescription("This is a test ad");
    ad.setPrice(100);
    ad.setLocation("Test Location");
    ad.setCreated(LocalDateTime.now());

    // Convert the AD to an AdDTO using the mapper
    AdvertisementMapper mapper = new AdvertisementMapperImpl();
    AdDTO adDto = mapper.adToAdDTO(ad);

    // Assert that the values of the AdDTO match the expected test values
    assertEquals(1, adDto.getId());
    assertEquals("Test Ad", adDto.getTitle());
    assertEquals("This is a test ad", adDto.getDescription());
    assertEquals(100, adDto.getPrice());
    assertEquals("Test Location", adDto.getLocation());
  }


  @Test
  public void testCreationAdDTOtoAd_Success() {
    // Set up test data
    Category category = new Category();
    category.setId(1);
    category.setName("Test Category");
    User newUser = new User();
    newUser.setId(1);
    newUser.setEmail("test@example.com");
    newUser.setFirstName("Test");
    newUser.setLastName("User");
    newUser.setPassword("password");
    newUser.setPhone("123-456-7890");
    newUser.setLocation("Testville");
    newUser.setCreated(LocalDateTime.now());
    AdvertisementMapper mapper = new AdvertisementMapperImpl();

    UserDTO userDTO = mapper.userToUserDTO(newUser);
    CategoryDTO categoryDTO= mapper.categoryToCategoryDTO(category);
    CreationAdDTO creationAdDTO = new CreationAdDTO("OFFER", categoryDTO, userDTO, "title", "description",
        100, "Testville");
    // Execute method under test
    AD ad = mapper.creationAdDTOtoAd(creationAdDTO);

    // Verify results
    assertEquals(creationAdDTO.getTitle(), ad.getTitle());
    assertEquals(creationAdDTO.getDescription(), ad.getDescription());
    assertEquals(creationAdDTO.getPrice(), ad.getPrice());
    assertEquals(mapper.categoryDTOtoCategory(creationAdDTO.getCategory()),ad.getCategory());
    assertEquals(mapper.userDTOtoUser(creationAdDTO.getUser()), ad.getUser());
  }

  @Test
  public void testListAdToAdDTO() {
    // create a list of AD objects
    List<AD> AdList = new ArrayList<>();
    AD ad1 = new AD();
    ad1.setId(1);
    ad1.setTitle("Ad 1");
    ad1.setDescription("Description for ad 1");
    AdList.add(ad1);

    AD ad2 = new AD();
    ad2.setId(2);
    ad2.setTitle("Ad 2");
    ad2.setDescription("Description for ad 2");
    AdList.add(ad2);
    AdvertisementMapper mapper = new AdvertisementMapperImpl();

    // pass the list of AD objects to the listAdToAdDTO method
    List<AdDTO> AdDTOList = mapper.listAdToAdDTO(AdList);

    // verify that the returned list of AdDTO objects has the same number of elements as the list of AD objects
    assertEquals(AdList.size(), AdDTOList.size());

    // verify that the properties of each AdDTO object match the corresponding properties of the AD object
    for (int i = 0; i < AdList.size(); i++) {
      assertEquals(AdList.get(i).getId(), AdDTOList.get(i).getId());
      assertEquals(AdList.get(i).getTitle(), AdDTOList.get(i).getTitle());
      assertEquals(AdList.get(i).getDescription(), AdDTOList.get(i).getDescription());
    }
  }

}