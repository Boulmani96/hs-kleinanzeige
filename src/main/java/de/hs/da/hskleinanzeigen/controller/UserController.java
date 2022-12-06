package de.hs.da.hskleinanzeigen.controller;

import de.hs.da.hskleinanzeigen.DTOs.CreationUserDTO;
import de.hs.da.hskleinanzeigen.DTOs.UserDTO;
import de.hs.da.hskleinanzeigen.domain.User;
import de.hs.da.hskleinanzeigen.mappers.UserMapper;
import de.hs.da.hskleinanzeigen.repository.UserRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    @PostMapping(path="/api/users")
    public ResponseEntity<UserDTO> addNewUser(@RequestBody CreationUserDTO creationUserDTO) {
        if(creationUserDTO.getEmail() == null || creationUserDTO.getFirstName() == null|| creationUserDTO.getPassword() == null ||
                creationUserDTO.getLastName() == null || creationUserDTO.getPhone() == null|| creationUserDTO.getLocation() == null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        if (userRepository.findByEmail(creationUserDTO.getEmail()) != null){
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }
        User user = userMapper.creationUserDTOToUser(creationUserDTO);
        userRepository.save(user);
        return new ResponseEntity<>(userMapper.userToUserDTO(user), HttpStatus.CREATED);
    }

    @GetMapping("/api/users/{id}")
    public ResponseEntity<UserDTO> getUserByID(@PathVariable int id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isEmpty()){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userMapper.userToUserDTO(optionalUser.get()), HttpStatus.OK);
    }

    @GetMapping("/api/users/email")
    public ResponseEntity<UserDTO> getUserByEmail(@RequestParam String email) {
        return new ResponseEntity<>(userMapper.userToUserDTO(userRepository.findByEmail(email)), HttpStatus.OK);
    }

    @GetMapping(path="/api/users")
    public ResponseEntity<Page>getUsers(@RequestParam int pageStart, @RequestParam int pageSize) {
        if(pageSize < 0 || pageStart < 0){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        PageRequest pr = PageRequest.of(pageStart, pageSize, Sort.by("created"));
        if (userRepository.findAll(pr).isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        Page<UserDTO> page = new PageImpl<>(userMapper.listUserToListUserDTO(userRepository.findAll(pr).getContent()));
        return new ResponseEntity<>(page, HttpStatus.OK);
    }
}
