package de.hs.da.hskleinanzeigen.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import de.hs.da.hskleinanzeigen.domain.AD;
import de.hs.da.hskleinanzeigen.domain.Category;
import de.hs.da.hskleinanzeigen.domain.Notepad;
import de.hs.da.hskleinanzeigen.domain.Type;
import de.hs.da.hskleinanzeigen.domain.User;
import de.hs.da.hskleinanzeigen.repository.NotepadRepository;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class NotepadServiceTest {

  @Mock
  private NotepadRepository mockRepo;

  @InjectMocks
  private NotepadService notepadService;

  private Notepad sampleNotepad;

  private Notepad deletedNotepad;

  @BeforeEach
  void setUp() {
    Category sampleCategory = new Category();
    sampleCategory.setName("Electronics");
    sampleCategory.setId(1);
    User sampleUser = new User();
    sampleUser.setId(1);
    sampleUser.setFirstName("John");
    sampleUser.setLastName("Doe");
    sampleUser.setPassword("password123");
    sampleUser.setCreated(LocalDateTime.now());
    sampleUser.setEmail("john@example.com");
    // Create a sample advertisement to use in the tests
    AD sampleAdvertisement = new AD();
    sampleAdvertisement.setId(1);
    sampleAdvertisement.setDescription("Test Ad Description");
    sampleAdvertisement.setType( Type.OFFER);
    sampleAdvertisement.setPrice(100);
    sampleAdvertisement.setCategory(sampleCategory);
    sampleAdvertisement.setUser(sampleUser);
    sampleAdvertisement.setCreated(LocalDateTime.now());
    sampleAdvertisement.setTitle("Test Ad");
    // Create a sample notepad to use in the tests
    sampleNotepad = new Notepad();
    sampleNotepad.setId(1);
    sampleNotepad.setUser(sampleUser);
    sampleNotepad.setNote("Test Note");
    sampleNotepad.setAd(sampleAdvertisement);
    sampleNotepad.setCreated(LocalDateTime.now());
  }

  @Test
  void testFindByUser_idAndByAd_id() {
    // Set up the mock to return a sample notepad when findByUser_idAndByAd_id is called
    when(mockRepo.findByUser_idAndByAd_id(1, 1)).thenReturn(sampleNotepad);

    // Call the findByUser_idAndByAd_id method on the NotepadService and store the result
    Notepad result = notepadService.findByUser_idAndByAd_id(1, 1);

    // Make assertions about the returned notepad
    assertNotNull(result);
    assertEquals(1, result.getUser().getId());
    assertEquals(1, result.getAd().getId());
  }

  @Test
  void testSaveNotepad() {
    notepadService.saveNotepad(sampleNotepad);
    // Verify that the save method on the mock Repository was called
    verify(mockRepo, times(1)).save(sampleNotepad);
  }


  @Test
  void testFindNotepadByUser_id() {
    // Set up the mock to return a list of notepads when findByUser_id is called
    when(mockRepo.findByUser_id(1)).thenReturn(Arrays.asList(sampleNotepad));

    // Call the findNotepadByUser_id method on the NotepadService and store the result
    List<Notepad> result = notepadService.findNotepadByUser_id(1);

    // Make assertions about the returned list
    assertNotNull(result);
    assertEquals(1, result.size());
    assertEquals(1, result.get(0).getUser().getId());
    assertEquals(1, result.get(0).getAd().getId());
  }

  @Test
  void testDeleteNotepad() {
    // Set up the mock to capture the notepad passed to the delete method
    doAnswer(invocation -> {
      Notepad notepad = invocation.getArgument(0);
      deletedNotepad = notepad;
      return null;
    }).when(mockRepo).delete(any(Notepad.class));

    // Call the deleteNotepad method on the NotepadService
    notepadService.deleteNotepad(sampleNotepad);

    // Verify that the delete method was called on the mock repository
    verify(mockRepo).delete(sampleNotepad);

    // Make assertions about the deleted notepad
    assertNotNull(deletedNotepad);
    assertEquals(1, deletedNotepad.getUser().getId());
    assertEquals(1, deletedNotepad.getAd().getId());
  }

}