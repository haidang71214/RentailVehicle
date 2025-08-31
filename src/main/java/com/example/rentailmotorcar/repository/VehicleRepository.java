package com.example.rentailmotorcar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.rentailmotorcar.entity.Vehicle;

public interface VehicleRepository  extends JpaRepository<Vehicle,String> {
   
}
