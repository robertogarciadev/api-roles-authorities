package com.example.rolesAuth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.rolesAuth.entity.PermissionEntity;

public interface PermissionRepository extends JpaRepository<PermissionEntity, Long> {

}
