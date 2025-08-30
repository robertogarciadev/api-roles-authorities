package com.example.rolesAuth.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.rolesAuth.entity.PermissionEntity;
import com.example.rolesAuth.entity.RoleEntity;
import com.example.rolesAuth.entity.UserEntity;
import com.example.rolesAuth.entity.dto.RoleDTO;
import com.example.rolesAuth.entity.dto.UserDTO;
import com.example.rolesAuth.entity.dto.PermissionDTO;
import com.example.rolesAuth.repository.RoleRepository;
import com.example.rolesAuth.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(user -> {
                    //Convert the permissiion to DTO
                    Set<PermissionEntity> listPermissionEntity = user.getRole().getListPermission();
                    Set<PermissionDTO> listPermissionDTO = permissionEntityToDTO(listPermissionEntity);

                    //Convert the role to DTO
                    RoleDTO roleDTO = roleEntityToDTO(user.getRole(), listPermissionDTO);

                    return UserDTO.builder()
                            .id(user.getId())
                            .mail(user.getMail())
                            .name(user.getName())
                            .lastName(user.getLastName())
                            .role(roleDTO)
                            .build();
                })
                .toList();
    }

    @Override
    public Optional<UserDTO> save(UserDTO dto) {
        return roleRepository.findById(dto.getRole().getId())
                .map(role -> {
                    UserEntity userSaved = userRepository.save(UserEntity.builder()
                            .mail(dto.getMail())
                            .name(dto.getName())
                            .password(dto.getPassword())
                            .lastName(dto.getLastName())
                            .role(role)
                            .build());

                    // Convert the permission to DTO
                    Set<PermissionDTO> permissionList = permissionEntityToDTO(userSaved.getRole().getListPermission());

                    // Convert role to DTO
                    RoleDTO roleDTO = roleEntityToDTO(userSaved.getRole(), permissionList);

                    // Return DTO of the entity saved
                    return userEntityToDTO(userSaved, roleDTO);
                });
    }
    
    @Override
    public Optional<UserDTO> findByMail(String mail){
        return userRepository.findByMail(mail)
        .map(user->{
            Set<PermissionDTO> permission = permissionEntityToDTO(user.getRole().getListPermission());
            RoleDTO roleDTO = roleEntityToDTO(user.getRole(), permission);
            return userEntityToDTO(user, roleDTO);
        });
    }



    private UserDTO userEntityToDTO(UserEntity entity, RoleDTO roleDTO) {
        return UserDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .lastName(entity.getLastName())
                .role(roleDTO)
                .build();
    }
    private RoleDTO roleEntityToDTO(RoleEntity entity, Set<PermissionDTO> listPermissions) {
        return RoleDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .listPermission(listPermissions)
                .build();
    }
    private Set<PermissionDTO> permissionEntityToDTO(Set<PermissionEntity> listEntity) {
        return listEntity.stream()
                .map(entity -> {
                    return PermissionDTO.builder()
                            .id(entity.getId())
                            .name(entity.getName()).build();
                })
                .collect(Collectors.toSet());
    }
}
