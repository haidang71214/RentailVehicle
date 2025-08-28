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
   UNKNOWN_ERROR(1006,"lỗi không xác định",HttpStatus.BAD_REQUEST),
   UNAUTHORIZED(1007,"Không xác thực",HttpStatus.UNAUTHORIZED),
   PERMISSION_NOT_FOUND(1010,"Permission not found",HttpStatus.NOT_FOUND),
   ROLE_NOT_FOUND(1011,"Role not found",HttpStatus.NOT_FOUND),
   EMAIL_NOT_VAILID(1008,"Email not vailid",HttpStatus.BAD_REQUEST),
   EMAIL_ERROR(1012,"Email can not send",HttpStatus.BAD_REQUEST),
   FILE_ERROR(1013,"File update error",HttpStatus.BAD_REQUEST),
   RESETCODE_ERROR(1014,"Reset code error",HttpStatus.BAD_REQUEST),
   SIGNAL_KEY_NOT_VAILID(1015,"Key error",HttpStatus.BAD_GATEWAY),
   SIGNKEY_ERROR(1016,"Parse error",HttpStatus.BAD_GATEWAY),
   UNAUTHENTICATED(1009,"Lỗi không xác thực",HttpStatus.UNAUTHORIZED);
   
   private int code;
   private String message;
   private HttpStatusCode statusCode;



}
