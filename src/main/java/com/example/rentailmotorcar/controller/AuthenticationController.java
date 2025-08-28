package com.example.rentailmotorcar.controller;

import java.text.ParseException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.rentailmotorcar.dto.request.AuthenticationRequest;
import com.example.rentailmotorcar.dto.request.IntroSpectRequest;
import com.example.rentailmotorcar.dto.response.ApiResponse;
import com.example.rentailmotorcar.dto.response.AuthenticationResponse;
import com.example.rentailmotorcar.dto.response.IntroSpectResponse;
import com.example.rentailmotorcar.service.AuthenticationService;
import com.nimbusds.jose.JOSEException;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/permission")
@RequiredArgsConstructor
public class AuthenticationController {
   AuthenticationService authenticationService;
   // login
   @PostMapping
   public ApiResponse<AuthenticationResponse> loginUser(@RequestBody AuthenticationRequest authenticationRequest){
      return ApiResponse.<AuthenticationResponse>builder()
      .code(200)
      .results(authenticationService.loginUser(authenticationRequest))
      .build();
   }
   // check token
   @PostMapping 
   public ApiResponse<IntroSpectResponse> introspectToken(@RequestBody IntroSpectRequest introSpectRequest)throws JOSEException,ParseException{
      return ApiResponse.<IntroSpectResponse>builder()
      .code(200)
      .results(authenticationService.introspect(introSpectRequest))
      .build();
   }
}
