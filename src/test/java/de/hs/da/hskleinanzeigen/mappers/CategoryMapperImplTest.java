package de.hs.da.hskleinanzeigen.mappers;

import static org.junit.jupiter.api.Assertions.*;

import de.hs.da.hskleinanzeigen.domain.Category;
import de.hs.da.hskleinanzeigen.dtos.CategoryDTO;
import de.hs.da.hskleinanzeigen.dtos.CreationCategoryDTO;
import org.junit.jupiter.api.Test;

class CategoryMapperImplTest {
  private CategoryMapper categoryMapper  = new CategoryMapperImpl();
  @Test
  public void testCategoryMapper() {
    // Test categoryToCategoryDTO()
    Category category = new Category();
    category.setId(1);
    category.setName("Test Category");

    CategoryDTO categoryDTO = categoryMapper.categoryToCategoryDTO(category);
    assertEquals(category.getId(), categoryDTO.getId());
    assertEquals(category.getName(), categoryDTO.getName());

    // Test CreationCategoryDTOtoCategory()
    CreationCategoryDTO creationCategoryDTO = new CreationCategoryDTO("Test CreationCategoryDTO",1);

    Category resultCategory = categoryMapper.CreationCategoryDTOtoCategory(creationCategoryDTO);
    assertEquals(creationCategoryDTO.getName(), resultCategory.getName());
  }

}