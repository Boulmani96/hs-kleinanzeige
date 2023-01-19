package de.hs.da.hskleinanzeigen.repository;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import de.hs.da.hskleinanzeigen.domain.Category;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CategoryRepositoryIT {

  @Autowired
  private CategoryRepository categoryRepository;

  @Autowired
  private DataSource dataSource;

  @Test
  public void testSaveCategory() throws SQLException {
    // Create a new category
    Category category = new Category();
    category.setName("Test Category");
    categoryRepository.save(category);

    // Retrieve the saved category from the database
    Connection connection = dataSource.getConnection();
    Statement statement = connection.createStatement();
    ResultSet resultSet = statement.executeQuery("SELECT * FROM CATEGORY WHERE name = 'Test Category'");
    resultSet.next();
    String name = resultSet.getString("name");

    // Assert that the retrieved category matches the expected values
    assertEquals("Test Category", name);
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