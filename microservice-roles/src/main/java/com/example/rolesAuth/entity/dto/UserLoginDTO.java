package com.example.rolesAuth.entity.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginDTO {
    private String mail;
    private String password;
}
