package com.example.rentailmotorcar.dto.request;
import java.util.Set;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
@Data
@Builder
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class UserRequestDto{
   String username;
   @Size(min = 6, message = "PASSWORD_INVALID")
   String password;
   String imageUrls;
   @Email(message = "EMAIL_NOT_VAILID")
   String email;
   Set<String> roles;
   // Set<String> reviews; // những cái mảng nó sẽ ẩn trong mapper và gán lại ở service, hoặc để riêng ra, do nó sẽ nặng perfoment
}