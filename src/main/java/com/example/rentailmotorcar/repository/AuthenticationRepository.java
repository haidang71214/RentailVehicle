package com.example.rentailmotorcar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.rentailmotorcar.entity.User;

public interface AuthenticationRepository extends JpaRepository<User, String>   {

}
