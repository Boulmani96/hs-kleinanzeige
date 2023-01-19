package de.hs.da.hskleinanzeigen.controller;

import static de.hs.da.hskleinanzeigen.domain.Type.OFFER;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.hs.da.hskleinanzeigen.domain.AD;
import de.hs.da.hskleinanzeigen.domain.Category;
import de.hs.da.hskleinanzeigen.domain.Type;
import de.hs.da.hskleinanzeigen.domain.User;
import de.hs.da.hskleinanzeigen.dtos.AdDTO;
import de.hs.da.hskleinanzeigen.dtos.CategoryDTO;
import de.hs.da.hskleinanzeigen.dtos.CreationAdDTO;
import de.hs.da.hskleinanzeigen.dtos.UserDTO;
import de.hs.da.hskleinanzeigen.mappers.AdvertisementMapper;
import de.hs.da.hskleinanzeigen.mappers.UserMapper;
import de.hs.da.hskleinanzeigen.services.AdvertisementService;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.h2.command.dml.MergeUsing.When;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(AdvertisementController.class)
@WithMockUser(username = "user", password = "user", roles = "user")
class AdvertisementControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private UserController userController;


  @MockBean
  private AdvertisementService advertisementService;

  @MockBean
  private CategoryController categoryController;


  @MockBean
  private UserMapper userMapper;
  @MockBean
  private AdvertisementMapper advertisementMapper;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  void testAddNewAdvertisement_Success() throws Exception {
    // mock the service and mapper methods
   // advertisementMapper= new AdvertisementMapperImpl();
    int mockCategoryId = 1;
    int mockUserId = 1;
    Category category = new Category();
    category.setName("Electronics");
    category.setId(1);
    User user = new User();
    user.setFirstName("John");
    user.setLastName("Doe");
    user.setPassword("password123");
    user.setCreated(LocalDateTime.now());
    user.setEmail("john@example.com");
    CategoryDTO categoryDTO = new CategoryDTO(1, "TestCategory");
    UserDTO userDTO = new UserDTO(1, "test@example.com", "Test", "User", "123-456-7890", "Testville");
    CreationAdDTO mockCreationAdDTO = new CreationAdDTO(
        "OFFER",categoryDTO,userDTO, "title", "description",
        100, "Testville"
         );
    CreationAdDTO creationAdDTO = new CreationAdDTO("OFFER", categoryDTO, userDTO, "title", "description",
        100, "Testville");
    CategoryDTO mockCategoryDTO = new CategoryDTO(mockCategoryId, "Test Category");
    UserDTO mockUserDTO = new UserDTO(mockUserId, "test@example.com", "Test", "User",
        "123-456-7890", "Testville");
    AD mockAd = new AD();
    mockAd.setId(1);
    mockAd.setType(OFFER);
    mockAd.setCategory(category);
    mockAd.setTitle("Test Ad");
    mockAd.setDescription("Test ad description");
    mockAd.setUser(user);
    mockAd.setCreated(LocalDateTime.now());
    AdDTO mockAdDTO = new AdDTO(
        1,
        "OFFER",
        mockCategoryDTO,
        mockUserDTO,
        "Test Ad",
        "Test ad description",
        100,
        "Testville"
    );
    when(categoryController.getCategoryByID(mockCategoryId)).thenReturn(ResponseEntity.ok(mockCategoryDTO));
    when(userController.getUserByID(mockUserId)).thenReturn(ResponseEntity.ok(mockUserDTO));
    when(advertisementMapper.creationAdDTOtoAd(mockCreationAdDTO)).thenReturn(mockAd);
    doNothing().when(advertisementService).saveAdvertisement(mockAd);
    when(advertisementMapper.adToAdDTO(mockAd)).thenReturn(mockAdDTO);


    // perform the request and assert the response
    mockMvc.perform(post("/api/advertisements").with(csrf())
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(creationAdDTO)))
        .andExpect(status().isCreated());
  }
  @Test
  void testGetAdvertisements_Success() throws Exception {
    List<AD> data = new ArrayList<>();
    data.add(mock(AD.class));
    data.add(mock(AD.class));
    data.add(mock(AD.class));

    int mockCategoryId = 1;
    int mockUserId = 1;
    Category category = new Category();
    category.setName("Electronics");
    category.setId(1);
    User user = new User();
    user.setFirstName("John");
    user.setLastName("Doe");
    user.setPassword("password123");
    user.setCreated(LocalDateTime.now());
    user.setEmail("john@example.com");
    CategoryDTO categoryDTO = new CategoryDTO(1, "TestCategory");
    UserDTO userDTO = new UserDTO(1, "test@example.com", "Test", "User", "123-456-7890", "Testville");
    CreationAdDTO mockCreationAdDTO = new CreationAdDTO(
        "OFFER",categoryDTO,userDTO, "title", "description",
        100, "Testville"
    );
    CreationAdDTO creationAdDTO = new CreationAdDTO("OFFER", categoryDTO, userDTO, "title", "description",
        100, "Testville");
    CategoryDTO mockCategoryDTO = new CategoryDTO(mockCategoryId, "Test Category");
    UserDTO mockUserDTO = new UserDTO(mockUserId, "test@example.com", "Test", "User",
        "123-456-7890", "Testville");
    AD mockAd = new AD();
    mockAd.setId(1);
    mockAd.setType(OFFER);
    mockAd.setCategory(category);
    mockAd.setTitle("Test Ad");
    mockAd.setDescription("Test ad description");
    mockAd.setUser(user);
    mockAd.setCreated(LocalDateTime.now());
    AdDTO mockAdDTO = new AdDTO(
        1,
        "OFFER",
        mockCategoryDTO,
        mockUserDTO,
        "Test Ad",
        "Test ad description",
        100,
        "Testville"
    );
    data.add(mockAd);

    PageRequest pr = PageRequest.of(1,3);
    when(advertisementService.findAllAD()).thenReturn(data);
    when(advertisementService.findByType(OFFER,pr)).thenReturn(data);
    when(advertisementService.findByCategory_id(1,pr)).thenReturn(data);
    when(advertisementService.findByPriceFromTo(50,150,pr)).thenReturn(data);

    // perform the request and assert the response
    mockMvc.perform(get("/api/advertisements")
        .param("type","OFFER")
        .param("category","1")
        .param("pageStart", "1")
        .param("pageSize", "3")
        .param("priceFrom","50")
        .param("priceTo","150"))
        .andExpect(status().isOk());
  }
  @Test
  void testGetAdvertisements_BAD_REQUEST() throws Exception {
    // perform the request and assert the response
    mockMvc.perform(get("/api/advertisements")
        .param("pageStart", "-1")
        .param("pageSize", "3")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest());
  }
  @Test
  void testGetAdvertisements_NO_CONTENT() throws Exception {
    // perform the request and assert the response
    mockMvc.perform(get("/api/advertisements")
        .param("pageStart", "1")
        .param("pageSize", "3")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNoContent());
  }

}











