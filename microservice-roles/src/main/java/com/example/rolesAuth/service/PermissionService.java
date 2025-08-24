package com.example.rolesAuth.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.rolesAuth.entity.dto.PermissionDTO;
import com.example.rolesAuth.repository.PermissionRepository;


@Service
public class PermissionService {

    @Autowired
    PermissionRepository permissionRepository;

    public List<PermissionDTO> getAllPermission() {
        return permissionRepository.findAll().stream()
                .map(permission -> {
                    return PermissionDTO.builder()
                            .id(permission.getId())
                            .name(permission.getName())
                            .build();
                })
                .toList();
    }
}
