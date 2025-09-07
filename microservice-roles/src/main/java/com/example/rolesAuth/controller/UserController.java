package com.example.rolesAuth.controller;



import java.util.List;

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
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/all-user")
    public ResponseEntity<?> getAllUser() {
        List<UserResponseDTO> listUsersDTO = userService.getAllUsers();
        return (!listUsersDTO.isEmpty())
                ? ResponseEntity.status(HttpStatus.OK).body(listUsersDTO)
                : ResponseEntity.noContent().build();
    }

}
