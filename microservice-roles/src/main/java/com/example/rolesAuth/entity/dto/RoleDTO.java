package com.example.rolesAuth.entity.dto;

import java.util.Set;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Format role")
public class RoleDTO {
    @Schema(description = "Id role", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private long id;
    @Schema(description = "Name role", example = "Admin", accessMode = Schema.AccessMode.READ_ONLY)
    private String name;
    @Schema(description = "List permission by role", example = "READ", accessMode = Schema.AccessMode.READ_ONLY)
    Set<PermissionDTO> listPermission;
}
