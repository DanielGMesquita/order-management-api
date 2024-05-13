package br.com.daniel.ordermanagement.controller;

import br.com.daniel.ordermanagement.entity.User;
import br.com.daniel.ordermanagement.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

  private UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/info")
  public User getUserDetails() {
    // Recuperando o e-mail a partir do contexto de segurança
    String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    // Devolvendo os dados do usuario a partir do e-mail informado
    return userService.findByEmail(email);
  }
}
