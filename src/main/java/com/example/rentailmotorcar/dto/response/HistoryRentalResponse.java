package com.example.rentailmotorcar.dto.response;

import java.util.Set;

import com.example.rentailmotorcar.entity.Vehicle;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class HistoryRentalResponse {
   String id;
   // @ManyToOne  // người thuê
   UserResponseDto renter; 
   // @ManyToOne
   Set<UserResponseDto> owner; // người cho thuê
   Set<Vehicle> vehicles; 
   java.time.LocalDateTime startTime; // thời gian bắt đầu thuê
   java.time.LocalDateTime endTime;
   int totalPrice;
}
