package com.example.rentailmotorcar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.rentailmotorcar.entity.Permission;


public interface PermissionRepository extends JpaRepository<Permission, String> {
   
}
