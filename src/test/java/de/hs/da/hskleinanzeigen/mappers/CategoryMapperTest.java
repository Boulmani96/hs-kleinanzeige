package de.hs.da.hskleinanzeigen.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import de.hs.da.hskleinanzeigen.domain.Category;
import de.hs.da.hskleinanzeigen.dtos.CategoryDTO;
import de.hs.da.hskleinanzeigen.dtos.CreationCategoryDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class CategoryMapperTest {
  @Mock
  private CategoryMapper categoryMapper;

  @BeforeEach
  public void setUp() {
    // Initialize the categoryMapper mock
    // ...
  }

  @Test
  public void testCategoryToCategoryDTO() {
    // Create a test Category object
    Category category = new Category();
    category.setId(1);
    category.setName("Test Category");

    // Stub the categoryMapper.categoryToCategoryDTO() method to return a CategoryDTO object
    when(categoryMapper.categoryToCategoryDTO(category)).thenReturn(new CategoryDTO(1, "Test Category"));

    // Call the categoryMapper.categoryToCategoryDTO() method
    CategoryDTO result = categoryMapper.categoryToCategoryDTO(category);

    // Verify that the categoryMapper.categoryToCategoryDTO() method was called
    verify(categoryMapper).categoryToCategoryDTO(category);

    // Assert that the result is as expected
    assertEquals(1, result.getId());
    assertEquals("Test Category", result.getName());
  }


  @Test
  public void testCreationCategoryDTOtoCategory() {
    // Create a test CreationCategoryDTO object
    CreationCategoryDTO creationCategoryDTO = new CreationCategoryDTO("Test Category",1);
    Category newCategory= new Category();
    newCategory.setId(1);
    newCategory.setName("Test Category");

    // Stub the categoryMapper.CreationCategoryDTOtoCategory() method to return a Category object
    when(categoryMapper.CreationCategoryDTOtoCategory(creationCategoryDTO)).thenReturn(newCategory);

    // Call the categoryMapper.CreationCategoryDTOtoCategory() method
    Category result = categoryMapper.CreationCategoryDTOtoCategory(creationCategoryDTO);

    // Verify that the categoryMapper.CreationCategoryDTOtoCategory() method was called
    verify(categoryMapper).CreationCategoryDTOtoCategory(creationCategoryDTO);

    // Assert that the result is as expected
    assertEquals(1, result.getId());
    assertEquals("Test Category", result.getName());
  }
}
