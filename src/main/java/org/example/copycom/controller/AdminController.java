package org.example.copycom.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    // ✅ Solo ADMIN - Ver todos los usuarios
    @GetMapping("/usuarios")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> listarUsuarios() {
        return ResponseEntity.ok("✅ [ADMIN] Lista de todos los usuarios");
    }

    // ✅ Solo ADMIN - Ver todos los clientes
    @GetMapping("/clientes")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> listarClientes() {
        return ResponseEntity.ok("✅ [ADMIN] Lista de todos los clientes");
    }

    // ✅ Solo ADMIN - Ver todos los trabajadores
    @GetMapping("/trabajadores")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> listarTrabajadores() {
        return ResponseEntity.ok("✅ [ADMIN] Lista de todos los trabajadores");
    }

    // ✅ Solo ADMIN - Ver todos los servicios
    @GetMapping("/servicios")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> listarServicios() {
        return ResponseEntity.ok("✅ [ADMIN] Lista de todos los servicios");
    }

    // ✅ Solo ADMIN - Ver todos los productos
    @GetMapping("/productos")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> listarProductos() {
        return ResponseEntity.ok("✅ [ADMIN] Lista de todos los productos");
    }

    // ✅ Solo ADMIN - Ver dashboard completo
    @GetMapping("/dashboard")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> verDashboard() {
        return ResponseEntity.ok("✅ [ADMIN] Dashboard completo del sistema");
    }

    // ✅ Solo ADMIN - Gestión de roles
    @PostMapping("/asignar-rol")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> asignarRol() {
        return ResponseEntity.ok("✅ [ADMIN] Rol asignado correctamente");
    }
}