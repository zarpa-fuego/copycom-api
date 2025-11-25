package org.example.copycom.service;

import org.example.copycom.dto.PedidoDto;

import java.util.List;

public interface PedidoService {
    List<PedidoDto> findAll();
    PedidoDto findById(Long id);
    PedidoDto findByNumeroPedido(String numeroPedido);
    PedidoDto save(PedidoDto pedidoDto);
    PedidoDto update(Long id, PedidoDto pedidoDto);
    void delete(Long id);
}

