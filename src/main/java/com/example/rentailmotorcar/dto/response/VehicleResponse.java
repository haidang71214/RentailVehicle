package com.example.rentailmotorcar.dto.response;

import java.util.Set;


import com.example.rentailmotorcar.enums.BrandEnums;
import com.example.rentailmotorcar.enums.VehicleStatus;
import com.example.rentailmotorcar.enums.VehicleType;

public class VehicleResponse {
   Set<String> imageUrl;
   VehicleType types;
   String name;
   VehicleStatus status; // đang thuê, đang rảnh / đang bảo dưỡng
   int star;   // star 
   int price; // giá
   BrandEnums brand;
   String owner;
   String rentailer;
   Set<ReviewResponse> reviews;
}
