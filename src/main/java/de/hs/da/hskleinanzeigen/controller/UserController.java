package de.hs.da.hskleinanzeigen.controller;

import de.hs.da.hskleinanzeigen.domain.User;
import de.hs.da.hskleinanzeigen.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping(path="/api/users")
    public ResponseEntity<User> addNewUser(@RequestBody User user) {
        if(user.getEmail() == null || user.getFirstName() == null||
                user.getLastName() == null || user.getPhone() == null|| user.getLocation() == null){
            return new ResponseEntity<>(user, HttpStatus.BAD_REQUEST);
        }
        if (userRepository.findByEmail(user.getEmail()) != null){
            return new ResponseEntity<>(user, HttpStatus.CONFLICT);
        }
        userRepository.save(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping("/api/users/{id}")
    public ResponseEntity<User> getUserByID(@PathVariable int id) {
        Optional<User> optionalUser = userRepository.findById(id);
        User user;
        if(optionalUser.isEmpty()){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        user = optionalUser.get();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/api/users/email")
    public ResponseEntity<User> getUserByEmail(@RequestParam String email) {
        return new ResponseEntity<>(userRepository.findByEmail(email), HttpStatus.OK);
    }

    @GetMapping(path="/api/users/all")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
