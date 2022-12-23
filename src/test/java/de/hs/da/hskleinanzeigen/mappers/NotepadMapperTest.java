package de.hs.da.hskleinanzeigen.mappers;

import static org.junit.jupiter.api.Assertions.*;

import de.hs.da.hskleinanzeigen.domain.AD;
import de.hs.da.hskleinanzeigen.domain.Category;
import de.hs.da.hskleinanzeigen.domain.Notepad;
import de.hs.da.hskleinanzeigen.domain.User;
import de.hs.da.hskleinanzeigen.dtos.CreationNotepadDTO;
import de.hs.da.hskleinanzeigen.dtos.GetNotepadDTO;
import de.hs.da.hskleinanzeigen.dtos.NotepadDTO;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

class NotepadMapperTest {
  @Mock
  private NotepadMapper notepadMapper;

  @Test
  public void testNotepadDTOtoNotepad_Success() {
// create a NotepadDTO object
    notepadMapper = new NotepadMapperImpl();
    NotepadDTO notepadDTO = new NotepadDTO(1,1,"note");

// create a Notepad object using the notepadDTOtoNotepad() method
    Notepad notepad = notepadMapper.notepadDTOtoNotepad(notepadDTO);

// assert that the Notepad object has the same values as the NotepadDTO object
    assertEquals(notepadDTO.getId(), notepad.getId());
    assertEquals(notepadDTO.getNote(), notepad.getNote());
  }

  @Test
  public void testNotepadToNotepadDTO() {
    // Create a Notepad object
    Notepad notepad = new Notepad();
    notepad.setId(1);
    notepad.setNote("This is a test note");
    notepad.setCreated(LocalDateTime.now());
    User user = new User();
    user.setId(1);
    user.setEmail("test@example.com");
    user.setFirstName("Test");
    user.setLastName("User");
    user.setPhone("123456");
    user.setLocation("Test City");
    notepad.setUser(user);
    AD ad = new AD();
    ad.setId(1);
    ad.setTitle("Test Ad");
    ad.setDescription("This is a test ad");
    ad.setPrice(100);
    ad.setCreated(LocalDateTime.now());
    notepad.setAd(ad);

    // Create a NotepadMapper object
    NotepadMapper notepadMapper = new NotepadMapperImpl();

    // Convert the Notepad object to a NotepadDTO object
    NotepadDTO notepadDTO = notepadMapper.notepadToNotepadDTO(notepad);

    // Assert that the values of the NotepadDTO object are correct
    assertEquals(notepad.getId(), notepadDTO.getId());
    assertEquals(notepad.getNote(), notepadDTO.getNote());
    assertEquals(notepad.getAd().getId(), notepadDTO.getAdvertisementId());
  }


  @Test
  public void testCreationNotepadDTOtoNotepad_Success() {
    // Set up test data
    CreationNotepadDTO creationNotepadDTO = new CreationNotepadDTO(1,"Test note");
    NotepadMapper notepadMapper = new NotepadMapperImpl();
    // Call the method under test
    Notepad result = notepadMapper.creationNotepadDTOtoNotepad(creationNotepadDTO);

    // Assert the results
    assertEquals(1, result.getId());
    assertEquals("Test note", result.getNote());
  }


  @Test
  public void testNotepadListToGetNotepadDTOList_Success() {
    notepadMapper = new NotepadMapperImpl();
    // Set up test data
    User user = new User();
    user.setId(1);
    user.setEmail("test@example.com");
    user.setFirstName("John");
    user.setLastName("Doe");
    user.setPhone("1234567890");
    user.setLocation("New York");
    Category category = new Category();
    category.setId(1);
    category.setName("Test Category");
    AD ad = new AD();
    ad.setId(1);
    ad.setTitle("Test Ad");
    ad.setDescription("This is a test ad.");
    ad.setPrice(100);
    ad.setCategory(category);
    ad.setUser(user);

    Notepad notepad1 = new Notepad();
    notepad1.setId(1);
    notepad1.setNote("This is a test note for notepad 1.");
    notepad1.setCreated(LocalDateTime.of(2022, 1, 1, 12, 0, 0));
    notepad1.setUser(user);
    notepad1.setAd(ad);

    Notepad notepad2 = new Notepad();
    notepad2.setId(2);
    notepad2.setNote("This is a test note for notepad 2.");
    notepad2.setCreated(LocalDateTime.of(2022, 1, 2, 12, 0, 0));
    notepad2.setUser(user);
    notepad2.setAd(ad);

    List<Notepad> notepadList = Arrays.asList(notepad1, notepad2);

    // Test the mapper method
    List<GetNotepadDTO> notepadDTOList = notepadMapper.notepadListToGetNotepadDTOList(notepadList);

    // Verify the results
    assertEquals(2, notepadDTOList.size());
    assertEquals(1, (int)notepadDTOList.get(0).getId());
    assertEquals("This is a test note for notepad 1.", notepadDTOList.get(0).getNote());
    assertEquals("Test Ad", notepadDTOList.get(0).getAdvertisement().getTitle());
    assertEquals(2, (int)notepadDTOList.get(1).getId());
    assertEquals("This is a test note for notepad 2.", notepadDTOList.get(1).getNote());
    assertEquals("Test Ad", notepadDTOList.get(1).getAdvertisement().getTitle());
  }


  @Test
  public void testNotepadToGetNotepadDTO_Success() {
    // Set up test data
    notepadMapper = new NotepadMapperImpl();
    Notepad notepad = new Notepad();
    notepad.setId(1);
    notepad.setNote("This is a test note.");
    notepad.setCreated(LocalDateTime.of(2022, 1, 1, 0, 0, 0));

    User user = new User();
    user.setId(1);
    user.setEmail("test@test.com");
    user.setFirstName("John");
    user.setLastName("Doe");
    user.setPhone("1234567890");
    user.setLocation("Test City");
    notepad.setUser(user);

    AD ad = new AD();
    ad.setId(1);
    ad.setTitle("Test AD");
    ad.setDescription("This is a test AD.");
    ad.setPrice(100);
    ad.setCreated(LocalDateTime.of(2022, 1, 1, 0, 0, 0));
    notepad.setAd(ad);

    // Call method under test
    GetNotepadDTO getNotepadDTO = notepadMapper.notepadToGetNotepadDTO(notepad);

    // Verify results
    assertEquals(1, getNotepadDTO.getId());
    assertEquals("This is a test note.", getNotepadDTO.getNote());
    assertEquals(1, getNotepadDTO.getAdvertisement().getId());
    assertEquals("Test AD", getNotepadDTO.getAdvertisement().getTitle());
    assertEquals("This is a test AD.", getNotepadDTO.getAdvertisement().getDescription());
    assertEquals(100.0, getNotepadDTO.getAdvertisement().getPrice(), 0.001);
  }


  @Test
  void userToUserDTO() {
  }

  @Test
  void userDTOtoUser() {
  }

  @Test
  void adToAdDTO() {
  }

  @Test
  void adDTOtoAd() {
  }
}