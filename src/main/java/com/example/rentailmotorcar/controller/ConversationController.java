package com.example.rentailmotorcar.controller;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.rentailmotorcar.dto.request.ConversationRequest;
import com.example.rentailmotorcar.dto.response.ApiResponse;
import com.example.rentailmotorcar.dto.response.ConversationResponse;
import com.example.rentailmotorcar.service.MessageService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/conversation")
@RequiredArgsConstructor
@FieldDefaults(level =  AccessLevel.PRIVATE,makeFinal = true)
public class ConversationController {
   MessageService messageService;

   @PostMapping("{id}")
   public ApiResponse<ConversationResponse> sendMessage(
      @PathVariable("id") String id,
      @RequestBody ConversationRequest conversationRequest
   ){
      return ApiResponse.<ConversationResponse>builder()
      .code(200)
      .results(messageService.createMessage(id, conversationRequest))
      .build();
   } 
   //  get id from room
   @GetMapping("{id}")
   public ApiResponse<List<ConversationResponse>> getConversation(
      @PathVariable("id") String id
   ){
      return ApiResponse.<List<ConversationResponse>>builder()
      .code(200)
      .results(messageService.getMessage(id))
      .build();
   }
   @DeleteMapping("{id}")
   public ApiResponse<Void> deleteMessage(
      @PathVariable("id") String id
   ){
      return ApiResponse.<Void>builder()
      .code(200)
      .results(messageService.deleteMessage(id))
      .build();
   }
   @PatchMapping("{id}")
   public ApiResponse<ConversationResponse> updateMessage(
      @PathVariable("id") String id,
      @RequestBody ConversationRequest conversationRequest
   ){
      return ApiResponse.<ConversationResponse>builder()
      .code(200)
      .results(messageService.update(id, conversationRequest))
      .build();
   }
}
