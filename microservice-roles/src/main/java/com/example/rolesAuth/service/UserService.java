package com.example.rolesAuth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.rolesAuth.repository.PermissionRepository;
import com.example.rolesAuth.repository.RoleRepository;
import com.example.rolesAuth.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PermissionRepository permissionRepository;
}
