package com.example.rolesAuth.controller;

import com.example.rolesAuth.entity.dto.PermissionDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.rolesAuth.service.PermissionService;

@RestController
@RequestMapping("api/permission")
@Tag(name = "Permissions")
public class PermissionController {

    @Autowired
    PermissionService permissionService;

    @Operation(
            summary = "Get all permission",
            description = "Allows to check all permission that each role has",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "List all permission by role",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(
                                            schema = @Schema(implementation = PermissionDTO.class)
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "204",
                            description = "List empty",
                            content = @Content
                    )
            }
    )
    @GetMapping("/all-permission")
    public ResponseEntity<?> getAllPermission() {
        return (!permissionService.getAllPermission().isEmpty())
                ? ResponseEntity.status(HttpStatus.OK).body(permissionService.getAllPermission())
                : ResponseEntity.status(HttpStatus.NO_CONTENT).body(permissionService.getAllPermission());
    }
}
