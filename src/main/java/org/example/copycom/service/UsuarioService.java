package org.example.copycom.service;

import org.example.copycom.dto.UsuarioDto;

import java.util.List;

public interface UsuarioService {
    List<UsuarioDto> findAll();

    UsuarioDto findById(Long id);

    UsuarioDto save(UsuarioDto usuarioDto);

    UsuarioDto update(Long id, UsuarioDto usuarioDto);

    void delete(Long id);
}
