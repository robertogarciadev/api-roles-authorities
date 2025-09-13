package com.example.rolesAuth.controller;



import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.rolesAuth.service.UserService;
import com.example.rolesAuth.entity.dto.UserResponseDTO;



@RestController
@RequestMapping("api/user")
@Tag(name = "Users")
public class UserController {

    @Autowired
    UserService userService;

    @Operation(
            summary = "Get all users.",
            description = "Allows you to check all users registered in the application.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "List all users",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(
                                            schema = @Schema(implementation = UserResponseDTO.class)
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "204",
                            description = "Empty list",
                            content = @Content
                    )
            }
    )
    @GetMapping("/all-user")
    public ResponseEntity<?> getAllUser() {
        List<UserResponseDTO> listUsersDTO = userService.getAllUsers();
        return (!listUsersDTO.isEmpty())
                ? ResponseEntity.status(HttpStatus.OK).body(listUsersDTO)
                : ResponseEntity.noContent().build();
    }

}
