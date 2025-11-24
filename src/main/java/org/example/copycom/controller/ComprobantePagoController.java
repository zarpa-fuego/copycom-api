package org.example.copycom.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comprobantes")
public class ComprobantePagoController {

    // ✅ ADMIN puede ver todos los comprobantes
    @GetMapping("/admin/todos")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> listarTodosComprobantesAdmin() {
        return ResponseEntity.ok("✅ [ADMIN] Todos los comprobantes de pago");
    }

    // ✅ CAJERO puede generar comprobantes
    @PostMapping("/cajero/generar")
    @PreAuthorize("hasRole('CAJERO')")
    public ResponseEntity<String> generarComprobante() {
        return ResponseEntity.ok("✅ [CAJERO] Comprobante generado exitosamente");
    }

    // ✅ CAJERO puede ver comprobantes
    @GetMapping("/cajero/pedido/{idPedido}")
    @PreAuthorize("hasRole('CAJERO')")
    public ResponseEntity<String> verComprobantePedido(@PathVariable Long idPedido) {
        return ResponseEntity.ok("✅ [CAJERO] Comprobante del pedido " + idPedido);
    }

    // ✅ CLIENTE puede ver SUS comprobantes
    @GetMapping("/cliente/mis-comprobantes")
    @PreAuthorize("hasRole('CLIENTE')")
    public ResponseEntity<String> listarMisComprobantes() {
        return ResponseEntity.ok("✅ [CLIENTE] Tus comprobantes de pago");
    }

    // ✅ ADMIN puede anular comprobantes
    @PutMapping("/admin/anular/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> anularComprobante(@PathVariable Long id) {
        return ResponseEntity.ok("✅ [ADMIN] Comprobante " + id + " anulado");
    }
}