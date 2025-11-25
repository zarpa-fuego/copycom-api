package org.example.copycom.service.impl;

import org.example.copycom.dto.EnvioDto;
import org.example.copycom.entity.EnvioEntity;
import org.example.copycom.entity.PedidoEntity;
import org.example.copycom.mapper.EnvioMapper;
import org.example.copycom.repository.EnvioRepository;
import org.example.copycom.repository.PedidoRepository;
import org.example.copycom.service.EnvioService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnvioServiceImpl implements EnvioService {

    private final EnvioRepository envioRepository;
    private final PedidoRepository pedidoRepository;
    private final EnvioMapper envioMapper;

    public EnvioServiceImpl(EnvioRepository envioRepository, 
                           PedidoRepository pedidoRepository,
                           EnvioMapper envioMapper) {
        this.envioRepository = envioRepository;
        this.pedidoRepository = pedidoRepository;
        this.envioMapper = envioMapper;
    }

    @Override
    public List<EnvioDto> findAll() {
        List<EnvioEntity> envios = envioRepository.findAll();
        return envios.stream()
                .map(envioMapper::envioEntityToEnvioDto)
                .collect(Collectors.toList());
    }

    @Override
    public EnvioDto findById(Long id) {
        EnvioEntity envioEntity = envioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Envío no encontrado con id: " + id));
        return envioMapper.envioEntityToEnvioDto(envioEntity);
    }

    @Override
    public EnvioDto findByNumeroGuia(String numeroGuia) {
        EnvioEntity envioEntity = envioRepository.findByNumeroGuia(numeroGuia)
                .orElseThrow(() -> new RuntimeException("Envío no encontrado con número de guía: " + numeroGuia));
        return envioMapper.envioEntityToEnvioDto(envioEntity);
    }

    @Override
    public EnvioDto findByPedidoId(Long pedidoId) {
        PedidoEntity pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado con id: " + pedidoId));
        
        EnvioEntity envioEntity = envioRepository.findByPedido(pedido)
                .orElseThrow(() -> new RuntimeException("No se encontró envío para el pedido: " + pedidoId));
        
        return envioMapper.envioEntityToEnvioDto(envioEntity);
    }

    @Override
    public EnvioDto save(EnvioDto envioDto) {
        if (envioDto.getNumeroGuia() != null && envioRepository.existsByNumeroGuia(envioDto.getNumeroGuia())) {
            throw new RuntimeException("Error: El número de guía ya está en uso!");
        }

        PedidoEntity pedido = pedidoRepository.findById(envioDto.getIdPedido())
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado con id: " + envioDto.getIdPedido()));

        if (envioRepository.findByPedido(pedido).isPresent()) {
            throw new RuntimeException("Error: Este pedido ya tiene un envío asociado!");
        }

        EnvioEntity envioEntity = envioMapper.envioDtoToEnvioEntity(envioDto);
        envioEntity.setPedido(pedido);
        
        EnvioEntity envioSaved = envioRepository.save(envioEntity);
        return envioMapper.envioEntityToEnvioDto(envioSaved);
    }

    @Override
    public EnvioDto update(Long id, EnvioDto envioDto) {
        EnvioEntity envioEntity = envioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Envío no encontrado con id: " + id));

        if (envioDto.getNumeroGuia() != null && 
            !envioDto.getNumeroGuia().equals(envioEntity.getNumeroGuia()) &&
            envioRepository.existsByNumeroGuia(envioDto.getNumeroGuia())) {
            throw new RuntimeException("Error: El número de guía ya está en uso!");
        }

        envioEntity.setDireccionOrigen(envioDto.getDireccionOrigen());
        envioEntity.setDireccionDestino(envioDto.getDireccionDestino());
        envioEntity.setTransportista(envioDto.getTransportista());
        envioEntity.setNumeroGuia(envioDto.getNumeroGuia());
        envioEntity.setEstadoEnvio(envioDto.getEstadoEnvio());
        envioEntity.setFechaEnvio(envioDto.getFechaEnvio());
        envioEntity.setFechaEstimadaEntrega(envioDto.getFechaEstimadaEntrega());
        envioEntity.setFechaRealEntrega(envioDto.getFechaRealEntrega());
        envioEntity.setObservaciones(envioDto.getObservaciones());

        EnvioEntity envioUpdated = envioRepository.save(envioEntity);
        return envioMapper.envioEntityToEnvioDto(envioUpdated);
    }

    @Override
    public void delete(Long id) {
        if (!envioRepository.existsById(id)) {
            throw new RuntimeException("Envío no encontrado con id: " + id);
        }
        envioRepository.deleteById(id);
    }
}

