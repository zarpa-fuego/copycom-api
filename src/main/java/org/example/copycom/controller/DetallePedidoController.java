package org.example.copycom.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/detalle-pedidos")
public class DetallePedidoController {

    // ✅ ADMIN puede ver todos los detalles
    @GetMapping("/admin/todos")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> listarTodosDetallesAdmin() {
        return ResponseEntity.ok("✅ [ADMIN] Todos los detalles de pedidos");
    }

    // ✅ CAJERO puede ver detalles de pedidos
    @GetMapping("/cajero/pedido/{idPedido}")
    @PreAuthorize("hasRole('CAJERO')")
    public ResponseEntity<String> verDetallePedidoCajero(@PathVariable Long idPedido) {
        return ResponseEntity.ok("✅ [CAJERO] Detalle del pedido " + idPedido);
    }

    // ✅ CAJERO puede agregar productos al pedido
    @PostMapping("/cajero/agregar")
    @PreAuthorize("hasRole('CAJERO')")
    public ResponseEntity<String> agregarDetalle() {
        return ResponseEntity.ok("✅ [CAJERO] Detalle agregado al pedido");
    }

    // ✅ CLIENTE puede ver detalles de SUS pedidos
    @GetMapping("/cliente/pedido/{idPedido}")
    @PreAuthorize("hasRole('CLIENTE')")
    public ResponseEntity<String> verDetallePedidoCliente(@PathVariable Long idPedido) {
        return ResponseEntity.ok("✅ [CLIENTE] Detalle de tu pedido " + idPedido);
    }
}