package de.hs.da.hskleinanzeigen.repository;

import de.hs.da.hskleinanzeigen.domain.Category;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CategoryRepositoryIT {

  @Autowired
  private CategoryRepository categoryRepository;

  @Test
  public void testSaveCategory() {
    // Create a new category
    Category category = new Category();
    category.setName("Test Category");
    categoryRepository.save(category);

    // Retrieve the saved category from the database
    Category category1 = categoryRepository.findByName(category.getName());

    // Assert that the retrieved category matches the expected values
    assertEquals("Test Category", category1.getName());
  }

  @Test
  public void testFindByName() {
    // Set up test data
    Category category = new Category();
    category.setName("Test Category");
    categoryRepository.save(category);

    // Invoke the code being tested
    Category result = categoryRepository.findByName("Test Category");

    // Verify the results
    assertNotNull(result);
    assertEquals("Test Category", result.getName());
    categoryRepository.delete(category);
  }
}