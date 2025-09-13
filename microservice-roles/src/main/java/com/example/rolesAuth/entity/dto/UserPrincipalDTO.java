package com.example.rolesAuth.entity.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserPrincipalDTO {
    private long id;
    private String mail;
    private String name;
    private String lastName;
    private String password;
    private RoleDTO role;
}
