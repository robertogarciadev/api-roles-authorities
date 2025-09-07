package com.example.rolesAuth.service;


import java.util.List;
import java.util.Optional;

import com.example.rolesAuth.entity.dto.UserCreateDTO;
import com.example.rolesAuth.entity.dto.UserPrincipalDTO;
import com.example.rolesAuth.entity.dto.UserResponseDTO;

public interface UserService {
    List<UserResponseDTO> getAllUsers();
    Optional<UserResponseDTO> save(UserCreateDTO dto);
    Optional<UserPrincipalDTO> findByMail(String mail);
    boolean existsByMail(String mail);
}
