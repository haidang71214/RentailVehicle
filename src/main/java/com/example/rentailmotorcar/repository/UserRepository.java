package com.example.rentailmotorcar.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.rentailmotorcar.entity.User;

public interface UserRepository extends JpaRepository<User,String> {
   Optional<User> findByUsername(String username);
   Optional<User> findByEmail(String email);
   Optional<User> findByResetToken(String resetToken);
}
