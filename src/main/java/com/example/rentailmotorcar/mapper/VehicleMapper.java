package com.example.rentailmotorcar.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.rentailmotorcar.dto.request.VehicleRequest;
import com.example.rentailmotorcar.dto.response.VehicleResponse;
import com.example.rentailmotorcar.entity.Vehicle;


@Mapper(componentModel = "spring")
public interface VehicleMapper {
   @Mapping(target = "owner",ignore = true)
   @Mapping(target = "rentailer",ignore = true)
   @Mapping(target = "reviews",ignore = true) // ẩn đi cái reviews, vì cái review nó là khi được thuê mới map vô
   Vehicle toVehicle(VehicleRequest vehicleRequest);
   
   VehicleResponse toVehicleResponse(Vehicle vehicle);
}
