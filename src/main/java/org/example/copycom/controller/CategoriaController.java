package org.example.copycom.controller;

import org.example.copycom.dto.CategoriaDto;
import org.example.copycom.service.CategoriaService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('CAJERO', 'ADMIN')")
    public List<CategoriaDto> findAll() {
        return categoriaService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('CAJERO', 'ADMIN')")
    public CategoriaDto findById(@PathVariable Long id) {
        return categoriaService.findById(id);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public CategoriaDto save(@RequestBody CategoriaDto categoriaDto) {
        return categoriaService.save(categoriaDto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public CategoriaDto update(@PathVariable Long id, @RequestBody CategoriaDto categoriaDto) { // 👈 Cambiar nombre y @PathVariable
        return categoriaService.update(id, categoriaDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole( 'ADMIN')")
    public void delete(@PathVariable  Long id) {
        categoriaService.delete(id);
    }

}
