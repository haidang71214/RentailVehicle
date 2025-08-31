package com.example.rentailmotorcar.controller;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.rentailmotorcar.dto.request.HistoryRentalVehicleRequest;
import com.example.rentailmotorcar.dto.response.ApiResponse;
import com.example.rentailmotorcar.dto.response.HistoryRentalResponse;
import com.example.rentailmotorcar.service.HistoryRentailSerivce;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/historyRental")
@RequiredArgsConstructor
@FieldDefaults(level =  AccessLevel.PRIVATE,makeFinal = true)
public class HistoryRentalController {
   HistoryRentailSerivce historyRentailSerivce;

   @PostMapping()
   public ApiResponse<HistoryRentalResponse> createHistory(@RequestBody HistoryRentalVehicleRequest historyRentalVehicleRequest){
      return ApiResponse.<HistoryRentalResponse>builder()
      .code(200)
      .results(historyRentailSerivce.createHistoryRental(historyRentalVehicleRequest))
      .build();
   }
   @GetMapping("/{id}")
   public ApiResponse<List<HistoryRentalResponse>> getHistory(@PathVariable("id") String id){
      return ApiResponse.<List<HistoryRentalResponse>>builder()
      .code(200)
      .results(historyRentailSerivce.getHistoryByOwner(id))
      .build();
   }
}
