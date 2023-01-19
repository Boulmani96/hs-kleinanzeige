package de.hs.da.hskleinanzeigen.controller;

import de.hs.da.hskleinanzeigen.domain.AD;
import de.hs.da.hskleinanzeigen.domain.Notepad;
import de.hs.da.hskleinanzeigen.domain.User;
import de.hs.da.hskleinanzeigen.dtos.CreationNotepadDTO;
import de.hs.da.hskleinanzeigen.dtos.GetNotepadDTO;
import de.hs.da.hskleinanzeigen.dtos.NotepadDTO;
import de.hs.da.hskleinanzeigen.mappers.NotepadMapper;
import de.hs.da.hskleinanzeigen.services.NotepadService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class NotepadController {
    @Autowired
    private NotepadService notepadService;

    @Autowired
    private NotepadMapper notepadMapper = Mappers.getMapper(NotepadMapper .class);

    @Autowired
    private UserController userController;

    @Autowired
    private AdvertisementController advertisementController;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Notepad Created", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = NotepadDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid parameters", content = @Content)
    })
    @PutMapping("/api/users/{userId}/notepad")
    public ResponseEntity<NotepadDTO> putNotepad(@RequestBody CreationNotepadDTO creationNotepadDTO, @PathVariable Integer userId) {
        if(creationNotepadDTO.getAdvertisementId() == null || userId == null || userId < 0 ||creationNotepadDTO.getNote() == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        //if User or Advertisement does not exists
        if(userController.getUserByID(userId) == null
                || advertisementController.getAdvertisementById(creationNotepadDTO.getAdvertisementId()) == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        User user = notepadMapper.userDTOtoUser(userController.getUserByID(userId).getBody());
        AD ad = notepadMapper.adDTOtoAd(advertisementController.getAdvertisementById(creationNotepadDTO.getAdvertisementId()).getBody());

        //Notepad already exists with the same user and advertisement => update
        Notepad existsNotepad = notepadService.findByUser_idAndByAd_id(userId, creationNotepadDTO.getAdvertisementId());
        if(existsNotepad != null){
            existsNotepad.setNote(creationNotepadDTO.getNote());
            existsNotepad.setCreated(LocalDateTime.now());
            notepadService.saveNotepad(existsNotepad);
            return new ResponseEntity<>(notepadMapper.notepadToNotepadDTO(existsNotepad), HttpStatus.OK);
        }
        //Notepad does not exists => add new Notepad
        Notepad notepad = notepadMapper.creationNotepadDTOtoNotepad(creationNotepadDTO);
        notepad.setUser(user);
        notepad.setAd(ad);
        notepad.setCreated(LocalDateTime.now());
        notepadService.saveNotepad(notepad);
        return new ResponseEntity<>(notepadMapper.notepadToNotepadDTO(notepad), HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found Notepad", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = GetNotepadDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "No User was found with the given userID", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid parameters", content = @Content),
            @ApiResponse(responseCode = "204", description = "No Notepad was found", content = @Content)
    })
    @GetMapping("/api/users/{userId}/notepad")
    public ResponseEntity<List<GetNotepadDTO>> getNotepad(@PathVariable Integer userId) {
        if (userId == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        // check if user exists or not
        if(userController.getUserByID(userId) == null || userController.getUserByID(userId).getBody() == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<Notepad> notepadList = notepadService.findNotepadByUser_id(userId);

        // if no content
        if (notepadList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        // mapper Notepad to GetNotepadDTO
        List<GetNotepadDTO> getNotepadDTOList = notepadMapper.notepadListToGetNotepadDTOList(notepadList);
        return new ResponseEntity<>(getNotepadDTOList, HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Notepad deleted", content = @Content),
            @ApiResponse(responseCode = "404", description = "No User was found with the given userID", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid parameters", content = @Content)
    })
    @DeleteMapping("/api/users/{userId}/notepad/{adId}")
    public ResponseEntity deleteNotepad(@PathVariable Integer userId, @PathVariable Integer adId){
        if(userId == null || adId == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Notepad notepad = notepadService.findByUser_idAndByAd_id(userId, adId);
        if (notepad == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        notepadService.deleteNotepad(notepad);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}