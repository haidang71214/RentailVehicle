package com.example.rentailmotorcar.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.rentailmotorcar.dto.request.HistoryRentalVehicleRequest;
import com.example.rentailmotorcar.dto.response.HistoryRentalResponse;
import com.example.rentailmotorcar.entity.HistoryRentail;
@Mapper(componentModel = "spring")
public interface HistoryRentalMapper {
   @Mapping(target = "vehicles",ignore = true)
   @Mapping(target = "owner", ignore = true)
   HistoryRentail toHoHistoryRentail(HistoryRentalVehicleRequest historyRentalVehicleRequest);
   HistoryRentalResponse toHistoryRentalResponse(HistoryRentail historyRentail);
}
