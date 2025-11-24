package org.example.copycom.config;

import jakarta.annotation.PostConstruct;
import org.example.copycom.entity.RolEntity;
import org.example.copycom.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {

    @Autowired
    private RolRepository rolRepository;

    @PostConstruct
    public void init() {
        createRoleIfNotExists("ROLE_CLIENTE", "Cliente del sistema - puede ver sus pedidos");
        createRoleIfNotExists("ROLE_CAJERO", "Cajero - gestiona pedidos y comprobantes");
        createRoleIfNotExists("ROLE_ADMIN", "Administrador - acceso total al sistema");
    }

    private void createRoleIfNotExists(String nombre, String descripcion) {
        rolRepository.findByNombre(nombre).orElseGet(() -> {
            RolEntity rol = new RolEntity();
            rol.setNombre(nombre);
            rol.setDescripcion(descripcion);
            rolRepository.save(rol);
            System.out.println("✅ Rol creado: " + nombre);
            return rol;
        });
    }
}