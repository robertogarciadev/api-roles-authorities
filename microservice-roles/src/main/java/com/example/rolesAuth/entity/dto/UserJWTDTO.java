package com.example.rolesAuth.entity.dto;


import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserJWTDTO {
    private long id;
    private String mail;
    private String name;
    private String lastName;
    private RoleDTO role;
    private String token;
}
