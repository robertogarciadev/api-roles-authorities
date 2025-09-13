package com.example.rolesAuth.entity.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Format response to client")
public class UserResponseDTO {
    @Schema(description = "User id")
    private long id;
    @Schema(description = "User mail")
    private String mail;
    @Schema(description = "User name")
    private String name;
    @Schema(description = "User last name")
    private String lastName;
    @Schema(description = "User role")
    private RoleDTO role;
}
