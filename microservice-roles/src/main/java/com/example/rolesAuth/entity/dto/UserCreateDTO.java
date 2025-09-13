package com.example.rolesAuth.entity.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "User format for register in app")
public class UserCreateDTO {
    @Schema(description = "User mail", example = "rob@gmail.com")
    private String mail;
    @Schema(description = "Name user", example = "John")
    private String name;
    @Schema(description = "Last name user", example = "Mark")
    private String lastName;
    @Schema(description = "Password user", example = "John2004!")
    private String password;
    @Schema(description = "Role user", example = "1")
    private RoleDTO role;
}
