package org.example.copycom.mapper;

import org.example.copycom.dto.UsuarioDto;
import org.example.copycom.entity.RolEntity;
import org.example.copycom.entity.UsuarioEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
    UsuarioMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);

    @Mapping(source = "roles", target = "roles", qualifiedByName = "rolEntitiesToStrings")
    UsuarioDto usuarioEntityToUsuarioDto(UsuarioEntity usuarioEntity);

    @Mapping(source = "roles", target = "roles", qualifiedByName = "stringsToRolEntities")
    UsuarioEntity usuarioDtoToUsuarioEntity(UsuarioDto usuarioDto);

    @Named("rolEntitiesToStrings")
    default Set<String> rolEntitiesToStrings(Set<RolEntity> roles) {
        if (roles == null) {
            return null;
        }
        return roles.stream()
                .map(RolEntity::getNombre)
                .collect(Collectors.toSet());
    }

    @Named("stringsToRolEntities")
    default Set<RolEntity> stringsToRolEntities(Set<String> roleNames) {
        if (roleNames == null) {
            return null;
        }
        return roleNames.stream()
                .map(nombre -> {
                    RolEntity rol = new RolEntity();
                    rol.setNombre(nombre);
                    return rol;
                })
                .collect(Collectors.toSet());
    }
}