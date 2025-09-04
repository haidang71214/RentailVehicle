package com.example.rentailmotorcar.dto.request;

import com.example.rentailmotorcar.entity.User;
import com.example.rentailmotorcar.enums.ToOwnerRequestStatus;

import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class ToOwnerReqDto {
   User user;
   //  PENDING','APPROVED','REJECTED
   String cccdSideFace; // không lưu, set null
   String cccdBackFace; // set để check với ai xong xóa luôn
   boolean checkCccd;
}
