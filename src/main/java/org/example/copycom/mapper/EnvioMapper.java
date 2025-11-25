package org.example.copycom.mapper;

import org.example.copycom.dto.EnvioDto;
import org.example.copycom.entity.EnvioEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EnvioMapper {
    EnvioMapper INSTANCE = Mappers.getMapper(EnvioMapper.class);

    @Mapping(source = "pedido.id", target = "idPedido")
    EnvioDto envioEntityToEnvioDto(EnvioEntity envioEntity);

    @Mapping(target = "pedido", ignore = true)
    EnvioEntity envioDtoToEnvioEntity(EnvioDto envioDto);
}

