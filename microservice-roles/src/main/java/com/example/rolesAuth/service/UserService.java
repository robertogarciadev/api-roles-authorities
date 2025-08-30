package com.example.rolesAuth.service;

import java.util.List;
import java.util.Optional;

import com.example.rolesAuth.entity.dto.UserDTO;

public interface UserService {
    List<UserDTO> getAllUsers();
    Optional<UserDTO> save(UserDTO dto);
    Optional<UserDTO> findByMail(String mail);
}
