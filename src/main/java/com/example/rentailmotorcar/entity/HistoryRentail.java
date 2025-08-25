package com.example.rentailmotorcar.entity;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HistoryRentail {
   @Id
   @GeneratedValue(strategy = GenerationType.UUID)
   String id;
   @ManyToOne  // người thuê
   User renter; 
   @ManyToOne
   User owner; // người cho thuê
   @ManyToMany // 1 lần có thể thuê nhiều xe, 1 xe có thể thuê nhiều lần
   Set<Vehicle> vehicles; 
   java.time.LocalDateTime startTime; // thời gian bắt đầu thuê
   java.time.LocalDateTime endTime;
   int totalPrice;
}
