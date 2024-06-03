package br.com.daniel.ordermanagement.controller;

import br.com.daniel.ordermanagement.dto.LoginCredentials;
import br.com.daniel.ordermanagement.entity.User;
import br.com.daniel.ordermanagement.security.JWTUtil;
import br.com.daniel.ordermanagement.service.UserService;
import java.util.Collections;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

  // Injecting Dependencies
  @Autowired private UserService userService;
  @Autowired private JWTUtil jwtUtil;
  @Autowired private AuthenticationManager authManager;
  @Autowired private PasswordEncoder passwordEncoder;

  // Registro de usuario
  @PostMapping("/register")
  public Map<String, Object> registerHandler(@RequestBody User user) {
    // Encriptando a senha usando o Bcrypt
    String encodedPass = passwordEncoder.encode(user.getUserPassword());
    user.setUserPassword(encodedPass);

    user = userService.saveUser(user);

    // Gerando o token JWT a partir do e-mail do Usuario
    // String token = jwtUtil.generateToken(user.getEmail());

    User usuarioResumido = new User();
    usuarioResumido.setUserName(user.getUserName());
    usuarioResumido.setUserEmail(user.getUserEmail());
    usuarioResumido.setUserId(user.getUserId());
    // Gerando o token JWT a partir dos dados do Usuario
    String token = jwtUtil.generateTokenWithUserData(usuarioResumido);

    // Retornando a resposta com o JWT
    return Collections.singletonMap("jwt-token", token);
  }

  // Login de usuario
  @PostMapping("/login")
  public Map<String, Object> loginHandler(@RequestBody LoginCredentials body) {
    try {
      // Criando o token que sera usado no processo de autenticacao
      UsernamePasswordAuthenticationToken authInputToken =
          new UsernamePasswordAuthenticationToken(body.getEmail(), body.getPassword());

      // Autenticando as credenciais de login
      authManager.authenticate(authInputToken);

      // Se o processo de autenticacao foi concluido com sucesso - etapa anterior,
      // eh gerado o JWT
      // String token = jwtUtil.generateToken(body.getEmail());

      User user = userService.findByEmail(body.getEmail());
      User usuarioResumido = new User();
      usuarioResumido.setUserName(user.getUserName());
      usuarioResumido.setUserEmail(user.getUserEmail());
      usuarioResumido.setUserId(user.getUserId());
      // Gerando o token JWT a partir dos dados do Usuario
      String token = jwtUtil.generateTokenWithUserData(usuarioResumido);

      // Responde com o JWT
      return Collections.singletonMap("jwt-token", token);
    } catch (AuthenticationException authExc) {
      throw new RuntimeException("Invalid Credentials");
    }
  }
}
