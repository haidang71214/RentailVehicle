package com.example.rentailmotorcar.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.rentailmotorcar.entity.Conversation;

public interface ConversationRepository extends JpaRepository<Conversation,String>  {
   List<Conversation> findByRoomChatId(String roomId);
   void deleteByRoomChatId(String roomId);
}
