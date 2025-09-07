package com.example.rolesAuth.controller;

import com.example.rolesAuth.entity.dto.UserCreateDTO;
import com.example.rolesAuth.entity.dto.UserLoginDTO;
import com.example.rolesAuth.service.AuthenticationService;
import com.example.rolesAuth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/authentication")
public class AuthenticationController {

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    UserService userService;

    @PostMapping("/sing-up")
    public ResponseEntity<?> singUp(@RequestBody UserCreateDTO dto) {
        return userService.save(dto)
                .map(item -> ResponseEntity.status(HttpStatus.CREATED).body(item))
                .orElse(ResponseEntity.badRequest().build());
    }

    @PostMapping("/sing-in")
    public ResponseEntity<?> singIn(@RequestBody UserLoginDTO dto) {
        return authenticationService.singInAndReturnJWT(dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
