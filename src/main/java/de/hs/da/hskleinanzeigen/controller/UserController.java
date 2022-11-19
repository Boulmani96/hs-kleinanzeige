package de.hs.da.hskleinanzeigen.controller;

import de.hs.da.hskleinanzeigen.domain.NotFoundException;
import de.hs.da.hskleinanzeigen.domain.User;
import de.hs.da.hskleinanzeigen.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping(path="/api/user") // Map ONLY POST Requests
    @ResponseBody
    public ResponseEntity<User> addNewUser(@RequestBody User user) {
        if(user.getEmail() == null || user.getPassword() == null || user.getFirst_name() == null|| user.getLast_name() == null || user.getPhone() == null|| user.getLocation() == null){
            return new ResponseEntity<>(user, HttpStatus.BAD_REQUEST);
        }

        /*if(category.getParent() != null ){
            //System.out.println(category.getParent().getName());
            if(getCategoryByID(category.getParent().getID()).isEmpty()) {
                throw new NotFoundException("Category with the id: " + getCategoryByID(category.getParent().getID()).get().getParent().getID() + " is not found");
            }
            category.setParent(getCategoryByID(category.getParent().getID()).get());
        }*/
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
    @GetMapping("/api/user/{id}")
    public Optional<User> getUSERByID(@PathVariable int id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()){
            throw new NotFoundException("Category with the id: "+id+" is not found");
        }
        return user;
    }

}
