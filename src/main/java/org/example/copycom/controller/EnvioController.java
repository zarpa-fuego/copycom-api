package org.example.copycom.controller;

import org.example.copycom.dto.EnvioDto;
import org.example.copycom.service.EnvioService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/envios")
public class EnvioController {

    private final EnvioService envioService;

    public EnvioController(EnvioService envioService) {
        this.envioService = envioService;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('CAJERO', 'ADMIN')")
    public List<EnvioDto> findAll() {
        return envioService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('CAJERO', 'ADMIN')")
    public EnvioDto findById(@PathVariable Long id) {
        return envioService.findById(id);
    }

    @GetMapping("/guia/{numeroGuia}")
    public EnvioDto findByNumeroGuia(@PathVariable String numeroGuia) {
        return envioService.findByNumeroGuia(numeroGuia);
    }

    @GetMapping("/pedido/{pedidoId}")
    public EnvioDto findByPedidoId(@PathVariable Long pedidoId) {
        return envioService.findByPedidoId(pedidoId);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('CAJERO', 'ADMIN')")
    public EnvioDto save(@RequestBody EnvioDto envioDto) {
        return envioService.save(envioDto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('CAJERO', 'ADMIN')")
    public EnvioDto update(@PathVariable Long id, @RequestBody EnvioDto envioDto) {
        return envioService.update(id, envioDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable Long id) {
        envioService.delete(id);
    }
}

