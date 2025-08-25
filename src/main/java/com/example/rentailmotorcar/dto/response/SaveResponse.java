package com.example.rentailmotorcar.dto.response;

import java.util.Set;

import com.example.rentailmotorcar.entity.User;

public class SaveResponse {
   String id;
   User user;
   Set<VehicleResponse> vehicle; // ép trong service
}
