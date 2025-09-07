package com.example.rolesAuth.service;

import com.example.rolesAuth.entity.dto.UserJWTDTO;
import com.example.rolesAuth.entity.dto.UserLoginDTO;

import java.util.Optional;

public interface AuthenticationService {
    Optional<UserJWTDTO> singInAndReturnJWT(UserLoginDTO dto);
}
