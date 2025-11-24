package org.example.copycom.mapper;

import org.example.copycom.dto.PedidoConsultaDto;
import org.example.copycom.entity.PedidoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PedidoConsultaMapper {
    PedidoConsultaMapper INSTANCE = Mappers.getMapper(PedidoConsultaMapper.class);

    PedidoConsultaDto pedidoEntityToPedidoDto(PedidoEntity pedidoEntity);

    PedidoEntity pedidoConsultaDtoToPedidoEntity(PedidoConsultaDto pedidoDto);

}
