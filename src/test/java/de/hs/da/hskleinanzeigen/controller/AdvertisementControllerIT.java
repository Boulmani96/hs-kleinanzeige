package de.hs.da.hskleinanzeigen.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.hs.da.hskleinanzeigen.domain.AD;
import de.hs.da.hskleinanzeigen.domain.Category;
import de.hs.da.hskleinanzeigen.domain.User;
import de.hs.da.hskleinanzeigen.dtos.AdDTO;
import de.hs.da.hskleinanzeigen.dtos.CategoryDTO;
import de.hs.da.hskleinanzeigen.dtos.CreationAdDTO;
import de.hs.da.hskleinanzeigen.dtos.UserDTO;
import de.hs.da.hskleinanzeigen.mappers.AdvertisementMapper;
import de.hs.da.hskleinanzeigen.services.AdvertisementService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static de.hs.da.hskleinanzeigen.domain.Type.OFFER;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(AdvertisementController.class)
@WithMockUser(username = "user", password = "user", roles = "user")
class AdvertisementControllerIT {

  @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private UserController userController;

  @MockBean
  private AdvertisementService advertisementService;

  @MockBean
  private CategoryController categoryController;

  @MockBean
  private AdvertisementMapper advertisementMapper;

  @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
  @Autowired
  private ObjectMapper objectMapper;

  CategoryDTO mockCategoryDTO;

  UserDTO mockUserDTO;

  CreationAdDTO mockAd;

  @BeforeEach
  void init() {
     mockCategoryDTO = new CategoryDTO(1,"Electronics");
     mockUserDTO = new UserDTO(1, "test@example.com","John","Test","123-456-7890","Darmstadt");
     mockAd = new CreationAdDTO(OFFER.toString(), mockCategoryDTO, mockUserDTO, "Title", "Description", 100, "Darmstadt");
  }

  @Test
  void testAddNewAdvertisementSuccess() throws Exception {
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
    UserDTO userDTO = new UserDTO(1, "test@example.com", "Test", "User", "123-456-7890", "Darmstadt");
    CreationAdDTO mockCreationAdDTO = new CreationAdDTO(
        "OFFER",categoryDTO,userDTO, "title", "description",
        100, "Darmstadt"
         );
    CreationAdDTO creationAdDTO = new CreationAdDTO("OFFER", categoryDTO, userDTO, "title", "description", 100, "Darmstadt");
    CategoryDTO mockCategoryDTO = new CategoryDTO(mockCategoryId, "Test Category");
    UserDTO mockUserDTO = new UserDTO(mockUserId, "test@example.com", "Test", "User",
        "123-456-7890", "Darmstadt");
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
        "Darmstadt"
    );

    when(categoryController.getCategoryByID(mockCategoryId)).thenReturn(ResponseEntity.ok(mockCategoryDTO));

    when(userController.getUserByID(mockUserId)).thenReturn(ResponseEntity.ok(mockUserDTO));

    when(advertisementMapper.creationAdDTOtoAd(mockCreationAdDTO)).thenReturn(mockAd);

    when(advertisementService.saveAdvertisement(mockAd)).thenReturn(mockAd);

    when(advertisementMapper.adToAdDTO(mockAd)).thenReturn(mockAdDTO);

