package de.hs.da.hskleinanzeigen.services;

import de.hs.da.hskleinanzeigen.domain.User;
import de.hs.da.hskleinanzeigen.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class UserService {
  @Autowired
  private UserRepository userRepository;
  
  public User findByEmail(String email){
    return userRepository.findByEmail(email);
  }


  public void saveUser(User user) {
    userRepository.save(user);
  }

  public User findUserById(int id) throws Exception{
   return userRepository.findById(id).orElseThrow(()->new Exception("No User Found"));
  }

  public Page<User> findAll(PageRequest pr) {
    return userRepository.findAll(pr);
  }
}
