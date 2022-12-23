package de.hs.da.hskleinanzeigen.controller;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import de.hs.da.hskleinanzeigen.dtos.AdDTO;
import de.hs.da.hskleinanzeigen.dtos.UserDTO;
import de.hs.da.hskleinanzeigen.mappers.NotepadMapper;
import de.hs.da.hskleinanzeigen.services.NotepadService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
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
    when(userController.getUserByID(anyInt())).thenReturn(new ResponseEntity<>(new UserDTO(1,"st@example.com","Test","User","123-456-7890","Testville"), HttpStatus.OK));
  //when(advertisementController.getAdvertisementById(anyInt())).thenReturn(new ResponseEntity<>(new AdDTO(), HttpStatus.OK));
  }

}