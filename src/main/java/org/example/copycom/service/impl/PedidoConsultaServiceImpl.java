package org.example.copycom.service.impl;

import org.example.copycom.dto.PedidoConsultaDto;
import org.example.copycom.entity.PedidoEntity;
import org.example.copycom.mapper.PedidoConsultaMapper;
import org.example.copycom.repository.PedidoConsultaRepository;
import org.example.copycom.service.PedidoConsultaService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PedidoConsultaServiceImpl implements PedidoConsultaService {
    private PedidoConsultaRepository pedidoConsultaRepository;
    private PedidoConsultaMapper pedidoConsultaMapper;

    public PedidoConsultaServiceImpl(PedidoConsultaRepository pedidoConsultaRepository, PedidoConsultaMapper pedidoConsultaMapper) {
        this.pedidoConsultaRepository = pedidoConsultaRepository;
        this.pedidoConsultaMapper = pedidoConsultaMapper;
    }

    public PedidoConsultaDto getPedidoReportDtoBySerieNumero(String serieNumero) {
        PedidoEntity pedido = this.pedidoConsultaRepository.getPedidoEntityBySerieNumero(serieNumero);

        if (pedido == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido no encontrado");
        }

        return this.pedidoConsultaMapper.pedidoEntityToPedidoDto(pedido);
    }
}