    // perform the request and assert the response
    mockMvc.perform(post("/api/advertisements").with(csrf())
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(creationAdDTO)))
        .andExpect(status().isCreated());
  }

  @Test
  void testAddNewAdWithNullParametersBAD_REQUEST() throws Exception{
    //With null User
    this.mockAd.setUser(null);

    when(advertisementService.saveAdvertisement(advertisementMapper.creationAdDTOtoAd(mockAd))).thenReturn(null);

    mockMvc.perform(post("/api/advertisements").with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(mockAd)))
            .andExpect(status().isBadRequest());

    //With null category
    this.mockAd.setUser(this.mockUserDTO);
    this.mockAd.setCategory(null);

    when(advertisementService.saveAdvertisement(advertisementMapper.creationAdDTOtoAd(mockAd))).thenReturn(null);

    mockMvc.perform(post("/api/advertisements").with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(mockAd)))
            .andExpect(status().isBadRequest());

    //With null User ID
    this.mockAd.getUser().setId(null);
    this.mockAd.setCategory(this.mockCategoryDTO);

    when(advertisementService.saveAdvertisement(advertisementMapper.creationAdDTOtoAd(mockAd))).thenReturn(null);

    mockMvc.perform(post("/api/advertisements").with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(mockAd)))
            .andExpect(status().isBadRequest());

    //With null category ID
    this.mockAd.getUser().setId(1);
    this.mockAd.getCategory().setId(null);
    when(advertisementService.saveAdvertisement(advertisementMapper.creationAdDTOtoAd(mockAd))).thenReturn(null);

    mockMvc.perform(post("/api/advertisements").with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(mockAd)))
            .andExpect(status().isBadRequest());

    //With null title
    this.mockAd.getUser().setId(1);
    this.mockAd.setTitle(null);

    when(advertisementService.saveAdvertisement(advertisementMapper.creationAdDTOtoAd(mockAd))).thenReturn(null);

    mockMvc.perform(post("/api/advertisements").with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(mockAd)))
            .andExpect(status().isBadRequest());

    //With null Type
    this.mockAd.setTitle("Title");
    this.mockAd.setType(null);

    when(advertisementService.saveAdvertisement(advertisementMapper.creationAdDTOtoAd(mockAd))).thenReturn(null);

    mockMvc.perform(post("/api/advertisements").with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(mockAd)))
            .andExpect(status().isBadRequest());
  }

    @Test
    void testAddNewADWithNotFoundUserBAD_REQUEST() throws Exception{
      int userId = 1;

      when(userController.getUserByID(userId)).thenReturn(null);

      mockMvc.perform(post("/api/advertisements").with(csrf())
                      .contentType(MediaType.APPLICATION_JSON)
                      .content(objectMapper.writeValueAsString(mockAd)))
              .andExpect(status().isBadRequest());
    }

    @Test
    void testAddNewADWithNotFoundCategoryBAD_REQUEST() throws Exception{
      int categoryId = 1;

      when(categoryController.getCategoryByID(categoryId)).thenReturn(null);

      mockMvc.perform(post("/api/advertisements").with(csrf())
                      .contentType(MediaType.APPLICATION_JSON)
                      .content(objectMapper.writeValueAsString(mockAd)))
              .andExpect(status().isBadRequest());
    }

  @Test
  void testGetOneAdvertisementByID() throws Exception{
    AD mockAd = mock(AD.class);

    given(advertisementService.findADById(mockAd.getId())).willReturn(Optional.of(mockAd));

    mockMvc.perform(get("/api/advertisements/{id}", mockAd.getId())
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
  }

  @Test
  void testGetOneAdvertisementByIdNotFound() throws Exception{
    int mockId = 1;

    given(advertisementService.findADById(mockId)).willReturn(Optional.empty());

    mockMvc.perform(get("/api/advertisements/{id}", mockId)
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());
  }

  @Test
  void testGetAdvertisementsSuccess() throws Exception {
    List<AD> data = new ArrayList<>();
    data.add(mock(AD.class));
    data.add(mock(AD.class));
    data.add(mock(AD.class));

    Category category = new Category();
    category.setName("Electronics");
    category.setId(1);

    User user = new User();
    user.setFirstName("John");
    user.setLastName("Doe");
    user.setPassword("password123");
    user.setCreated(LocalDateTime.now());
    user.setEmail("john@example.com");

    AD mockAd = new AD();
    mockAd.setId(1);
    mockAd.setType(OFFER);
    mockAd.setCategory(category);
    mockAd.setTitle("Test Ad");
    mockAd.setDescription("Test ad description");
    mockAd.setUser(user);
    mockAd.setCreated(LocalDateTime.now());

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
  void testGetAdvertisementsWithInvalideParametersBAD_REQUEST() throws Exception {
    // perform the request and assert the response
    mockMvc.perform(get("/api/advertisements")
        .param("pageStart", "-1")
        .param("pageSize", "3")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest());
  }

  @Test
  void testGetAdvertisementsNO_CONTENT() throws Exception {
    // perform the request and assert the response
    mockMvc.perform(get("/api/advertisements")
        .param("pageStart", "1")
        .param("pageSize", "3")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNoContent());
  }
}