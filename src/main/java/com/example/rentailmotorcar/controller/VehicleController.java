package com.example.rentailmotorcar.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.example.rentailmotorcar.dto.request.VehicleRequest;
import com.example.rentailmotorcar.dto.response.ApiResponse;
import com.example.rentailmotorcar.dto.response.VehicleResponse;
import com.example.rentailmotorcar.service.VehicleService;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/vehicle")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class VehicleController {
   VehicleService vehicleService;
   
   @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
   public ApiResponse<VehicleResponse> createVehicle(
      @Valid
      @ModelAttribute VehicleRequest vehicleRequest,
      @RequestPart(value = "images", required = false) List<MultipartFile> avatar) throws IOException
      {
         return ApiResponse.<VehicleResponse>builder()
         .code(200)
         .results(vehicleService.createVehicle(vehicleRequest, avatar))
         .build();
   }

   @GetMapping()
   public ApiResponse<List<VehicleResponse>> getlistVehicle(){
      return ApiResponse.<List<VehicleResponse>>builder()
      .code(200)
      .results(vehicleService.getVehicleList())
      .build();
   }
   @GetMapping("{id}")
   public ApiResponse<VehicleResponse> getDetailVehicle(@PathVariable("id")String id){
      return  ApiResponse.<VehicleResponse>builder()
      .code(200)
      .results(vehicleService.getDetailVehicle(id))
      .build();
   }
   @DeleteMapping("{id}")
   public ApiResponse<Void> deleteVehicel(@PathVariable("id")String id){
      return ApiResponse.<Void>builder()
      .code(200)
      .results(vehicleService.deleteVehicle(id))
      .build();
   }
}
