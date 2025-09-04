package com.example.rentailmotorcar.dto.response;
import com.example.rentailmotorcar.enums.ToOwnerRequestStatus;

import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class ToOwnerResDto {
   UserResponseDto user;
   //  PENDING','APPROVED','REJECTED
   ToOwnerRequestStatus status;
   String cccdNumber;
   boolean checkCccd;
}
