package org.example.copycom.service.impl;

import org.example.copycom.dto.PedidoDto;
import org.example.copycom.entity.PedidoEntity;
import org.example.copycom.mapper.PedidoMapper;
import org.example.copycom.repository.PedidoRepository;
import org.example.copycom.service.PedidoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository pedidoRepository;
    private final PedidoMapper pedidoMapper;

    public PedidoServiceImpl(PedidoRepository pedidoRepository, PedidoMapper pedidoMapper) {
        this.pedidoRepository = pedidoRepository;
        this.pedidoMapper = pedidoMapper;
    }

    @Override
    public List<PedidoDto> findAll() {
        List<PedidoEntity> pedidos = pedidoRepository.findAll();
        return pedidos.stream()
                .map(pedidoMapper::pedidoEntityToPedidoDto)
                .collect(Collectors.toList());
    }

    @Override
    public PedidoDto findById(Long id) {
        PedidoEntity pedidoEntity = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado con id: " + id));
        return pedidoMapper.pedidoEntityToPedidoDto(pedidoEntity);
    }

    @Override
    public PedidoDto findByNumeroPedido(String numeroPedido) {
        PedidoEntity pedidoEntity = pedidoRepository.findByNumeroPedido(numeroPedido)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado con número: " + numeroPedido));
        return pedidoMapper.pedidoEntityToPedidoDto(pedidoEntity);
    }

    @Override
    public PedidoDto save(PedidoDto pedidoDto) {
        if (pedidoDto.getNumeroPedido() != null && pedidoRepository.existsByNumeroPedido(pedidoDto.getNumeroPedido())) {
            throw new RuntimeException("Error: El número de pedido ya está en uso!");
        }

        PedidoEntity pedidoEntity = pedidoMapper.pedidoDtoToPedidoEntity(pedidoDto);
        PedidoEntity pedidoSaved = pedidoRepository.save(pedidoEntity);
        return pedidoMapper.pedidoEntityToPedidoDto(pedidoSaved);
    }

    @Override
    public PedidoDto update(Long id, PedidoDto pedidoDto) {
        PedidoEntity pedidoEntity = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado con id: " + id));

        if (pedidoDto.getNumeroPedido() != null && 
            !pedidoDto.getNumeroPedido().equals(pedidoEntity.getNumeroPedido()) &&
            pedidoRepository.existsByNumeroPedido(pedidoDto.getNumeroPedido())) {
            throw new RuntimeException("Error: El número de pedido ya está en uso!");
        }

        pedidoEntity.setNumeroPedido(pedidoDto.getNumeroPedido());
        pedidoEntity.setEstadoPedido(pedidoDto.getEstadoPedido());
        pedidoEntity.setPrioridad(pedidoDto.getPrioridad());
        pedidoEntity.setSubtotal(pedidoDto.getSubtotal());
        pedidoEntity.setDescuento(pedidoDto.getDescuento());
        pedidoEntity.setTotal(pedidoDto.getTotal());
        pedidoEntity.setIdCliente(pedidoDto.getIdCliente());
        pedidoEntity.setFechaPedido(pedidoDto.getFechaPedido());
        pedidoEntity.setFechaEntregaEstimada(pedidoDto.getFechaEntregaEstimada());
        pedidoEntity.setObservaciones(pedidoDto.getObservaciones());

        PedidoEntity pedidoUpdated = pedidoRepository.save(pedidoEntity);
        return pedidoMapper.pedidoEntityToPedidoDto(pedidoUpdated);
    }

    @Override
    public void delete(Long id) {
        if (!pedidoRepository.existsById(id)) {
            throw new RuntimeException("Pedido no encontrado con id: " + id);
        }
        pedidoRepository.deleteById(id);
    }
}

