package com.example.rolesAuth;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.rolesAuth.entity.PermissionEntity;
import com.example.rolesAuth.entity.RoleEntity;
import com.example.rolesAuth.entity.enums.PermissionEnum;
import com.example.rolesAuth.entity.enums.RoleEnum;
import com.example.rolesAuth.repository.PermissionRepository;
import com.example.rolesAuth.repository.RoleRepository;
import com.example.rolesAuth.repository.UserRepository;

@SpringBootApplication
public class RolesApplication {
	public static void main(String[] args) {
		SpringApplication.run(RolesApplication.class, args);		
	}
}
