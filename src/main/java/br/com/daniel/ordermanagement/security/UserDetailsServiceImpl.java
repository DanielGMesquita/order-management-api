package br.com.daniel.ordermanagement.security;

import br.com.daniel.ordermanagement.entity.User;
import br.com.daniel.ordermanagement.repository.UserRepository;
import java.util.Collections;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {
  @Autowired UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String email) {
    Optional<User> userResponse = userRepository.findByUserEmail(email);
    if (userResponse.isEmpty()) {
      throw new UsernameNotFoundException("User not found for email " + email);
    }

    User user = userResponse.get();
    return new org.springframework.security.core.userdetails.User(
        email,
        user.getUserPassword(),
        Collections.singletonList(
            new SimpleGrantedAuthority(
                "ROLE_USER"))); // Define, de forma estatica, o perfil do usuario encontrado
  }
}
