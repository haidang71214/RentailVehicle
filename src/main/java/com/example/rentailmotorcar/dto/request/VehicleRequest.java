package com.example.rentailmotorcar.dto.request;

import java.util.Set;

import com.example.rentailmotorcar.enums.BrandEnums;
import com.example.rentailmotorcar.enums.VehicleStatus;
import com.example.rentailmotorcar.enums.VehicleType;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class VehicleRequest {
   String id ; 
   Set<String> imageUrl;
   VehicleType types;
   String name;
   VehicleStatus status; // đang thuê, đang rảnh / đang bảo dưỡng
   int star;   // star 
   int price; // giá
   BrandEnums brand;
   String owner;
   String rentailer;
}
