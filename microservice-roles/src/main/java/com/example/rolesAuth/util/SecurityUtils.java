package com.example.rolesAuth.util;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.example.rolesAuth.entity.dto.RoleDTO;

public class SecurityUtils {

    private static final String PREFIX_ROLE = "ROLE_";

    public static SimpleGrantedAuthority convertToAuthority(RoleDTO roleEntity) {
        return (roleEntity.toString().startsWith(PREFIX_ROLE))
                ? new SimpleGrantedAuthority(roleEntity.getName())
                : new SimpleGrantedAuthority(PREFIX_ROLE + roleEntity.getName());
    }
}
