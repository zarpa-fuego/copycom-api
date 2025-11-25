package org.example.copycom.service;


import org.example.copycom.dto.RolDto;

import java.util.List;

public interface RolService {
    List<RolDto> findAll();

    RolDto finById(Long id);
}
