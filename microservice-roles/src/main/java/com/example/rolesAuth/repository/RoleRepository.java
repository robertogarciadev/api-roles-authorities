package com.example.rolesAuth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.rolesAuth.entity.RoleEntity;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

}
