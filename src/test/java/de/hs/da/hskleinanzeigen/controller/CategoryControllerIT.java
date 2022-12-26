package de.hs.da.hskleinanzeigen.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.hs.da.hskleinanzeigen.domain.Category;
import de.hs.da.hskleinanzeigen.dtos.CategoryDTO;
import de.hs.da.hskleinanzeigen.dtos.CreationCategoryDTO;
import de.hs.da.hskleinanzeigen.mappers.CategoryMapper;
import de.hs.da.hskleinanzeigen.services.CategoryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(CategoryController.class)
@WithMockUser(username = "user", password = "user", roles = "user")
class CategoryControllerIT {
  @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private CategoryService categoryService;

  @MockBean
  private CategoryMapper categoryMapper;

  @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
  @Autowired
  private ObjectMapper objectMapper;

  @Test
  void testAddNewCategory_Success() throws Exception {
    // mock the service and mapper methods
    CreationCategoryDTO mockCreationCategoryDTO = new CreationCategoryDTO("Test Category",0);
    mockCreationCategoryDTO.setName("Test Category");
    mockCreationCategoryDTO.setParentId(0);
    Category mockCategory = new Category();
    mockCategory.setId(1);
    mockCategory.setName("Test Category");
    mockCategory.setParent(new Category());
    when(categoryMapper.CreationCategoryDTOtoCategory(mockCreationCategoryDTO)).thenReturn(mockCategory);
    when(categoryService.findCategoryByName("Test Category")).thenReturn(null);
    when(categoryService.saveCategory(mockCategory)).thenReturn(mockCategory);
    CategoryDTO mockCategoryDTO = new CategoryDTO(1,"Test Category");
    mockCategoryDTO.setId(1);
    mockCategoryDTO.setName("Test Category");

    when(categoryMapper.categoryToCategoryDTO(mockCategory)).thenReturn(mockCategoryDTO);

    // perform the request and assert the response
    mockMvc.perform(post("/api/categories").with(csrf())
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(mockCreationCategoryDTO)))
        .andExpect(status().isCreated());
  }
  @Test
  void testAddNewCategory_BadRequest() throws Exception {
    // mock the service and mapper methods
    CreationCategoryDTO mockCreationCategoryDTO = new CreationCategoryDTO("Test Category",1);
    mockCreationCategoryDTO.setName(null);
    mockCreationCategoryDTO.setParentId(1);
    Category mockCategory = new Category();
    mockCategory.setId(1);
    mockCategory.setName("Test Category");
    mockCategory.setParent(new Category());
    when(categoryMapper.CreationCategoryDTOtoCategory(mockCreationCategoryDTO)).thenReturn(mockCategory);
    when(categoryService.findCategoryByName("Test Category")).thenReturn(null);
    when(categoryService.saveCategory(mockCategory)).thenReturn(mockCategory);
    CategoryDTO mockCategoryDTO = new CategoryDTO(1,"Test Category");
    when(categoryMapper.categoryToCategoryDTO(mockCategory)).thenReturn(mockCategoryDTO);

    // perform the request and assert the response
    mockMvc.perform(post("/api/categories").with(csrf())
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(mockCreationCategoryDTO)))
        .andExpect(status().isBadRequest());
  }
  @Test
  void testAddNewCategory_BadRequest_ParentCategoryNotAvailable() throws Exception {
    // mock the service and mapper methods
    CreationCategoryDTO mockCreationCategoryDTO = new CreationCategoryDTO("Test Category",1);
    Category mockCategory = new Category();
    mockCategory.setId(1);
    mockCategory.setName("Test Category");
    mockCategory.setParent(new Category());
    when(categoryMapper.CreationCategoryDTOtoCategory(mockCreationCategoryDTO)).thenReturn(mockCategory);
    when(categoryService.findCategoryByName("Test Category")).thenReturn(null);
    when(categoryService.saveCategory(mockCategory)).thenReturn(mockCategory);
    when(categoryService.findCategoryById(1)).thenReturn(Optional.empty());

    // perform the request and assert the response
    mockMvc.perform(post("/api/categories").with(csrf())
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(mockCreationCategoryDTO)))
        .andExpect(status().isBadRequest());
  }

  @Test
  void testAddNewCategory_Conflict() throws Exception {
    // mock the service and mapper methods
    CreationCategoryDTO mockCreationCategoryDTO = new CreationCategoryDTO("Test Category",0);
    mockCreationCategoryDTO.setName("Test Category");
    mockCreationCategoryDTO.setParentId(0);
    Category mockCategory = new Category();
    mockCategory.setId(1);
    mockCategory.setName("Test Category");
    mockCategory.setParent(new Category());
    when(categoryMapper.CreationCategoryDTOtoCategory(mockCreationCategoryDTO)).thenReturn(mockCategory);
    when(categoryService.findCategoryByName("Test Category")).thenReturn(mockCategory);
    when(categoryService.saveCategory(mockCategory)).thenReturn(mockCategory);
    CategoryDTO mockCategoryDTO = new CategoryDTO(0,"Test Category");
    mockCategoryDTO.setId(0);
    mockCategoryDTO.setName("Test Category");
    when(categoryMapper.categoryToCategoryDTO(mockCategory)).thenReturn(mockCategoryDTO);

    // perform the request and assert the response
    mockMvc.perform(post("/api/categories").with(csrf())
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(mockCreationCategoryDTO)))
        .andExpect(status().isConflict());
  }

  @Test
  void testGetCategoryById_Success() throws Exception {
    // mock the service and mapper methods
    Category mockCategory = new Category();
    mockCategory.setId(1);
    mockCategory.setName("TestCategory");
    Optional<Category> optionalMockCategory = Optional.of(mockCategory);
    when(categoryService.findCategoryById(1)).thenReturn(Optional.of(mockCategory));
    CategoryDTO mockCategoryDTO = new CategoryDTO(1,"TestCategory");
    mockCategoryDTO.setId(1);
    mockCategoryDTO.setName("TestCategory");
    when(categoryMapper.categoryToCategoryDTO(mockCategory)).thenReturn(mockCategoryDTO);

    // perform the request and assert the response
    mockMvc.perform(get("/api/categories/1"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.id").value(1))
        .andExpect(jsonPath("$.name").value("TestCategory"));

  }

  @Test
  void testGetCategoryById_NotFound() throws Exception {
    // mock the service and mapper methods
    when(categoryService.findCategoryById(1)).thenReturn(Optional.empty());

    // perform the request and assert the response
    mockMvc.perform(get("/api/categories/1"))
        .andExpect(status().isNotFound());
  }

  @Test
  void testGetCategoryByName_Success() throws Exception {
    // mock the service and mapper methods
    Category mockCategory = new Category();
    mockCategory.setId(1);
    mockCategory.setName("TestCategory");
    when(categoryService.findCategoryByName("TestCategory")).thenReturn(mockCategory);
    CategoryDTO mockCategoryDTO = new CategoryDTO(1,"TestCategory");
    mockCategoryDTO.setId(1);
    mockCategoryDTO.setName("TestCategory");
    when(categoryMapper.categoryToCategoryDTO(mockCategory)).thenReturn(mockCategoryDTO);

    // perform the request and assert the response
    mockMvc.perform(get("/api/categories/name").param("name","TestCategory"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.id").value(1))
        .andExpect(jsonPath("$.name").value("TestCategory"));
  }
  @Test
  void testGetCategoryByName_NotFound() throws Exception {
    // mock the service and mapper methods
    when(categoryService.findCategoryByName("TestCategory")).thenReturn(null);

    // perform the request and assert the response
    mockMvc.perform(get("/api/categories/name").param("name","TestCategory"))
        .andExpect(status().isNotFound());
  }




@Test
  public void testGetAllCategories() throws Exception {

    MvcResult result = mockMvc.perform(get("/api/categories/all"))
        .andExpect(status().isOk())
        .andReturn();

    // Parse the response body
    String response = result.getResponse().getContentAsString();

    // Verify that the response is correct
    assertEquals("Hello", response);

  }

}