/*
@ExtendWith(MockitoExtension.class)
public class AdvertisementControllerTest {

  @Mock
  private AdvertisementService advertisementService;

  @Mock
  private CategoryController categoryController;

  @Mock
  private UserController userController;

  @Mock
  private AdvertisementMapper advertisementMapper;

  @InjectMocks
  private AdvertisementController advertisementController;

  @BeforeEach
  public void setUp() throws Exception {
    // Set up mock behavior for dependencies
    /*when(categoryController.getCategoryByID(anyInt()))
        .thenReturn(new ResponseEntity<>(new CategoryDTO(1, "TestCategory"), HttpStatus.OK));
    when(userController.getUserByID(anyInt())).thenReturn(new ResponseEntity<>(
        new UserDTO(1, "test@example.com", "Test", "User", "123-456-7890", "Testville"),
        HttpStatus.OK));


  }


  @Test
  public void testAddNewAdvertisement() throws Exception {
    // Set up the request body
    CategoryDTO category = new CategoryDTO(1, "TestCategory");
    UserDTO user = new UserDTO(1, "test@example.com", "Test", "User", "123-456-7890", "Testville");

    CreationAdDTO creationAdDTO = new CreationAdDTO("OFFER", category, user, "title", "description",
        100, "Testville");
    creationAdDTO.setType("OFFER");

    // Call the addNewAdvertisement() method on the AdvertisementController instance
    ResponseEntity<AdDTO> response = advertisementController.addNewAdvertisement(creationAdDTO);

    // Verify that the returned value is as expected
    assertEquals(HttpStatus.CREATED, response.getStatusCode());
  }

  @Test
  public void testGetAdvertisementById() {
    // Set up the mock behavior for the advertisementService
    when(advertisementService.findADById(1)).thenReturn(Optional.of(new AD()));

    // Call the getAdvertisementById() method on the AdvertisementController instance
    ResponseEntity<AdDTO> response = advertisementController.getAdvertisementById(1);

    // Verify that the returned value is as expected
    assertEquals(HttpStatus.OK, response.getStatusCode());
  }



  @Test
  public void testGetAdvertisements_Success() throws Exception {
    // Create a list of advertisements
    List<AD> advertisements = createAdvertisementList();

    // Set up the mock behavior for the AdvertisementService
    when(advertisementService.findAllAD()).thenReturn(advertisements);

    // Call the getAdvertisements() method and verify the response
    ResponseEntity<Page> response = advertisementController.getAdvertisements(null, -1, -1, -1, 0, 10);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertNotNull(response.getBody());
    //assertEquals(2, response.getBody().getTotalElements());
  }

}





/*
class AdvertisementControllerTest {

  @Test
  void addNewAdvertisement() {
  }

  @Test
  void getAdvertisementById() {
  }

  @Test
  void getAdvertisements() {
  }
}
*/



