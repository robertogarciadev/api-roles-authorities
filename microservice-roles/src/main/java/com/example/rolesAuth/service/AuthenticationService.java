package com.example.rolesAuth.service;

import com.example.rolesAuth.entity.dto.UserDTO;

import java.util.Optional;

public interface AuthenticationService {
    Optional<UserDTO> singInAndReturnJWT(UserDTO dto);
}
