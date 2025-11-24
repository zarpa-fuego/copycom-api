package org.example.copycom.dto;

import lombok.Data;
import java.util.Set;

@Data
public class RegisterRequestDTO {
    private String username;
    private String email;
    private String password;
    private String nombre;
    private String apellido;
    private Set<String> roles;
}