package com.example.rentailmotorcar.service;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.example.rentailmotorcar.dto.request.RoomChatRequest;
import com.example.rentailmotorcar.dto.response.RoomChatResponse;
import com.example.rentailmotorcar.entity.RoomChat;
import com.example.rentailmotorcar.exception.AppException;
import com.example.rentailmotorcar.exception.ErrorCode;
import com.example.rentailmotorcar.mapper.RoomChatMapper;
import com.example.rentailmotorcar.repository.RoomChatRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class RoomChatService {
   RoomChatRepository roomChatRepository;
   RoomChatMapper roomChatMapper;
   MessageService messageService;
   // tạo room chat 
   @PreAuthorize("isAuthenticated()")
   public RoomChatResponse createRoomChat(RoomChatRequest roomChatRequest){
      RoomChat roomChat = roomChatMapper.toRoomChat(roomChatRequest);
      return roomChatMapper.toRoomChatResponse(roomChatRepository.save(roomChat));
   }
   // xóa room chat thì xóa luôn cả message
   @PreAuthorize("isAuthenticated()")
   public Void deleteRoomChat(String roomId){
      messageService.deleteMessageByRoomId(roomId);
      roomChatRepository.deleteById(roomId);
      return null;
   }
   
  public RoomChatResponse getExistingRoomChat(String userId1, String userId2) {
    RoomChat room = roomChatRepository.findRoomByTwoUsers(userId1, userId2)
        .orElseThrow(() -> new AppException(ErrorCode.ROOM_CHAT_NOT_FOUND));
    return roomChatMapper.toRoomChatResponse(room);
}
  @PreAuthorize("isAuthenticated()")
public List<RoomChatResponse> getListRoomChat(String userId) {
    List<RoomChat> roomChats = roomChatRepository.findByUser_Id(userId);

    return roomChats.stream()
            .map(roomChatMapper::toRoomChatResponse)
            .toList();
}
 
}
