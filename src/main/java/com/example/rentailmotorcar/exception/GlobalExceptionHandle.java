package com.example.rentailmotorcar.exception;

import java.nio.file.AccessDeniedException;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.rentailmotorcar.dto.response.ApiResponse;

@ControllerAdvice
public class GlobalExceptionHandle {
   //"cấu hình lại cái runtime exception"
   // ở đây bao gồm những class mà mình muốn bắt, và respone lại nó
   private ErrorCode errorCode;
   @ExceptionHandler(value = RuntimeException.class)
   // khi mình khai báo như này thì nó tự lấy thông tin của thằng truyền vào và nhả ra cái đẹp hơn
      ResponseEntity<ApiResponse> handlingRuntimeException(RuntimeException exception){
            ApiResponse apiResponse = new ApiResponse<>();
            apiResponse.setCode(200);
            apiResponse.setMessage("Lỗi Không Xác Định");
            return ResponseEntity.badRequest().body(apiResponse);
      }
   @ExceptionHandler(value = AppException.class)
      ResponseEntity<ApiResponse> handlingAppException(AppException exception){
            ApiResponse apiResponse = new ApiResponse<>();
            ErrorCode errorCode = exception.getErrorCode();
            // get code là ở chỗ ErroCode đấy
            apiResponse.setCode(errorCode.getCode());
            apiResponse.setMessage(errorCode.getMessage());
            return ResponseEntity.status(errorCode.getStatusCode()).body(apiResponse);
      }

 @ExceptionHandler({AccessDeniedException.class, AuthorizationDeniedException.class})
public ResponseEntity<ApiResponse> handleAccessDenied(RuntimeException exception) {
    ErrorCode errorCode = ErrorCode.UNAUTHORIZED;
    return ResponseEntity.status(errorCode.getStatusCode()).body(
        ApiResponse.builder()
            .code(errorCode.getCode())
            .message(errorCode.getMessage())
            .build()
    );
}


      // handle valid error và lỗi không xác định 
   @ExceptionHandler(value =  MethodArgumentNotValidException.class)
   ResponseEntity<ApiResponse> handlingMethodArgurmentException(MethodArgumentNotValidException exception){
      ApiResponse apiResponse = new ApiResponse<>();
            String enumKey = exception.getFieldError().getDefaultMessage(); 
            // chỗ này nó đang lây cái error code, chính xác thì nó lấy luôn cả cái key của error code để nhét vào
            ErrorCode errorCode = ErrorCode.UNKNOWN_ERROR;
        try {
       errorCode = ErrorCode.valueOf(enumKey);
       
    } catch (IllegalArgumentException ex) {
       errorCode = ErrorCode.UNKNOWN_ERROR; // fallback nếu không có enum tương ứng
    }
            apiResponse.setCode(errorCode.getCode());
            apiResponse.setMessage(errorCode.getMessage());
         return ResponseEntity.status(errorCode.getStatusCode()).body(apiResponse);
   }
}
