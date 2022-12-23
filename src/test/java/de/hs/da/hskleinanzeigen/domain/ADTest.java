package de.hs.da.hskleinanzeigen.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ADTest {

  @Test
  public void testEquals() {
    // Create two AD objects with the same values
    Category category = new Category();
    User user=new User();
    LocalDateTime localDateTime = LocalDateTime.now();
    AD ad1 = new AD();
    ad1.setId(1);
    ad1.setType(Type.OFFER);
    ad1.setCategory(category);
    ad1.setUser(user);
    ad1.setTitle("Test Title");
    ad1.setDescription("Test Description");
    ad1.setPrice(100);
    ad1.setLocation("Test Location");
    ad1.setCreated(localDateTime);

    AD ad2 = new AD();
    ad2.setId(1);
    ad2.setType(Type.OFFER);
    ad2.setCategory(category);
    ad2.setUser(user);
    ad2.setTitle("Test Title");
    ad2.setDescription("Test Description");
    ad2.setPrice(100);
    ad2.setLocation("Test Location");
    ad2.setCreated(localDateTime);

    // Verify that the two AD objects are equal
    assertTrue(ad1.equals(ad2));
  }


  @Test
  public void testEqualsWithDifferentValues() {
    // Create two AD objects with different values
    AD ad1 = new AD();
    ad1.setId(1);
    ad1.setType(Type.OFFER);
    ad1.setCategory(new Category());
    ad1.setUser(new User());
    ad1.setTitle("Test Title");
    ad1.setDescription("Test Description");
    ad1.setPrice(100);
    ad1.setLocation("Test Location");
    ad1.setCreated(LocalDateTime.now());

    AD ad2 = new AD();
    ad2.setId(2);
    ad2.setType(Type.OFFER);
    ad2.setCategory(new Category());
    ad2.setUser(new User());
    ad2.setTitle("Different Title");
    ad2.setDescription("Different Description");
    ad2.setPrice(200);
    ad2.setLocation("Different Location");
    ad2.setCreated(LocalDateTime.now());

    // Verify that the two AD objects are not equal
    assertFalse(ad1.equals(ad2));
  }


  @Test
  public void testHashCode() {
    // Create two AD objects with the same values
    Category category = new Category();
    User user=new User();
    LocalDateTime localDateTime = LocalDateTime.now();
    AD ad1 = new AD();
    ad1.setId(1);
    ad1.setType(Type.OFFER);
    ad1.setCategory(category);
    ad1.setUser(user);
    ad1.setTitle("Test Title");
    ad1.setDescription("Test Description");
    ad1.setPrice(100);
    ad1.setLocation("Test Location");
    ad1.setCreated(localDateTime);

    AD ad2 = new AD();
    ad2.setId(1);
    ad2.setType(Type.OFFER);
    ad2.setCategory(category);
    ad2.setUser(user);
    ad2.setTitle("Test Title");
    ad2.setDescription("Test Description");
    ad2.setPrice(100);
    ad2.setLocation("Test Location");
    ad2.setCreated(localDateTime);

    // Verify that the two AD objects have the same hash code
    assertEquals(ad1.hashCode(), ad2.hashCode());
  }
  @Test
  public void testSettersAndGetters() {
    // Create a new AD object
    AD ad = new AD();

    // Set the values of all fields using the setters
    ad.setId(1);
    ad.setType(Type.OFFER);
    ad.setCategory(new Category());
    ad.setUser(new User());
    ad.setTitle("Test Title");
    ad.setDescription("Test Description");
    ad.setPrice(100);
    ad.setLocation("Test Location");
    ad.setCreated(LocalDateTime.now());

    // Verify that the setters worked correctly and that we can retrieve the correct values using the getters
    assertEquals(1, ad.getId());
    assertEquals(Type.OFFER, ad.getType());
    assertNotNull(ad.getCategory());
    assertNotNull(ad.getUser());
    assertEquals("Test Title", ad.getTitle());
    assertEquals("Test Description", ad.getDescription());
    assertEquals(100, ad.getPrice());
    assertEquals("Test Location", ad.getLocation());
    assertNotNull(ad.getCreated());
  }

  @Test
  public void testToString() {
    // Create a new AD object
    AD ad = new AD();
    ad.setId(1);
    ad.setType(Type.OFFER);
    ad.setCategory(new Category());
    ad.setUser(new User());
    ad.setTitle("Test Title");
    ad.setDescription("Test Description");
    ad.setPrice(100);
    ad.setLocation("Test Location");

    // Verify that the toString method returns a string representation of the object
    String expectedString = "AD(id=1, type=OFFER, category=Category(id=null, name=null, parent=null), user=User(id=null, email=null, password=null, firstName=null, lastName=null, phone=null, location=null, created=null), title=Test Title, description=Test Description, price=100, location=Test Location, created=null)";
    assertEquals(expectedString, ad.toString());
  }

}