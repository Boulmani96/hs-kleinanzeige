package de.hs.da.hskleinanzeigen.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import de.hs.da.hskleinanzeigen.domain.Category;
import de.hs.da.hskleinanzeigen.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {


  @Mock
  private CategoryRepository mockRepo;

  @InjectMocks
  private CategoryService categoryService;

  private Category sampleCategory;

  @BeforeEach
  void setUp() {
    // Create a sample category to use in the tests
    sampleCategory = new Category();
    sampleCategory.setName("Electronics");
  }
  @Test
  void testFindByName() {
    // Set up the mock to return the sample category when findByName is called
    when(mockRepo.findByName("Electronics")).thenReturn(sampleCategory);

    // Call the findByName method on the CategoryService and store the result
    Category result = categoryService.findCategoryByName("Electronics");

    // Make assertions about the returned value
    assertNotNull(result);
    assertEquals("Electronics", result.getName());
  }


  @Test
  void testSaveCategory() {
    // Call the saveCategory method on the CategoryService
    categoryService.saveCategory(sampleCategory);

    // Verify that the save method on the mock repository was called
    verify(mockRepo).save(sampleCategory);
  }

  @Test
  void testFindCategoryById() throws Exception {
    // Set up the mock to return the sample category when findById is called
    when(mockRepo.findById(1)).thenReturn(Optional.of(sampleCategory));

    // Call the findCategoryById method on the CategoryService and store the result
    Category result = categoryService.findCategoryById(1).get();

    // Make assertions about the returned value
    assertNotNull(result);
    assertEquals("Electronics", result.getName());
  }
  /*
  @Test
  void testFindCategoryById_NotFound() {
    // Set up the mock to return empty when findById is called
    when(mockRepo.findById(1)).thenReturn(null);

    // Call the findCategoryById method on the CategoryService and store the result
    Exception exception = assertThrows(Exception.class, () -> categoryService.findCategoryById(1));

    // Make assertions about the returned exception
    assertEquals("No Category Found", exception.getMessage());
  }

   */
}