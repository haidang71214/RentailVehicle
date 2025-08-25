package com.example.rentailmotorcar.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HistoryConversation {
   @Id
   @GeneratedValue(strategy = GenerationType.UUID)
   String id;
   @ManyToOne // gán room chat id vào, nhiều mesage chỉ được nhắn trong 1 cái room
   RoomChat roomChat;
   String message;
   @ManyToOne
   User user;
}
