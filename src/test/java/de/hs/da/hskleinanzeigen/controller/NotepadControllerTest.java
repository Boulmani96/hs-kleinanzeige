package de.hs.da.hskleinanzeigen.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import de.hs.da.hskleinanzeigen.domain.AD;
import de.hs.da.hskleinanzeigen.domain.Notepad;
import de.hs.da.hskleinanzeigen.domain.User;
import de.hs.da.hskleinanzeigen.dtos.AdDTO;
import de.hs.da.hskleinanzeigen.dtos.CreationNotepadDTO;
import de.hs.da.hskleinanzeigen.dtos.GetNotepadDTO;
import de.hs.da.hskleinanzeigen.dtos.NotepadDTO;
import de.hs.da.hskleinanzeigen.dtos.UserDTO;
import de.hs.da.hskleinanzeigen.mappers.NotepadMapper;
import de.hs.da.hskleinanzeigen.services.NotepadService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class NotepadControllerTest {

  @Mock
  private NotepadService notepadService;

  @Mock
  private NotepadMapper notepadMapper;

  @Mock
  private UserController userController;

  @Mock
  private AdvertisementController advertisementController;

  @InjectMocks
  private NotepadController notepadController;

  @BeforeEach
  public void setUp() throws Exception {
//    when(userController.getUserByID(anyInt())).thenReturn(new ResponseEntity<>(new UserDTO(1,"st@example.com","Test","User","123-456-7890","Testville"), HttpStatus.OK));
  //when(advertisementController.getAdvertisementById(anyInt())).thenReturn(new ResponseEntity<>(new AdDTO(), HttpStatus.OK));
  }
  @Test
  public void testPutNotepad() throws Exception {
    // Set up the request body
    CreationNotepadDTO creationNotepadDTO = new CreationNotepadDTO(1,"note");
    UserDTO mockUserDTO = mock(UserDTO.class);
    AdDTO mockAdDTO = mock(AdDTO.class);
    User mockUser = mock(User.class);
    Notepad mockNotepad = mock(Notepad.class);
    when(userController.getUserByID(1)).thenReturn(ResponseEntity.ok(mockUserDTO));
    when(advertisementController.getAdvertisementById(creationNotepadDTO.getAdvertisementId())).thenReturn(ResponseEntity.ok(mockAdDTO));
    when(notepadService.findByUser_idAndByAd_id(1, creationNotepadDTO.getAdvertisementId())).thenReturn(mockNotepad);
    doNothing().when(notepadService).saveNotepad(mockNotepad);


    // Call the putNotepad() method on the NotepadController instance
    ResponseEntity<NotepadDTO> response = notepadController.putNotepad(creationNotepadDTO, 1);

    // Verify that the returned value is as expected
    assertEquals(HttpStatus.OK, response.getStatusCode());
  }
  @Test
  public void testPutNotepadNotExist() throws Exception {
    // Set up the request body
    CreationNotepadDTO creationNotepadDTO = new CreationNotepadDTO(1,"note");
    UserDTO mockUserDTO = mock(UserDTO.class);
    AdDTO mockAdDTO = mock(AdDTO.class);
    User mockUser = mock(User.class);
    Notepad mockNotepad = mock(Notepad.class);
    when(userController.getUserByID(1)).thenReturn(ResponseEntity.ok(mockUserDTO));
    when(advertisementController.getAdvertisementById(creationNotepadDTO.getAdvertisementId())).thenReturn(ResponseEntity.ok(mockAdDTO));
    when(notepadMapper.creationNotepadDTOtoNotepad(creationNotepadDTO)).thenReturn(mockNotepad);
    doNothing().when(notepadService).saveNotepad(mockNotepad);


    // Call the putNotepad() method on the NotepadController instance
    ResponseEntity<NotepadDTO> response = notepadController.putNotepad(creationNotepadDTO, 1);

    // Verify that the returned value is as expected
    assertEquals(HttpStatus.OK, response.getStatusCode());
  }
  @Test
  public void testDeleteNotepad() throws Exception{
    Notepad mockNotepad = mock(Notepad.class);
    when(notepadService.findByUser_idAndByAd_id(1, 1)).thenReturn(mockNotepad);
    doNothing().when(notepadService).deleteNotepad(mockNotepad);

    ResponseEntity<NotepadDTO> response = notepadController.deleteNotepad(1, 1);
    assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

  }
  @Test
  public void testDeleteNotepad_BAD_REQUEST() throws Exception{
    ResponseEntity<NotepadDTO> response = notepadController.deleteNotepad(null,null );
    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

  }
  @Test
  public void testDeleteNotepad_NOT_FOUND() throws Exception{
    ResponseEntity<NotepadDTO> response = notepadController.deleteNotepad(1,1 );
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

  }
  @Test
  public void testPutNotepad_BAD_REQUEST() throws Exception {
    CreationNotepadDTO creationNotepadDTO = new CreationNotepadDTO(1,"note");

    // Call the putNotepad() method on the NotepadController instance
    ResponseEntity<NotepadDTO> response = notepadController.putNotepad(creationNotepadDTO, null);

    // Verify that the returned value is as expected
    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
  }
  @Test
  public void testGetNotepad() throws Exception {
    CreationNotepadDTO creationNotepadDTO = new CreationNotepadDTO(1,"note");
    Notepad notepad1 = mock(Notepad.class);
    Notepad notepad2 = mock(Notepad.class);
    UserDTO user= mock(UserDTO.class);
    List<Notepad> notepadList = new ArrayList<>();
    notepadList.add(notepad1);
    notepadList.add(notepad2);
//    when(notepadService.findByUser_idAndByAd_id(1, 1)).thenReturn(notepad1);
    when(userController.getUserByID(1)).thenReturn(ResponseEntity.ok(user));
    when(notepadService.findNotepadByUser_id(1)).thenReturn(notepadList);

    // Call the putNotepad() method on the NotepadController instance
    ResponseEntity<List<GetNotepadDTO>> response = notepadController.getNotepad(1);

    // Verify that the returned value is as expected
    assertEquals(HttpStatus.OK, response.getStatusCode());
  }
  @Test
  public void testGetNotepad_NO_CONTENT() throws Exception {

    UserDTO user= mock(UserDTO.class);
    when(userController.getUserByID(1)).thenReturn(ResponseEntity.ok(user));

    // Call the putNotepad() method on the NotepadController instance
    ResponseEntity<List<GetNotepadDTO>> response = notepadController.getNotepad(1);

    // Verify that the returned value is as expected
    assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
  }


}