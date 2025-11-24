package org.example.copycom.mapper;

import org.example.copycom.dto.CategoriaDto;
import org.example.copycom.entity.CategoriaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CategoriaMapper {
    CategoriaMapper INSTANCE = Mappers.getMapper(CategoriaMapper.class);

    CategoriaDto categoriaEntityToCategoriaDto(CategoriaEntity categoriaEntity);

    CategoriaEntity categoriaDtoToCategoria(CategoriaDto categoriaDto);
}
