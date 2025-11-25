package org.example.copycom.config;

import jakarta.annotation.PostConstruct;
import org.example.copycom.entity.RolEntity;
import org.example.copycom.entity.UsuarioEntity;
import org.example.copycom.repository.RolRepository;
import org.example.copycom.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class DataInitializer {

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() {
        System.out.println("🌱 Iniciando seed de datos...");

        // ============================================
        // 1. CREAR ROLES
        // ============================================
        RolEntity rolAdmin = createRoleIfNotExists("ROLE_ADMIN", "Administrador - acceso total al sistema");
        RolEntity rolCajero = createRoleIfNotExists("ROLE_CAJERO", "Cajero - gestiona pedidos y comprobantes");

        System.out.println("✅ Roles verificados/creados");

        // ============================================
        // 2. CREAR USUARIOS
        // ============================================

        // Usuario 1: Solo ADMIN
        Set<RolEntity> rolesAdmin = new HashSet<>();
        rolesAdmin.add(rolAdmin);
        createUsuarioIfNotExists("admin", "admin@copycom.com", "Admin", "Sistema",
                "admin123", rolesAdmin);

        // Usuario 2: Solo CAJERO
        Set<RolEntity> rolesCajero = new HashSet<>();
        rolesCajero.add(rolCajero);
        createUsuarioIfNotExists("cajero", "cajero@copycom.com", "Cajero", "Ventas",
                "cajero123", rolesCajero);

        // Usuario 3: ADMIN + CAJERO
        Set<RolEntity> rolesAdminCajero = new HashSet<>();
        rolesAdminCajero.add(rolAdmin);
        rolesAdminCajero.add(rolCajero);
        createUsuarioIfNotExists("admin-cajero", "admin.cajero@copycom.com", "Admin", "Cajero",
                "admin123", rolesAdminCajero);

        System.out.println("✅ Usuarios verificados/creados");
        System.out.println("🎉 Seed completado exitosamente!");
        printCredentials();
    }

    private RolEntity createRoleIfNotExists(String nombre, String descripcion) {
        return rolRepository.findByNombre(nombre).orElseGet(() -> {
            RolEntity rol = new RolEntity();
            rol.setNombre(nombre);
            rol.setDescripcion(descripcion);
            rolRepository.save(rol);
            System.out.println("✅ Rol creado: " + nombre);
            return rol;
        });
    }

    private void createUsuarioIfNotExists(String username, String email, String nombre,
                                          String apellido, String password, Set<RolEntity> roles) {
        usuarioRepository.findByUsername(username).orElseGet(() -> {
            UsuarioEntity usuario = new UsuarioEntity();
            usuario.setUsername(username);
            usuario.setEmail(email);
            usuario.setNombre(nombre);
            usuario.setApellido(apellido);
            usuario.setPassword(passwordEncoder.encode(password));
            usuario.setActivo(true);
            usuario.setRoles(roles);
            usuarioRepository.save(usuario);

            String rolesStr = roles.stream()
                    .map(RolEntity::getNombre)
                    .reduce((a, b) -> a + ", " + b)
                    .orElse("");
            System.out.println("✅ Usuario creado: " + username + " - Roles: " + rolesStr);
            return usuario;
        });
    }

    private void printCredentials() {
        System.out.println("\n📋 CREDENCIALES DE ACCESO:");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("👨‍💼 USUARIO: admin");
        System.out.println("   • Email: admin@copycom.com");
        System.out.println("   • Password: admin123");
        System.out.println("   • Roles: ROLE_ADMIN");
        System.out.println("\n💰 USUARIO: cajero");
        System.out.println("   • Email: cajero@copycom.com");
        System.out.println("   • Password: cajero123");
        System.out.println("   • Roles: ROLE_CAJERO");
        System.out.println("\n👑 USUARIO: admin-cajero");
        System.out.println("   • Email: admin.cajero@copycom.com");
        System.out.println("   • Password: admin123");
        System.out.println("   • Roles: ROLE_ADMIN, ROLE_CAJERO");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");
    }
}