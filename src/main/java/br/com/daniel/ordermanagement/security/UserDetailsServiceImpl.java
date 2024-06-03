package br.com.daniel.ordermanagement.security;

import br.com.daniel.ordermanagement.entity.User;
import br.com.daniel.ordermanagement.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Optional;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> userRes = userRepo.findByUserEmail(email);
        if(userRes.isEmpty())
            throw new UsernameNotFoundException("Não foi possível encontrar usuário com o email = " + email);

        User user = userRes.get();
        return new org.springframework.security.core.userdetails.User(
                email,
                user.getUserPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))); // Define, de forma estatica, o perfil do usuario encontrado
    }
}
