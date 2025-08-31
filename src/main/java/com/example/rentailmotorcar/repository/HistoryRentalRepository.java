package com.example.rentailmotorcar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.rentailmotorcar.entity.HistoryRentail;
import com.example.rentailmotorcar.entity.User;

public interface HistoryRentalRepository extends JpaRepository<HistoryRentail,String>  {
   List<HistoryRentail> findByOwner(User user);
}
