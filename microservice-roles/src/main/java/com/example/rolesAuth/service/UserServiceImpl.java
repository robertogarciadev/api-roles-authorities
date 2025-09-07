package com.example.rolesAuth.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.rolesAuth.entity.PermissionEntity;
import com.example.rolesAuth.entity.RoleEntity;
import com.example.rolesAuth.entity.UserEntity;
import com.example.rolesAuth.entity.dto.RoleDTO;
import com.example.rolesAuth.entity.dto.UserCreateDTO;
import com.example.rolesAuth.entity.dto.UserPrincipalDTO;
import com.example.rolesAuth.entity.dto.UserResponseDTO;
import com.example.rolesAuth.exception.EmailAlreadyExistsException;
import com.example.rolesAuth.entity.dto.PermissionDTO;
import com.example.rolesAuth.repository.RoleRepository;
import com.example.rolesAuth.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(user -> {
                    // Convert the permissiion to DTO
                    Set<PermissionEntity> listPermissionEntity = user.getRole().getListPermission();
                    Set<PermissionDTO> listPermissionDTO = permissionEntityToDTO(listPermissionEntity);

                    // Convert the role to DTO
                    RoleDTO roleDTO = roleEntityToDTO(user.getRole(), listPermissionDTO);
                    
                    // Convert and return to DTO
                    return userEntityToUserResponseDTO(user, roleDTO);
                })
                .toList();
    }

    @Override
    public Optional<UserResponseDTO> save(UserCreateDTO dto) {

        // Check mail
        if (userRepository.existsByMail(dto.getMail())) {
            throw new EmailAlreadyExistsException("Mail is already exist");
        }
        // Check role
        RoleEntity role = roleRepository.findById(dto.getRole().getId())
                .map(optRole -> {
                    return optRole;
                }).orElseThrow(() -> new EntityNotFoundException("Role don't exist"));

        // Saved new user
        UserEntity userSaved = userRepository.save(UserEntity.builder()
                .mail(dto.getMail())
                .name(dto.getName())
                .password(passwordEncoder.encode(dto.getPassword()))
                .lastName(dto.getLastName())
                .role(role)
                .build());

        // Convert permission to DTO
        Set<PermissionDTO> listPermissionDTOs = permissionEntityToDTO(role.getListPermission());
        
        // Conver role to DTO
        RoleDTO roleDTO = roleEntityToDTO(role, listPermissionDTOs);
        
        // Return UseDTO
        UserResponseDTO userResponseDTO = userEntityToUserResponseDTO(userSaved, roleDTO);
        return Optional.of(userResponseDTO);
    }

    @Override
    public Optional<UserPrincipalDTO> findByMail(String mail) {
        return userRepository.findByMail(mail)
                .map(user -> {
                    Set<PermissionDTO> permission = permissionEntityToDTO(user.getRole().getListPermission());
                    RoleDTO roleDTO = roleEntityToDTO(user.getRole(), permission);
                    return UserPrincipalDTO.builder()
                    .id(user.getId())
                    .mail(user.getMail())
                    .name(user.getName())
                    .lastName(user.getLastName())
                    .password(user.getPassword())
                    .role(roleDTO)
                    .build();
                });
    }

    @Override
    public boolean existsByMail(String mail) {
        return userRepository.existsByMail(mail);
    }

    private UserResponseDTO userEntityToUserResponseDTO(UserEntity entity, RoleDTO roleDTO) {
        return UserResponseDTO.builder()
                .id(entity.getId())
                .mail(entity.getMail())
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
