package org.example.copycom.controller;

import org.example.copycom.dto.JwtResponseDTO;
import org.example.copycom.dto.LoginRequestDTO;
import org.example.copycom.dto.RegisterRequestDTO;
import org.example.copycom.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<JwtResponseDTO> login(@RequestBody LoginRequestDTO loginRequest) {
        JwtResponseDTO jwtResponse = authService.authenticateUser(loginRequest);
        return ResponseEntity.ok(jwtResponse);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequestDTO registerRequest) {
        String message = authService.registerUser(registerRequest);
        return ResponseEntity.ok(message);
    }
}