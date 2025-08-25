package com.example.rentailmotorcar.entity;

import java.util.Set;

import com.example.rentailmotorcar.enums.NotificationStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Notification {
   @Id
   @GeneratedValue(strategy = GenerationType.UUID)
   String id; // notificaiotn_id
   String title;
   String content;
   @Enumerated(EnumType.STRING)
   NotificationStatus status;
   String link; // chứa đường dẫn
   @ManyToMany
   Set<User> users;
}
