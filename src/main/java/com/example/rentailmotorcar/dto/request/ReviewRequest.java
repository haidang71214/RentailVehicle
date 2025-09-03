package com.example.rentailmotorcar.dto.request;
import com.example.rentailmotorcar.entity.User;
import com.example.rentailmotorcar.entity.Vehicle;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
   @NotNull(message = "TITTLE_NOT_NULL")
   @NotBlank(message = "TITTLE_NOT_NULL")
   String title;
   int star;
}
