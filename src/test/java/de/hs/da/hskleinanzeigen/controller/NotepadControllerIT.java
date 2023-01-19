package de.hs.da.hskleinanzeigen.controller;

import de.hs.da.hskleinanzeigen.domain.Notepad;
import de.hs.da.hskleinanzeigen.dtos.*;
import de.hs.da.hskleinanzeigen.mappers.NotepadMapper;
import de.hs.da.hskleinanzeigen.services.NotepadService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class NotepadControllerIT {

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


  @Test
  public void testPutNotepad() {
    // Set up the request body
    CreationNotepadDTO creationNotepadDTO = new CreationNotepadDTO(1,"note");

    UserDTO mockUserDTO = mock(UserDTO.class);

    AdDTO mockAdDTO = mock(AdDTO.class);

    Notepad mockNotepad = mock(Notepad.class);

    when(userController.getUserByID(1)).thenReturn(ResponseEntity.ok(mockUserDTO));

    when(advertisementController.getAdvertisementById(creationNotepadDTO.getAdvertisementId())).thenReturn(ResponseEntity.ok(mockAdDTO));

    when(notepadService.findByUser_idAndByAd_id(1, creationNotepadDTO.getAdvertisementId())).thenReturn(mockNotepad);

    when(notepadService.saveNotepad(mockNotepad)).thenReturn(mockNotepad);

    // Call the putNotepad() method on the NotepadController instance
    ResponseEntity<NotepadDTO> response = notepadController.putNotepad(creationNotepadDTO, 1);

    // Verify that the returned value is as expected
    assertEquals(HttpStatus.OK, response.getStatusCode());
  }

  @Test
  public void testPutNotepadNotExist() {
    // Set up the request body
    CreationNotepadDTO creationNotepadDTO = new CreationNotepadDTO(1,"note");

    UserDTO mockUserDTO = mock(UserDTO.class);

    AdDTO mockAdDTO = mock(AdDTO.class);

    Notepad mockNotepad = mock(Notepad.class);

    when(userController.getUserByID(1)).thenReturn(ResponseEntity.ok(mockUserDTO));

    when(advertisementController.getAdvertisementById(creationNotepadDTO.getAdvertisementId())).thenReturn(ResponseEntity.ok(mockAdDTO));

    when(notepadMapper.creationNotepadDTOtoNotepad(creationNotepadDTO)).thenReturn(mockNotepad);

    when(notepadService.saveNotepad(mockNotepad)).thenReturn(mockNotepad);

    // Call the putNotepad() method on the NotepadController instance
    ResponseEntity<NotepadDTO> response = notepadController.putNotepad(creationNotepadDTO, 1);

    // Verify that the returned value is as expected
    assertEquals(HttpStatus.OK, response.getStatusCode());
  }

  @Test
  public void testPutNotepadWithNotFoundUser() {
    CreationNotepadDTO creationNotepadDTO = new CreationNotepadDTO(1,"note");

    Integer userId = 1;

    when(userController.getUserByID(userId)).thenReturn(null);

    ResponseEntity<NotepadDTO> response = notepadController.putNotepad(creationNotepadDTO, userId);

    // Verify that the returned value is as expected
    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
  }

  @Test
  public void testDeleteNotepad() {
    Notepad mockNotepad = mock(Notepad.class);

    when(notepadService.findByUser_idAndByAd_id(1, 1)).thenReturn(mockNotepad);

    doNothing().when(notepadService).deleteNotepad(mockNotepad);

    ResponseEntity<NotepadDTO> response = notepadController.deleteNotepad(1, 1);

    assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
  }

  @Test
  public void testDeleteNotepad_BAD_REQUEST() {
    ResponseEntity<NotepadDTO> response = notepadController.deleteNotepad(null,null);
    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
  }

  @Test
  public void testDeleteNotepad_NOT_FOUND() {
    ResponseEntity<NotepadDTO> response = notepadController.deleteNotepad(1,1);
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
  }

  @Test
  public void testPutNotepad_BAD_REQUEST() {
    CreationNotepadDTO creationNotepadDTO = new CreationNotepadDTO(1,"note");

    // Call the putNotepad() method on the NotepadController instance
    ResponseEntity<NotepadDTO> response = notepadController.putNotepad(creationNotepadDTO, null);

    // Verify that the returned value is as expected
    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
  }

  @Test
  public void testGetNotepad_NO_CONTENT() {
    UserDTO user = mock(UserDTO.class);

    when(userController.getUserByID(1)).thenReturn(ResponseEntity.ok(user));

    // Call the putNotepad() method on the NotepadController instance
    ResponseEntity<List<GetNotepadDTO>> response = notepadController.getNotepad(1);

    // Verify that the returned value is as expected
    assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
  }

  @Test
  public void testGetNotepadSuccess() {
    // Set up the request body
    UserDTO mockUserDTO = mock(UserDTO.class);

    Notepad mockNotepad = mock(Notepad.class);

    when(userController.getUserByID(1)).thenReturn(ResponseEntity.ok(mockUserDTO));

    List<Notepad> notepadList = new ArrayList<>();
    notepadList.add(mockNotepad);

    when(notepadService.findNotepadByUser_id(1)).thenReturn(notepadList);

    // Call the putNotepad() method on the NotepadController instance
    ResponseEntity<List<GetNotepadDTO>> response = notepadController.getNotepad(1);

    // Verify that the returned value is as expected
    assertEquals(HttpStatus.OK, response.getStatusCode());
  }

  @Test
  public void testGetNotepadWithNullUserID(){
    ResponseEntity<List<GetNotepadDTO>> response = notepadController.getNotepad(null);
    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
  }

  @Test
  public void testGetNotepadWithNotFoundUser(){
    ResponseEntity<List<GetNotepadDTO>> response = notepadController.getNotepad(1);
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
  }
}