package org.example.copycom.mapper;

import org.example.copycom.dto.PedidoDto;
import org.example.copycom.entity.PedidoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PedidoMapper {
    PedidoMapper INSTANCE = Mappers.getMapper(PedidoMapper.class);

    PedidoDto pedidoEntityToPedidoDto(PedidoEntity pedidoEntity);

    PedidoEntity pedidoDtoToPedidoEntity(PedidoDto pedidoDto);
}

