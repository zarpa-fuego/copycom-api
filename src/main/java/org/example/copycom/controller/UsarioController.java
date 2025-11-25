package org.example.copycom.controller;

import org.example.copycom.dto.UsuarioDto;
import org.example.copycom.service.UsuarioService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsarioController {
    private final UsuarioService usuarioService;

    public UsarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public List<UsuarioDto> findAll() {
        return usuarioService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public UsuarioDto findById(@PathVariable Long id) {
        return usuarioService.findById(id);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public UsuarioDto save(@RequestBody UsuarioDto usuarioDto) {
        return usuarioService.save(usuarioDto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public UsuarioDto update(@PathVariable Long id, @RequestBody UsuarioDto usuarioDto) {
        return usuarioService.update(id, usuarioDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public void delete(@PathVariable Long id) {
        usuarioService.delete(id);
    }
}

