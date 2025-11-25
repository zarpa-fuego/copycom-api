package org.example.copycom.mapper;


import org.example.copycom.dto.RolDto;
import org.example.copycom.entity.RolEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RolMapper {
    RolMapper INSTANCE = Mappers.getMapper(RolMapper.class);
    RolDto rolEntityToRolDto(RolEntity rolEntity);
    RolEntity rolDtoToRolEntity(RolDto rolDto);
}
