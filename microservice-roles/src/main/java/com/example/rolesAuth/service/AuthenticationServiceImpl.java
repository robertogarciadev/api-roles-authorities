package com.example.rolesAuth.service;

import com.example.rolesAuth.entity.dto.UserDTO;
import com.example.rolesAuth.security.CustomUserPrincipal;
import com.example.rolesAuth.security.jwt.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtProvider jwtProvider;

    /**
     * Cuando un usuario hace login
     * @param dto
     * @return UserDTO with token
     */
    @Override
    public Optional<UserDTO> singInAndReturnJWT(UserDTO dto) {

        //Por debajo de esta operación ocurre:
        //Buscar al usuario en la BD (mediante tu CustomUserDetailsService).
        //Verificar que la contraseña que llegó (dto.getPassword()) coincide con la almacenada (después de aplicar el PasswordEncoder).
        //Si va bien → retorna un objeto Authentication ya autenticado.
        //Si falla → lanza una excepción (BadCredentialsException, etc.).
        Authentication auth= authenticationManager.authenticate(
                 new UsernamePasswordAuthenticationToken(dto.getMail(), dto.getPassword())
        );

        CustomUserPrincipal userPrincipal = (CustomUserPrincipal) auth.getPrincipal();
        String token = jwtProvider.generateToken(userPrincipal);

        return Optional.of(UserDTO.builder()
                .id(userPrincipal.getId())
                .name(userPrincipal.getName())
                .lastName(userPrincipal.getLastName())
                .token(token)
                .build());
    }
}
