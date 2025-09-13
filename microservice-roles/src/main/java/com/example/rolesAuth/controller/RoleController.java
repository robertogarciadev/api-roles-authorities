package com.example.rolesAuth.controller;


import com.example.rolesAuth.entity.dto.RoleDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.rolesAuth.service.RoleService;

@RestController
@RequestMapping("api/role")
@Slf4j
@Tag(name = "Roles")
public class RoleController {

    @Autowired
    RoleService roleService;

    @Operation(
            summary = "Get all roles",
            description = "Allows to check all roles enables in the application",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "List all roles",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(
                                            schema = @Schema(implementation = RoleDTO.class)
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "201",
                            description = "List empty",
                            content = @Content
                    )
            }
    )
    @GetMapping("/all-roles")
    public ResponseEntity<?> getAllRoles() {
        return (!roleService.getAllRoles().isEmpty())
                ? ResponseEntity.status(HttpStatus.OK).body(roleService.getAllRoles())
                : ResponseEntity.status(HttpStatus.NO_CONTENT).body(roleService.getAllRoles());
    }
}
