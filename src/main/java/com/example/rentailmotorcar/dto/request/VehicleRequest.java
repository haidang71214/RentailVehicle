package com.example.rentailmotorcar.dto.request;

import java.util.Set;

import com.example.rentailmotorcar.enums.BrandEnums;
import com.example.rentailmotorcar.enums.VehicleStatus;
import com.example.rentailmotorcar.enums.VehicleType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
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
public class VehicleRequest {
   String id ;
   // IMAGE_NOT_NULL
      @NotNull(message = "IMAGE_NOT_NULL")
   @NotBlank(message = "IMAGE_NOT_NULL")
   @NotEmpty(message = "IMAGE_NOT_NULL")
   Set<String> imageUrl;
      @NotNull(message = "TYPE_NOT_NULL")
   @NotBlank(message = "TYPE_NOT_NULL")
   @NotEmpty(message = "TYPE_NOT_NULL")
   VehicleType types;
   @NotNull(message = "NAME_NOT_NULL")
   @NotBlank(message = "NAME_NOT_NULL")
   @NotEmpty(message = "NAME_NOT_NULL")
   String name;
   VehicleStatus status; // đang thuê, đang rảnh / đang bảo dưỡng

   int price; // giá
   @NotNull(message = "BRAND_NOT_NULL")
   @NotBlank(message = "BRAND_NOT_NULL")
   @NotEmpty(message = "BRAND_NOT_NULL")
   BrandEnums brand;
   String owner;
}
