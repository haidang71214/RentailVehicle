package com.example.rentailmotorcar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.rentailmotorcar.entity.Role;

public interface RoleRepository extends JpaRepository<Role,String> {
   
}
