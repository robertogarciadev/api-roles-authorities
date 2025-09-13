package com.example.rolesAuth.controller;

import com.example.rolesAuth.entity.dto.UserCreateDTO;
import com.example.rolesAuth.entity.dto.UserJWTDTO;
import com.example.rolesAuth.entity.dto.UserLoginDTO;
import com.example.rolesAuth.entity.dto.UserResponseDTO;
import com.example.rolesAuth.exception.ErrorExceptionResponse;
import com.example.rolesAuth.service.AuthenticationService;
import com.example.rolesAuth.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/authentication")
@Tag(name = "Authentication", description = "Sing-up & sing-in by users")
public class AuthenticationController {

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    UserService userService;


    @Operation(
            summary = "For registration of users",
            description = "Allows register user with mail, name, last name, password and role. \n" +
                    "Only the role id is required for registration",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Mail (unique), name, last name, password and role(only id)",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UserCreateDTO.class)
                    )
            ),
           responses = {
                    @ApiResponse (
                            description = "User created",
                            responseCode = "201",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = UserResponseDTO.class)
                            )
                    ),
                   @ApiResponse(
                           description = "Bad request",
                           responseCode = "400",
                           content = @Content(
                                   mediaType = "application/jason",
                                   schema = @Schema(implementation = ErrorExceptionResponse.class)
                           )
                   ),
                   @ApiResponse(
                           description = "Mail already exist",
                           responseCode = "409",
                           content = @Content(
                                   mediaType = "application/jason"
                           )
                   )
           }
    )
    @PostMapping("/sing-up")
    public ResponseEntity<?> singUp(@RequestBody UserCreateDTO dto) {
        return userService.save(dto)
                .map(item -> ResponseEntity.status(HttpStatus.CREATED).body(item))
                .orElse(ResponseEntity.badRequest().build());
    }


    @Operation(
            summary = "For login in app",
            description = "Allows the user to login through in the app. Once logged in, the user receive Token to make request",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "User authentication with mail and password",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation =  UserLoginDTO.class)
                    )
            ),
            responses = {
                    @ApiResponse(
                            description = "User founded",
                            responseCode = "201",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = UserJWTDTO.class)
                            )
                    ),
                    @ApiResponse(
                            description = "User not found",
                            responseCode = "404",
                            content = @Content(
                                    mediaType = "application/json"
                            )
                    )
            }
    )
    @PostMapping("/sing-in")
    public ResponseEntity<?> singIn(@RequestBody UserLoginDTO dto) {
        return authenticationService.singInAndReturnJWT(dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
