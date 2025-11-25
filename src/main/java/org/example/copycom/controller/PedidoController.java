package org.example.copycom.controller;

import org.example.copycom.dto.PedidoDto;
import org.example.copycom.service.PedidoService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    // CRUD Completo de Pedidos

    @GetMapping
    @PreAuthorize("hasAnyRole('CAJERO', 'ADMIN')")
    public List<PedidoDto> findAll() {
        return pedidoService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('CAJERO', 'ADMIN')")
    public PedidoDto findById(@PathVariable Long id) {
        return pedidoService.findById(id);
    }

    @GetMapping("/numero/{numeroPedido}")
    @PreAuthorize("hasAnyRole('CAJERO', 'ADMIN')")
    public PedidoDto findByNumeroPedido(@PathVariable String numeroPedido) {
        return pedidoService.findByNumeroPedido(numeroPedido);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('CAJERO', 'ADMIN')")
    public PedidoDto save(@RequestBody PedidoDto pedidoDto) {
        return pedidoService.save(pedidoDto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('CAJERO', 'ADMIN')")
    public PedidoDto update(@PathVariable Long id, @RequestBody PedidoDto pedidoDto) {
        return pedidoService.update(id, pedidoDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable Long id) {
        pedidoService.delete(id);
    }

    // Endpoints existentes (mantenidos para compatibilidad)

    @GetMapping("/admin/todos")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> listarTodosPedidosAdmin() {
        return ResponseEntity.ok("✅ [ADMIN] Lista de TODOS los pedidos del sistema");
    }

    @GetMapping("/cajero/todos")
    @PreAuthorize("hasRole('CAJERO')")
    public ResponseEntity<String> listarTodosPedidosCajero() {
        return ResponseEntity.ok("✅ [CAJERO] Lista de todos los pedidos");
    }

    @PostMapping("/cajero/crear")
    @PreAuthorize("hasRole('CAJERO')")
    public ResponseEntity<String> crearPedido() {
        return ResponseEntity.ok("✅ [CAJERO] Pedido creado exitosamente");
    }

    @PutMapping("/cajero/actualizar/{id}")
    @PreAuthorize("hasRole('CAJERO')")
    public ResponseEntity<String> actualizarPedido(@PathVariable Long id) {
        return ResponseEntity.ok("✅ [CAJERO] Pedido " + id + " actualizado");
    }

    @GetMapping("/cliente/mis-pedidos")
    @PreAuthorize("hasRole('CLIENTE')")
    public ResponseEntity<String> listarMisPedidos() {
        return ResponseEntity.ok("✅ [CLIENTE] Tus pedidos personales");
    }

    @DeleteMapping("/admin/eliminar/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> eliminarPedido(@PathVariable Long id) {
        return ResponseEntity.ok("✅ [ADMIN] Pedido " + id + " eliminado");
    }
}