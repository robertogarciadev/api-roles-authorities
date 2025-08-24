package com.example.rolesAuth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.rolesAuth.service.RoleService;

@RestController
@RequestMapping("api/role")
public class RoleController {

    @Autowired
    RoleService roleService;

    @GetMapping("/all-roles")
    public ResponseEntity<?> getAllRoles() {
        return (!roleService.getAllRoles().isEmpty())
                ? ResponseEntity.status(HttpStatus.OK).body(roleService.getAllRoles())
                : ResponseEntity.status(HttpStatus.NO_CONTENT).body(roleService.getAllRoles());
    }
}
