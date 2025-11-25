package org.example.copycom.service.impl;

import org.example.copycom.dto.RolDto;
import org.example.copycom.entity.RolEntity;
import org.example.copycom.mapper.RolMapper;
import org.example.copycom.repository.RolRepository;
import org.example.copycom.service.RolService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RolServiceImpl implements RolService {
    private final RolRepository rolRepository;
    private final RolMapper  rolMapper;

    public RolServiceImpl(RolRepository rolRepository, RolMapper rolMapper) {
        this.rolRepository = rolRepository;
        this.rolMapper = rolMapper;
    }

    @Override
    public List<RolDto> findAll(){
        List<RolEntity> roles = rolRepository.findAll();
        return roles.stream()
                .map(rolMapper::rolEntityToRolDto)
                .collect(Collectors.toList());
    }

    @Override
    public RolDto finById(Long id){
        RolEntity rolEntity = rolRepository.findById(id).orElseThrow(() -> new RuntimeException("Rol no encontrado con id: " + id));
        return rolMapper.rolEntityToRolDto(rolEntity);
    }
}

