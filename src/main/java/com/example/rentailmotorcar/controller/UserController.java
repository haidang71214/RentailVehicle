package com.example.rentailmotorcar.controller;

import java.io.IOException;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.rentailmotorcar.dto.request.UserRequestDto;
import com.example.rentailmotorcar.dto.response.ApiResponse;
import com.example.rentailmotorcar.dto.response.UserResponseDto;
import com.example.rentailmotorcar.service.UserService;

import jakarta.mail.MessagingException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {
    UserService userService;
// create user with role admin
   @PostMapping(value = "/createUser" , consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
public ApiResponse<UserResponseDto> createUser(
    // form data 
         @ModelAttribute UserRequestDto userRequestDto,
        @RequestPart(value = "avatar", required = false) MultipartFile avatar) throws IOException {
    return ApiResponse.<UserResponseDto>builder()
            .code(200)
            .results(userService.createUser(userRequestDto, avatar))
            .build();
}
    // register
     @PostMapping(value = "/register",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
public ApiResponse<UserResponseDto> register(
    // form data 
        @ModelAttribute UserRequestDto userRequestDto,
        @RequestPart(value = "avatar", required = false) MultipartFile avatar) throws IOException {
    return ApiResponse.<UserResponseDto>builder()
            .code(200)
            .results(userService.registerUser(userRequestDto, avatar))
            .build();
}
    // find user detail by admin
    @GetMapping("/findUser/{id}")
    public ApiResponse<UserResponseDto> findUser(@PathVariable("id") String id){
        return ApiResponse.<UserResponseDto>builder()
        .code(200)
        .results(userService.findUserbyId(id))
        .build();
    }
    // admin update user
   @PatchMapping(value = "/adminUpdateUser/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ApiResponse<UserResponseDto> updateUserByAdmin(
        @PathVariable("id") String userId,
         @ModelAttribute UserRequestDto userRequestDto,   // nhận JSON string
    @RequestPart(value = "avatar", required = false) MultipartFile avatar)throws IOException{

        return ApiResponse.<UserResponseDto>builder()
        .results(userService.updateUser(userId,userRequestDto,avatar ))
        .build();
    }   
    // user update chinh no
    @PatchMapping(value = "userUpdateUser/{id}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ApiResponse<UserResponseDto> userUpdateUser(
        @PathVariable("id") String id,
        @ModelAttribute UserRequestDto userRequestDto,
        @RequestPart(value = "avatar",required = false) MultipartFile avatar
    ) throws IOException{
        return ApiResponse.<UserResponseDto>builder()
        .code(200)
        .results(userService.userUpdateUser(id, userRequestDto, avatar))
        .build();
    }
    @PostMapping("/sendResetCode")
    public ApiResponse<Void> sendResetCode(@RequestBody String email)throws MessagingException{
        return ApiResponse.<Void>builder()
        .code(200)
        .message("Send mail successful")
        .results(userService.sendCodeToEmail(email))
        .build();
    }
    @PostMapping("/resetPassword")
    public ApiResponse<Void> checkTokenAndResetPass(
        @RequestBody String token,
        @RequestBody String newPassword
    ){
        return ApiResponse.<Void>builder()
        .results(userService.checkResetToken(token, newPassword))
        .build();
    }
}
