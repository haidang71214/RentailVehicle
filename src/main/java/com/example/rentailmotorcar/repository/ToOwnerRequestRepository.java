package com.example.rentailmotorcar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.rentailmotorcar.entity.ToOwnerRequest;

public interface ToOwnerRequestRepository extends JpaRepository<ToOwnerRequest,String>  {
   
}
