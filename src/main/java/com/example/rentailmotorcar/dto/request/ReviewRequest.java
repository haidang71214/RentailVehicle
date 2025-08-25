package com.example.rentailmotorcar.dto.request;
import com.example.rentailmotorcar.entity.User;
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
public class ReviewRequest {
   String id; // hide cái này không ta
   Vehicle vehicle;
   User user; // map với user
   String title;
}
