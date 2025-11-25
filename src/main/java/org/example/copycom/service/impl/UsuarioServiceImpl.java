package org.example.copycom.service.impl;

import org.example.copycom.dto.UsuarioDto;
import org.example.copycom.entity.RolEntity;
import org.example.copycom.entity.UsuarioEntity;
import org.example.copycom.mapper.UsuarioMapper;
import org.example.copycom.repository.RolRepository;
import org.example.copycom.repository.UsuarioRepository;
import org.example.copycom.service.UsuarioService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;
    private final PasswordEncoder encoder;  // ✅ Añadir
    private final RolRepository rolRepository;
    // NOTA: En una aplicación real, se inyectaría un PasswordEncoder y un RolRepository aquí.

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper, PasswordEncoder encoder, RolRepository rolRepository) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
        this.encoder = encoder;
        this.rolRepository = rolRepository;
    }


    // ----------------------------------------------------
    // Operaciones de Lectura (Read)
    // ----------------------------------------------------

    @Override
    public List<UsuarioDto> findAll() {
        List<UsuarioEntity> usuarios = usuarioRepository.findAll();
        return usuarios.stream()
                .map(usuarioMapper::usuarioEntityToUsuarioDto)
                .collect(Collectors.toList());
    }

    @Override
    public UsuarioDto findById(Long id) {
        UsuarioEntity usuarioEntity = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + id));
        return usuarioMapper.usuarioEntityToUsuarioDto(usuarioEntity);
    }

    // ----------------------------------------------------
    // Operación de Guardado (Create)
    // ----------------------------------------------------

    @Override
    public UsuarioDto save(UsuarioDto usuarioDto) {
        // NOTA: Idealmente, aquí se usaría el mapper para convertir,
        // y luego se codificaría la contraseña y se cargarían los roles desde DB.
        UsuarioEntity usuarioEntity = new UsuarioEntity();

        usuarioEntity.setUsername(usuarioDto.getUsername());
        usuarioEntity.setEmail(usuarioDto.getEmail());
        // Se debe usar un PasswordEncoder aquí para codificar el password antes de guardar:
        usuarioEntity.setPassword(encoder.encode(usuarioDto.getPassword()));
        usuarioEntity.setNombre(usuarioDto.getNombre());
        usuarioEntity.setApellido(usuarioDto.getApellido());
        usuarioEntity.setActivo(true);

        // La gestión de roles (Set<RolEntity>) debe hacerse buscando las entidades Rol
        Set<String> strRoles = usuarioDto.getRoles();
        Set<RolEntity> roles = new HashSet<>();

        if (strRoles == null || strRoles.isEmpty()) {
            RolEntity clienteRole = rolRepository.findByNombre("ROLE_CLIENTE")
                    .orElseThrow(() -> new RuntimeException("Error: Rol CLIENTE no encontrado."));
            roles.add(clienteRole);
        } else {
            strRoles.forEach(role -> {
                RolEntity rolEntity = rolRepository.findByNombre(role.startsWith("ROLE_") ? role : "ROLE_" + role.toUpperCase())
                        .orElseThrow(() -> new RuntimeException("Error: Rol " + role + " no encontrado."));
                roles.add(rolEntity);
            });
        }

        usuarioEntity.setRoles(roles);
        UsuarioEntity usuarioEntitySaved = usuarioRepository.save(usuarioEntity);
        return usuarioMapper.usuarioEntityToUsuarioDto(usuarioEntitySaved);
    }

    // ----------------------------------------------------
    // Operación de Actualización (Update)
    // ----------------------------------------------------

    @Override
    public UsuarioDto update(Long id, UsuarioDto usuarioDto) {
        UsuarioEntity usuarioEntity = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + id));

        usuarioEntity.setUsername(usuarioDto.getUsername());
        usuarioEntity.setEmail(usuarioDto.getEmail());

        // Solo actualizar password si viene en el DTO
        if (usuarioDto.getPassword() != null && !usuarioDto.getPassword().isEmpty()) {
            usuarioEntity.setPassword(encoder.encode(usuarioDto.getPassword()));
        }

        usuarioEntity.setNombre(usuarioDto.getNombre());
        usuarioEntity.setApellido(usuarioDto.getApellido());

        // Actualizar roles si vienen en el DTO
        if (usuarioDto.getRoles() != null && !usuarioDto.getRoles().isEmpty()) {
            Set<RolEntity> roles = new HashSet<>();
            usuarioDto.getRoles().forEach(role -> {
                RolEntity rolEntity = rolRepository.findByNombre(role.startsWith("ROLE_") ? role : "ROLE_" + role.toUpperCase())
                        .orElseThrow(() -> new RuntimeException("Error: Rol " + role + " no encontrado."));
                roles.add(rolEntity);
            });
            usuarioEntity.setRoles(roles);
        }

        UsuarioEntity usuarioEntityUpdated = usuarioRepository.save(usuarioEntity);
        return usuarioMapper.usuarioEntityToUsuarioDto(usuarioEntityUpdated);
    }

    // ----------------------------------------------------
    // Operación de Eliminación (Delete)
    // ----------------------------------------------------

    @Override
    public void delete(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new RuntimeException("Usuario no encontrado con id: " + id);
        }
        usuarioRepository.deleteById(id);
    }
}