package de.hs.da.hskleinanzeigen.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CategoryTest {
  private Category category = new Category();
  private Category category1 = new Category();
  private Category category2 = new Category();
  @BeforeEach
  public void setUp() {

    category1.setId(1);
    category1.setName("Test Category");
    category1.setParent(new Category());


    category2.setId(2);
    category2.setName("Different Category");
    category2.setParent(null);

  }


  @Test
  public void testCategoryConstructor() {
    assertNotNull(category);
    assertEquals(null, category.getId());
    assertNull(category.getName());
    assertNull(category.getParent());
  }

  @Test
  public void testSettersAndGetters() {
    Category category = new Category();
    category.setId(1);
    category.setName("Test Category");
    category.setParent(new Category());

    // Verify that the setters worked correctly and that we can retrieve the correct values using the getters
    assertEquals(1, category.getId());
    assertEquals("Test Category", category.getName());
    assertNotNull(category.getParent());
  }

  @Test
  public void testEquals() {
    assertTrue(category1.equals(category1));
  }
  @Test
  public void testEqualsWithDifferentValues() {
    assertFalse(category1.equals(category2));
  }




  @Test
  public void testHashCode() {
    assertEquals(category1.hashCode(), category1.hashCode());
  }


  @Test
  public void testToString() {
    // Verify that the toString method returns a string representation of the object
    String expectedString = "Category(id=1, name=Test Category, parent=Category(id=null, name=null, parent=null))";
    assertEquals(expectedString, category1.toString());
  }

}