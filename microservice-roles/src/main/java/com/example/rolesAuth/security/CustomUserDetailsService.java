package com.example.rolesAuth.security;

import java.util.Optional;
import java.util.Set;

import javax.management.relation.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.rolesAuth.entity.dto.RoleDTO;
import com.example.rolesAuth.entity.dto.UserDTO;
import com.example.rolesAuth.service.UserService;
import com.example.rolesAuth.util.SecurityUtils;

public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {

        CustomUserPrincipal customUserPrincipal = userService.findByMail(mail)
                .map(userDTO -> {
                    // Convert Role of user to GrantedAuthority
                    RoleDTO roleDTO = userDTO.getRole();
                    Set<GrantedAuthority> authorities = Set.of(
                            SecurityUtils.convertToAuthority(roleDTO));

                    return CustomUserPrincipal.builder()
                            .id(userDTO.getId())
                            .mail(userDTO.getMail())
                            .name(userDTO.getName())
                            .lastName(userDTO.getLastName())
                            .authorities(authorities).build();

                }).orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return customUserPrincipal;
    }
}
