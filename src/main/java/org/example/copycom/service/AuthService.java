package org.example.copycom.service;

import org.example.copycom.dto.JwtResponseDTO;
import org.example.copycom.dto.LoginRequestDTO;
import org.example.copycom.dto.RegisterRequestDTO;

public interface AuthService {
    JwtResponseDTO authenticateUser(LoginRequestDTO loginRequest);
    String registerUser(RegisterRequestDTO registerRequest);
}