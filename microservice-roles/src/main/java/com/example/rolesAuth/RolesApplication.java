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

	@Autowired
	UserRepository userRepository;

	@Autowired
	PermissionRepository permissionRepository;

	@Autowired
	RoleRepository roleRepository;

	public static void main(String[] args) {
		SpringApplication.run(RolesApplication.class, args);		
	}

	@Bean
	CommandLineRunner init(){
		return args ->{

			// CREATE PERMISSION
			PermissionEntity permissionCreate = PermissionEntity.builder().name(PermissionEnum.CREATE.name()).build();
			PermissionEntity permissionRead = PermissionEntity.builder().name(PermissionEnum.READ.name()).build();
			PermissionEntity permissionUpdate = PermissionEntity.builder().name(PermissionEnum.UPDATE.name()).build();
			PermissionEntity permissionDelete = PermissionEntity.builder().name(PermissionEnum.DELETE.name()).build();

			// SAVE PERMISSION
			permissionRepository.saveAll(List.of(
				permissionCreate,
				permissionRead,
				permissionUpdate,
				permissionDelete
			));

			// CREATE ROLES
			RoleEntity roleAdmin = RoleEntity.builder()
			.name(RoleEnum.ADMIN.name())
			.listPermission(Set.of(
					permissionCreate,
					permissionRead,
					permissionUpdate, 
					permissionDelete)).build();

			RoleEntity roleUser = RoleEntity.builder()
			.name(RoleEnum.USER.name())
			.listPermission(Set.of(
					permissionCreate,
					permissionRead,
					permissionUpdate)).build();

			RoleEntity roleInvited = RoleEntity.builder()
			.name(RoleEnum.INVITED.name())
			.listPermission(Set.of(permissionRead)).build();

			//SAVE ROLES
			roleRepository.saveAll(List.of(
			roleAdmin,
			roleUser,
			roleInvited
			));
		};
	}
}
