package com.example.rolesAuth.security.jwt;

import com.example.rolesAuth.security.CustomUserPrincipal;
import org.springframework.security.core.Authentication;

import jakarta.servlet.http.HttpServletRequest;

public interface JwtProvider {

    String generateToken(CustomUserPrincipal user);
    Authentication getAuthentication(HttpServletRequest request);
    boolean isTokenValid(HttpServletRequest request);
}
