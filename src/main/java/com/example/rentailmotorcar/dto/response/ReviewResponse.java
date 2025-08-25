package com.example.rentailmotorcar.dto.response;
import com.example.rentailmotorcar.entity.User;
import com.example.rentailmotorcar.entity.Vehicle;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewResponse {
   String id; // hide cái này không ta
   Vehicle vehicle;
   User user; // map với user
   String title;
}
