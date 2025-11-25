package org.example.copycom.service.impl;

import org.example.copycom.dto.JwtResponseDTO;
import org.example.copycom.dto.LoginRequestDTO;
import org.example.copycom.dto.UsuarioDto;
import org.example.copycom.entity.RolEntity;
import org.example.copycom.entity.UsuarioEntity;
import org.example.copycom.repository.RolRepository;
import org.example.copycom.repository.UsuarioRepository;
import org.example.copycom.security.jwt.JwtUtils;
import org.example.copycom.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public JwtResponseDTO authenticateUser(LoginRequestDTO loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UsuarioEntity userDetails = (UsuarioEntity) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .toList();

        return JwtResponseDTO.builder()
                .token(jwt)
                .type("Bearer")
                .id(userDetails.getId())
                .username(userDetails.getUsername())
                .email(userDetails.getEmail())
                .nombre(userDetails.getNombre())
                .apellido(userDetails.getApellido())
                .roles(roles)
                .build();
    }

    @Override
    public String registerUser(UsuarioDto registerRequest) {
        if (usuarioRepository.existsByUsername(registerRequest.getUsername())) {
            throw new RuntimeException("Error: El nombre de usuario ya está en uso!");
        }

        if (usuarioRepository.existsByEmail(registerRequest.getEmail())) {
            throw new RuntimeException("Error: El email ya está en uso!");
        }

        UsuarioEntity user = new UsuarioEntity();
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(encoder.encode(registerRequest.getPassword()));
        user.setNombre(registerRequest.getNombre());
        user.setApellido(registerRequest.getApellido());
        user.setActivo(true);

        Set<String> strRoles = registerRequest.getRoles();
        Set<RolEntity> roles = new HashSet<>();

        if (strRoles == null || strRoles.isEmpty()) {
            // Por defecto, asignar rol CLIENTE
            RolEntity clienteRole = rolRepository.findByNombre("ROLE_CLIENTE")
                    .orElseThrow(() -> new RuntimeException("Error: Rol CLIENTE no encontrado."));
            roles.add(clienteRole);
        } else {
            strRoles.forEach(role -> {
                switch (role.toLowerCase()) {
                    case "admin":
                        RolEntity adminRole = rolRepository.findByNombre("ROLE_ADMIN")
                                .orElseThrow(() -> new RuntimeException("Error: Rol ADMIN no encontrado."));
                        roles.add(adminRole);
                        break;
                    case "cajero":
                        RolEntity cajeroRole = rolRepository.findByNombre("ROLE_CAJERO")
                                .orElseThrow(() -> new RuntimeException("Error: Rol CAJERO no encontrado."));
                        roles.add(cajeroRole);
                        break;
                    case "cliente":
                        RolEntity clienteRole = rolRepository.findByNombre("ROLE_CLIENTE")
                                .orElseThrow(() -> new RuntimeException("Error: Rol CLIENTE no encontrado."));
                        roles.add(clienteRole);
                        break;
                    default:
                        RolEntity defaultRole = rolRepository.findByNombre("ROLE_CLIENTE")
                                .orElseThrow(() -> new RuntimeException("Error: Rol CLIENTE no encontrado."));
                        roles.add(defaultRole);
                }
            });
        }

        user.setRoles(roles);
        usuarioRepository.save(user);

        return "Usuario registrado exitosamente!";
    }
}