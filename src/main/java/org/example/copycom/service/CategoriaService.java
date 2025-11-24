package org.example.copycom.service;

import org.example.copycom.dto.CategoriaDto;

import java.util.List;

public interface CategoriaService {
    public List<CategoriaDto> findAll();

    public CategoriaDto findById(Long id);

    public CategoriaDto save(CategoriaDto categoriaDto);
    public void delete(Long id);
    CategoriaDto update(Long id, CategoriaDto categoriaDto);
}
