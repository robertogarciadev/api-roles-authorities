package com.example.rolesAuth.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.rolesAuth.entity.dto.PermissionDTO;
import com.example.rolesAuth.entity.dto.RoleDTO;
import com.example.rolesAuth.repository.RoleRepository;

@Service
public class RoleService {

    @Autowired
    RoleRepository roleRepository;

    public List<RoleDTO> getAllRoles() {
        return roleRepository.findAll().stream()
                .map(role -> {
                    //map list Permission by role to pass it on later to RoleDTO 
                    Set<PermissionDTO> listPermissionDTO = role.getListAuth().stream()
                            .map(permission -> {
                                return PermissionDTO.builder()
                                        .id(permission.getId())
                                        .name(permission.getName())
                                        .build();
                            }).collect(Collectors.toSet());
                    return RoleDTO.builder()
                            .id(role.getId())
                            .name(role.getName())
                            .listPermission(listPermissionDTO)
                            .build();
                })
                .toList();
    }
}
