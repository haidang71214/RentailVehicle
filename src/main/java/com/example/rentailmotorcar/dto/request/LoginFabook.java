package com.example.rentailmotorcar.dto.request;

// đại khái mấy cái này thao tác trong fe
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
@Data
@Builder
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class LoginFabook {
   String faceId;
   String email;
   String userName;
   String avatarUrl;
}
