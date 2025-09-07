package com.example.rolesAuth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.rolesAuth.service.PermissionService;

@RestController
@RequestMapping("api/permission")
public class PermissionController {

    @Autowired
    PermissionService permissionService;

    @GetMapping("/all-permission")
    public ResponseEntity<?> getAllPermission() {
        return (!permissionService.getAllPermission().isEmpty())
                ? ResponseEntity.status(HttpStatus.OK).body(permissionService.getAllPermission())
                : ResponseEntity.status(HttpStatus.NO_CONTENT).body(permissionService.getAllPermission());
    }
}
