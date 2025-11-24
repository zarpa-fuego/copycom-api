package org.example.copycom.service.impl;

import org.example.copycom.dto.CategoriaDto;
import org.example.copycom.entity.CategoriaEntity;
import org.example.copycom.mapper.CategoriaMapper;
import org.example.copycom.repository.CategoriaRepository;
import org.example.copycom.service.CategoriaService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final CategoriaMapper categoriaMapper;

    public CategoriaServiceImpl(CategoriaRepository categoriaRepository, CategoriaMapper categoriaMapper) {
        this.categoriaRepository = categoriaRepository;
        this.categoriaMapper = categoriaMapper;
    }

    @Override
    public List<CategoriaDto> findAll() {
        List<CategoriaEntity> categorias = categoriaRepository.findAll();
        return categorias.stream()
                .map(categoriaMapper::categoriaEntityToCategoriaDto)
                .collect(Collectors.toList());
    }

    @Override
    public CategoriaDto findById(Long id) {
        CategoriaEntity categoriaEntity = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada con id: " + id));
        return categoriaMapper.categoriaEntityToCategoriaDto(categoriaEntity);
    }

    @Override
    public CategoriaDto save(CategoriaDto categoriaDto) {
        CategoriaEntity categoriaEntity = new CategoriaEntity();
        categoriaEntity.setNombre(categoriaDto.getNombre());
        categoriaEntity.setDescripcion(categoriaDto.getDescripcion());
        CategoriaEntity categoriaEntitySaved = categoriaRepository.save(categoriaEntity);
        return categoriaMapper.categoriaEntityToCategoriaDto(categoriaEntitySaved);
    }

    @Override
    public CategoriaDto update(Long id, CategoriaDto categoriaDto) { // 👈 Implementar update
        CategoriaEntity categoriaEntity = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada con id: " + id));

        categoriaEntity.setNombre(categoriaDto.getNombre());
        categoriaEntity.setDescripcion(categoriaDto.getDescripcion());

        CategoriaEntity categoriaEntityUpdated = categoriaRepository.save(categoriaEntity);
        return categoriaMapper.categoriaEntityToCategoriaDto(categoriaEntityUpdated);
    }

    @Override
    public void delete(Long id) {
        if (!categoriaRepository.existsById(id)) {
            throw new RuntimeException("Categoría no encontrada con id: " + id);
        }
        categoriaRepository.deleteById(id);
    }
}