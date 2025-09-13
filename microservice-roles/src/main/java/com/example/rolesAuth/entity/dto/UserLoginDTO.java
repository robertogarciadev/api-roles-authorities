package com.example.rolesAuth.entity.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Login format")
public class UserLoginDTO {
    @Schema(description = "User mail", example = "rob@gmail.com")
    private String mail;
    @Schema(description = "User password", example = "1234")
    private String password;
}
