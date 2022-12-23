package de.hs.da.hskleinanzeigen.controller;

import de.hs.da.hskleinanzeigen.domain.User;
import de.hs.da.hskleinanzeigen.dtos.CreationUserDTO;
import de.hs.da.hskleinanzeigen.dtos.UserDTO;
import de.hs.da.hskleinanzeigen.mappers.UserMapper;
import de.hs.da.hskleinanzeigen.mappers.UserMapperImpl;
import de.hs.da.hskleinanzeigen.services.UserService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    private UserService userService;


    @Autowired
    private UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User Created", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = UserDTO.class)) }),
            @ApiResponse(responseCode = "409", description = "User already exists", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid parameters", content = @Content)
    })
    @PostMapping(path="/api/users")
    public ResponseEntity<UserDTO> addNewUser(@Valid @RequestBody CreationUserDTO creationUserDTO) {
        if(creationUserDTO.getEmail() == null || creationUserDTO.getFirstName() == null|| creationUserDTO.getPassword() == null ||
                creationUserDTO.getLastName() == null || creationUserDTO.getPhone() == null|| creationUserDTO.getLocation() == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (userService.findByEmail(creationUserDTO.getEmail()) != null){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        userMapper = new UserMapperImpl();
        User user = userMapper.creationUserDTOToUser(creationUserDTO);
        user.setCreated(LocalDateTime.now());
        userService.saveUser(user);
        return new ResponseEntity<>(userMapper.userToUserDTO(user), HttpStatus.CREATED);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found User", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = UserDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "No user was found with the given ID", content = @Content)
    })
    @GetMapping("/api/users/{id}")
    public ResponseEntity<UserDTO> getUserByID(@PathVariable int id) throws Exception {
        User optionalUser = userService.findUserById(id);
        if(optionalUser== null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userMapper.userToUserDTO(optionalUser), HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found User", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = UserDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "No user was found with the given email", content = @Content)
    })
    @GetMapping("/api/users/email")
    public ResponseEntity<UserDTO> getUserByEmail(@RequestParam String email) {
        User user = userService.findByEmail(email);
        if (user == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(userMapper.userToUserDTO(user), HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found Users", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Page.class)) }),
            @ApiResponse(responseCode = "204", description = "No content with the given parameters", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid Parameters", content = @Content)
    })
    @GetMapping(path="/api/users")
    public ResponseEntity<Page>getUsers(@RequestParam int pageStart, @RequestParam int pageSize) {
        if(pageSize < 0 || pageStart < 0){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        PageRequest pr = PageRequest.of(pageStart, pageSize, Sort.by("created"));
        if (userService.findAll(pr).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        Page<UserDTO> page = new PageImpl<>(userMapper.listUserToListUserDTO(
            userService.findAll(pr).getContent()));
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
