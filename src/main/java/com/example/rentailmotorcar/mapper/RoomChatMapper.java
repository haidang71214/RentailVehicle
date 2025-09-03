package com.example.rentailmotorcar.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.rentailmotorcar.dto.request.RoomChatRequest;
import com.example.rentailmotorcar.dto.response.RoomChatResponse;
import com.example.rentailmotorcar.entity.RoomChat;

@Mapper(componentModel = "spring")
public interface RoomChatMapper {
   @Mapping(target = "user", ignore = true)
   @Mapping(target = "id", ignore = true)
   RoomChat toRoomChat(RoomChatRequest roomChatRequest);
   RoomChatResponse toRoomChatResponse(RoomChat roomChat);
}
