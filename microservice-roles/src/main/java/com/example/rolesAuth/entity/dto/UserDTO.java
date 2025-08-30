package com.example.rolesAuth.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private long id;
    private String mail;
    private String name;
    private String lastName;
    private String password;
    private RoleDTO role;
}
