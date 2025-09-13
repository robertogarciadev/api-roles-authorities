package com.example.rolesAuth.entity.dto;


import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Logged in user format")
public class UserJWTDTO {
    @Schema(description = "User id", example = "1")
    private long id;
    @Schema(description = "User mail", example = "rob@gmail.com")
    private String mail;
    @Schema(description = "User name", example = "Rob")
    private String name;
    @Schema(description = "User last name", example = "Gordon")
    private String lastName;
    @Schema(description = "User role", example = "ADMIN")
    private RoleDTO role;
    @Schema(description = "Token for request", accessMode = Schema.AccessMode.READ_ONLY)
    private String token;
}
