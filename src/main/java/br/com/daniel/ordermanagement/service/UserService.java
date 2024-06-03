package br.com.daniel.ordermanagement.service;

import br.com.daniel.ordermanagement.entity.User;
import br.com.daniel.ordermanagement.repository.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
  @Autowired UserRepository userRepository;

  public List<User> getAll() {
    return userRepository.findAll();
  }

  public User getById(Integer id) {
    return userRepository.findById(id).orElse(null);
  }

  public User findByEmail(String email) {
    return userRepository.findByUserEmail(email).get();
  }

  public User saveUser(User user) {
    return userRepository.save(user);
  }

  public User updateUser(Integer id, User user) {
    User updatedUser = userRepository.findById(id).orElse(null);
    if (updatedUser != null) {
      updatedUser.setUserEmail(user.getUserEmail());
      updatedUser.setUserName(user.getUserName());
      return userRepository.save(updatedUser);
    } else {
      return null;
    }
  }

  public Boolean deleteUser(Integer id) {
    User user = userRepository.findById(id).orElse(null);
    if (user != null) {
      userRepository.delete(user);
      return true;
    } else {
      return false;
    }
  }
}
