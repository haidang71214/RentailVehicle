package com.example.rentailmotorcar.service;

import org.springframework.stereotype.Service;

import com.example.rentailmotorcar.dto.request.UserRequestDto;
import com.example.rentailmotorcar.dto.response.UserResponseDto;
import com.example.rentailmotorcar.entity.User;
import com.example.rentailmotorcar.mapper.UserMapper;
import com.example.rentailmotorcar.repository.RoleRepository;
import com.example.rentailmotorcar.repository.UserRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@FieldDefaults(level =  AccessLevel.PRIVATE,makeFinal = true)
@Slf4j
public class UserService {
   UserRepository userRepository;
   RoleRepository roleRepository;
   UserMapper userMapper;

   // create
   // register 
   // update
   // delete

   public UserResponseDto createUser(UserRequestDto userRequestDto){
       User user = userMapper.toUser(userRequestDto);
       
   }

}
