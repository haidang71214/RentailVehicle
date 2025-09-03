package com.example.rentailmotorcar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.rentailmotorcar.entity.Review;
import com.example.rentailmotorcar.entity.Vehicle;

public interface ReviewRepository extends JpaRepository<Review, String> {
   List<Review>  findByVehicle(Vehicle vehicle);
}
