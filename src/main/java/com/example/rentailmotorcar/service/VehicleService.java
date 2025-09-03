package com.example.rentailmotorcar.service;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.rentailmotorcar.dto.request.VehicleRequest;
import com.example.rentailmotorcar.dto.response.VehicleResponse;
import com.example.rentailmotorcar.entity.Vehicle;
import com.example.rentailmotorcar.enums.VehicleStatus;
import com.example.rentailmotorcar.exception.AppException;
import com.example.rentailmotorcar.exception.ErrorCode;
import com.example.rentailmotorcar.mapper.VehicleMapper;

import com.example.rentailmotorcar.repository.VehicleRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class VehicleService {
   VehicleMapper vehicleMapper;
   VehicleRepository vehicleRepository;
   CloudinaryService cloudinaryService;
   // create vehicle
   @PreAuthorize("hasRole('OWNER')")
   public VehicleResponse createVehicle( VehicleRequest vehicleRequest,List<MultipartFile> images) throws IOException{
      Vehicle vehicle = vehicleMapper.toVehicle(vehicleRequest);
     Set<String> urls = new HashSet<>();
    for (MultipartFile image : images) {
        String url = cloudinaryService.uploadFile(image); // hàm upload cloud trả về URL
        urls.add(url);
    }
    vehicle.setStatus(VehicleStatus.AVAILABLE);
    vehicle.setStar(4);
    vehicle.setImageUrl(urls);
    vehicleRepository.save(vehicle);
    return vehicleMapper.toVehicleResponse(vehicle);
   }
   // xem 
   public List<VehicleResponse> getVehicleList(){
      return vehicleRepository.findAll().stream().map(vehicleMapper :: toVehicleResponse).toList();
   }
   // detail
   public VehicleResponse getDetailVehicle(String id){
      // 
      
      Vehicle vehicle = vehicleRepository.findById(id).orElseThrow(()-> new AppException(ErrorCode.VEHICLE_NOT_FOUND));
      return vehicleMapper.toVehicleResponse(vehicle);
   }
   // delete
   // không cho phép xóa xe đang thuê
   // không xóa lịch sử thuê xe
   @PreAuthorize("hasRole('OWNER')")
   public Void deleteVehicle(String id){
      Vehicle vehicle = vehicleRepository.findById(id).orElseThrow(()-> new AppException(ErrorCode.VEHICLE_NOT_FOUND));

      if(vehicle.getStatus() == VehicleStatus.RENTED){
         throw new AppException(ErrorCode.RENTED_VEHICLE);
      }
      // gỡ quan hệ 
      if (vehicle.getOwner() != null) {
        vehicle.setOwner(null);
    }

    if (vehicle.getRentailer() != null) {
        vehicle.setRentailer(null);
    }
      vehicleRepository.deleteById(id);
      return null;
   }
}
