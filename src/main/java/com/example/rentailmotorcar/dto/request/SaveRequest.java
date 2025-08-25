package com.example.rentailmotorcar.dto.request;

import java.util.Set;

import com.example.rentailmotorcar.entity.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class SaveRequest {
   String id;
   User user;
   Set<String> vehicle; // lưu String
}
