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
@Schema(description = "Format permission")
public class PermissionDTO {
    @Schema(description = "id", example = "1",accessMode = Schema.AccessMode.READ_ONLY)
    private long id;
    @Schema(description = "Name permissions", example = "READ", accessMode = Schema.AccessMode.READ_ONLY)
    private String name;
}
