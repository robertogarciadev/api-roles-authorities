package com.example.rolesAuth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


import com.example.rolesAuth.entity.UserEntity;
import com.example.rolesAuth.entity.enums.RoleEnum;
import com.example.rolesAuth.repository.UserRepository;

@SpringBootApplication
public class RolesApplication {

	@Autowired
	UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(RolesApplication.class, args);		
	}

	@Bean
	CommandLineRunner init(){
		return args ->{
			userRepository.save(
				UserEntity.builder()
				.mail("rob@gmail.com")
				.password("1234")
				.role(RoleEnum.ADMIN.name()).build());
		};
	}
}
