package org.example.copycom.service;

import org.example.copycom.dto.EnvioDto;

import java.util.List;

public interface EnvioService {
    List<EnvioDto> findAll();
    EnvioDto findById(Long id);
    EnvioDto findByNumeroGuia(String numeroGuia);
    EnvioDto findByPedidoId(Long pedidoId);
    EnvioDto save(EnvioDto envioDto);
    EnvioDto update(Long id, EnvioDto envioDto);
    void delete(Long id);
}

