package com.example.rentailmotorcar.service;

import java.util.HashSet;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.example.rentailmotorcar.dto.request.HistoryRentalVehicleRequest;
import com.example.rentailmotorcar.dto.response.HistoryRentalResponse;
import com.example.rentailmotorcar.entity.HistoryRentail;
import com.example.rentailmotorcar.entity.User;
import com.example.rentailmotorcar.exception.AppException;
import com.example.rentailmotorcar.exception.ErrorCode;
import com.example.rentailmotorcar.mapper.HistoryRentalMapper;
import com.example.rentailmotorcar.repository.HistoryRentalRepository;
import com.example.rentailmotorcar.repository.UserRepository;
import com.example.rentailmotorcar.repository.VehicleRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Slf4j
public class HistoryRentailSerivce {
   HistoryRentalRepository historyRentalRepository;
   HistoryRentalMapper historyMapper;
   VehicleRepository vehicleRepository;
   UserRepository userRepository;
   // get history
   // create history (inject qua cai phuong tien de no tao)
   public HistoryRentalResponse createHistoryRental(HistoryRentalVehicleRequest historyRentalVehicleRequest){
      HistoryRentail historyRentail = historyMapper.toHoHistoryRentail(historyRentalVehicleRequest); 
      var vehicles = vehicleRepository.findAllById(historyRentalVehicleRequest.getVehicles());
      historyRentail.setVehicles(new HashSet<>(vehicles));
      var owner = userRepository.findAllById(historyRentalVehicleRequest.getOwner());
      historyRentail.setOwner(new HashSet<>(owner));
      historyRentail = historyRentalRepository.save(historyRentail);
      return historyMapper.toHistoryRentalResponse(historyRentail);
   }
   // get History cho người chủ
   @PreAuthorize("hasRole('OWNER')")
   public List<HistoryRentalResponse> getHistoryByOwner(String ownerId){
      User user = userRepository.findById(ownerId).orElseThrow(()-> new AppException(ErrorCode.USER_NOT_FOUND));
      return historyRentalRepository.findByOwner(user).stream().map(historyMapper :: toHistoryRentalResponse).toList();
   }
}
