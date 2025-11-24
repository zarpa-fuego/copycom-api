package org.example.copycom.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    // ✅ ADMIN puede ver todos los pedidos
    @GetMapping("/admin/todos")
   // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> listarTodosPedidosAdmin() {
        return ResponseEntity.ok("✅ [ADMIN] Lista de TODOS los pedidos del sistema");
    }

    // ✅ CAJERO puede ver todos los pedidos
    @GetMapping("/cajero/todos")
    @PreAuthorize("hasRole('CAJERO')")
    public ResponseEntity<String> listarTodosPedidosCajero() {
        return ResponseEntity.ok("✅ [CAJERO] Lista de todos los pedidos");
    }

    // ✅ CAJERO puede crear pedidos
    @PostMapping("/cajero/crear")
    @PreAuthorize("hasRole('CAJERO')")
    public ResponseEntity<String> crearPedido() {
        return ResponseEntity.ok("✅ [CAJERO] Pedido creado exitosamente");
    }

    // ✅ CAJERO puede actualizar pedidos
    @PutMapping("/cajero/actualizar/{id}")
    @PreAuthorize("hasRole('CAJERO')")
    public ResponseEntity<String> actualizarPedido(@PathVariable Long id) {
        return ResponseEntity.ok("✅ [CAJERO] Pedido " + id + " actualizado");
    }

    // ✅ CLIENTE puede ver solo SUS pedidos
    @GetMapping("/cliente/mis-pedidos")
    @PreAuthorize("hasRole('CLIENTE')")
    public ResponseEntity<String> listarMisPedidos() {
        return ResponseEntity.ok("✅ [CLIENTE] Tus pedidos personales");
    }

    // ✅ ADMIN puede eliminar pedidos
    @DeleteMapping("/admin/eliminar/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> eliminarPedido(@PathVariable Long id) {
        return ResponseEntity.ok("✅ [ADMIN] Pedido " + id + " eliminado");
    }
}