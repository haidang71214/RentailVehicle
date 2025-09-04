package com.example.rentailmotorcar.service;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.example.rentailmotorcar.dto.request.ConversationRequest;
import com.example.rentailmotorcar.dto.response.ConversationResponse;
import com.example.rentailmotorcar.entity.Conversation;
import com.example.rentailmotorcar.entity.RoomChat;
import com.example.rentailmotorcar.exception.AppException;
import com.example.rentailmotorcar.exception.ErrorCode;
import com.example.rentailmotorcar.mapper.ConversationMapper;
import com.example.rentailmotorcar.repository.ConversationRepository;
import com.example.rentailmotorcar.repository.RoomChatRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Slf4j
//  hoặc chat mình sẽ dùng microservice với mongo để làm
public class MessageService {
   ConversationMapper conversationMapper;
   ConversationRepository conversationRepository;
   RoomChatRepository roomChatRepositoryl;
   // phải đăng nhập thì mới được nhắn tin
   
   @PreAuthorize("isAuthenticated()")
   public ConversationResponse createMessage(String roomId,ConversationRequest conversationRequest){
      Conversation conversation = conversationMapper.toConversation(conversationRequest);
      RoomChat roomChat = roomChatRepositoryl.findById(roomId).orElseThrow(()->new AppException(ErrorCode.ROOM_CHAT_NOT_FOUND));
      conversation.setRoomChat(roomChat);
      conversation.setCreatedAt(LocalDateTime.now());
      conversationRepository.save(conversation);
      return conversationMapper.toConversationResponse(conversation);
   }
   // lấy message trong roomchat
   public List<ConversationResponse> getMessage(String roomId){
      return conversationRepository.findByRoomChatId(roomId).stream().map(conversationMapper :: toConversationResponse).toList();
   }
   // xóa message
   public Void deleteMessage(String messageId){
   // kiểm tra message quá 30p là không thể xóa
   LocalDateTime now = LocalDateTime.now();
   Conversation conversation = conversationRepository.findById(messageId).orElseThrow(()-> new AppException(ErrorCode.MESSAGE_NOT_FOUND));
   LocalDateTime converSationChat = conversation.getCreatedAt();
   if(converSationChat.plusMinutes(30).isBefore(now)){
      throw new AppException(ErrorCode.MESSAGE_CANNOT_DELETE);
   }
   conversationRepository.deleteById(messageId);
     return null;
   }
public ConversationResponse update(String messageId,ConversationRequest conversationRequest){
   // kiểm tra message quá 30p là không thể xóa
   LocalDateTime now = LocalDateTime.now();
   Conversation conversation = conversationRepository.findById(messageId).orElseThrow(()-> new AppException(ErrorCode.MESSAGE_NOT_FOUND));
   LocalDateTime converSationChat = conversation.getCreatedAt();
   if(converSationChat.plusMinutes(30).isBefore(now)){
      throw new AppException(ErrorCode.MESSAGE_CANNOT_DELETE);
   }
   conversation.setCreatedAt(LocalDateTime.now());
   conversation.setMessage(conversationRequest.getMessage());
     return conversationMapper.toConversationResponse(conversationRepository.save(conversation));
   }
   // xóa message theo room
   public Void deleteMessageByRoomId(String roomId){
      conversationRepository.deleteByRoomChatId(roomId);
      return null;
   }
}