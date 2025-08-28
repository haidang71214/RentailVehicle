package com.example.rentailmotorcar.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.rentailmotorcar.entity.Role;

public interface RoleRepository extends JpaRepository<Role,String> {
Optional<Role> findByName(String name);
}
