package br.com.daniel.ordermanagement.dto;

import lombok.Data;

@Data
public class LoginCredentials {
  private String email;
  private String password;
}
