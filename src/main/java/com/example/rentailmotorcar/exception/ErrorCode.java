package com.example.rentailmotorcar.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
   USER_EXISTED( 1001,"user existed",HttpStatus.FOUND),
   USERNAME_INVALID(1002,"User name not vailid",HttpStatus.BAD_REQUEST),
   PASSWORD_INVALID(1003,"password không hợp lệ",HttpStatus.BAD_REQUEST),
   INVALID_KEY(1004,"Key error không xác định",HttpStatus.BAD_REQUEST),
   USER_NOT_FOUND(2005,"Không tìm thấy user",HttpStatus.NOT_FOUND),
   UNKNOWN_ERROR(1007,"lỗi không xác định",HttpStatus.BAD_REQUEST),
   UNAUTHORIZED(1008,"Không xác thực",HttpStatus.UNAUTHORIZED),
   PERMISSION_NOT_FOUND(1010,"Permission not found",HttpStatus.NOT_FOUND),
   ROLE_NOT_FOUND(1011,"Role not found",HttpStatus.NOT_FOUND),
   EMAIL_NOT_VAILID(1006,"Email not vailid",HttpStatus.BAD_REQUEST),
    UNAUTHENTICATED(1009,"Lỗi không xác thực",HttpStatus.UNAUTHORIZED);
   private int code;
   private String message;
   private HttpStatusCode statusCode;



}
