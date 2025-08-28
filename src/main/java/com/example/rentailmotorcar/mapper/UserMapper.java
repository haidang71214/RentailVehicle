package com.example.rentailmotorcar.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.rentailmotorcar.dto.request.UserRequestDto;
import com.example.rentailmotorcar.dto.response.UserResponseDto;
import com.example.rentailmotorcar.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
   @Mapping(target = "roles",ignore = true)
   @Mapping(target = "id",ignore = true)
   @Mapping(target = "imageUrl",ignore = true)
   @Mapping(target = "facebookId",ignore = true)
   @Mapping(target = "refreshtoken",ignore = true)
   @Mapping(target = "resetToken",ignore = true)
   User toUser(UserRequestDto userRequestDto);
   UserResponseDto toUserResponseDto(User user);
}
