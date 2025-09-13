package com.example.rolesAuth.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.rolesAuth.entity.dto.PermissionDTO;
import com.example.rolesAuth.entity.dto.RoleDTO;
import com.example.rolesAuth.repository.RoleRepository;

@Service
@Slf4j
public class RoleServiceImpl implements RoleService {


    @Autowired
    RoleRepository roleRepository;

    @Override
    public List<RoleDTO> getAllRoles() {
        log.info("INFO: Accediendo a RoleService a todos los roles");
        log.debug("DEBUG: Accediendo a Role Service a los roles");
        return roleRepository.findAll().stream()
                .map(role -> {
                    //map list Permission by role to pass it on later to RoleDTO 
                    Set<PermissionDTO> listPermissionDTO = role.getListPermission().stream()
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
