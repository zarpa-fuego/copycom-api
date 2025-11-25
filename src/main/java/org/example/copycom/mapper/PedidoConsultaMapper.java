package org.example.copycom.mapper;

import org.example.copycom.dto.PedidoConsultaDto;
import org.example.copycom.entity.PedidoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PedidoConsultaMapper {
    PedidoConsultaMapper INSTANCE = Mappers.getMapper(PedidoConsultaMapper.class);

    @Mapping(source = "numeroPedido", target = "serieNumero")
    @Mapping(target = "estadoProceso", ignore = true)
    PedidoConsultaDto pedidoEntityToPedidoDto(PedidoEntity pedidoEntity);

    @Mapping(source = "serieNumero", target = "numeroPedido")
    @Mapping(target = "estadoPedido", ignore = true)
    @Mapping(target = "prioridad", ignore = true)
    @Mapping(target = "subtotal", ignore = true)
    @Mapping(target = "descuento", ignore = true)
    @Mapping(target = "total", ignore = true)
    @Mapping(target = "idCliente", ignore = true)
    @Mapping(target = "fechaEntregaEstimada", ignore = true)
    @Mapping(target = "observaciones", ignore = true)
    @Mapping(target = "creadoEn", ignore = true)
    @Mapping(target = "actualizadoEn", ignore = true)
    PedidoEntity pedidoConsultaDtoToPedidoEntity(PedidoConsultaDto pedidoDto);

}
