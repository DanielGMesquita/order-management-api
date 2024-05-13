package br.com.daniel.ordermanagement.service;

import br.com.daniel.ordermanagement.entity.User;
import br.com.daniel.ordermanagement.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
  @Autowired UserRepository userRepository;

  public User findByEmail(String email) {
    return userRepository
        .findByUserEmail(email)
        .orElseThrow(() -> new EntityNotFoundException("User not found for email: " + email));
  }

  public User save(User user) {
    return userRepository.save(user);
  }
}
