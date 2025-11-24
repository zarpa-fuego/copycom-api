package org.example.copycom.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/test")
public class TestController {

    // ✅ Endpoint PÚBLICO - Sin @PreAuthorize
    @GetMapping("/public")
    public ResponseEntity<String> publicEndpoint() {
        return ResponseEntity.ok("✅ Este es un endpoint PÚBLICO - No requiere token");
    }

    // ✅ Endpoint protegido - Con @PreAuthorize
    @GetMapping("/protected")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<String> protectedEndpoint() {
        return ResponseEntity.ok("✅ ¡Acceso autorizado! Tu token es válido.");
    }

    // ✅ Solo usuarios con rol USER
    @GetMapping("/user")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<String> userEndpoint() {
        return ResponseEntity.ok("✅ Acceso de USER autorizado");
    }

    // ✅ Solo usuarios con rol ADMIN
    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> adminEndpoint() {
        return ResponseEntity.ok("✅ Acceso de ADMIN autorizado");
    }

    // ✅ Solo usuarios con rol USER o ADMIN
    @GetMapping("/user-or-admin")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<String> userOrAdminEndpoint() {
        return ResponseEntity.ok("✅ Acceso de USER o ADMIN autorizado");
    }
}