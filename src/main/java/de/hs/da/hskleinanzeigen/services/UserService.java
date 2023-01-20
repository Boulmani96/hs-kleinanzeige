package de.hs.da.hskleinanzeigen.services;

import de.hs.da.hskleinanzeigen.domain.User;
import de.hs.da.hskleinanzeigen.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  public User findByEmail(String email){
    return userRepository.findByEmail(email);
  }

  @CachePut(value="users", key = "#user.id", unless="#result == null")
  public User saveUser(User user) {
    return userRepository.save(user);
  }

  @Cacheable(value = "Users", key = "#id")
  public Optional<User> findUserById(int id) {
    return userRepository.findById(id);
  }

  public Page<User> findAll(PageRequest pr) {
    return userRepository.findAll(pr);
  }
}