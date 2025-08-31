package com.example.rentailmotorcar.service;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.rentailmotorcar.dto.request.UserRequestDto;
import com.example.rentailmotorcar.dto.response.UserResponseDto;
import com.example.rentailmotorcar.entity.Role;
import com.example.rentailmotorcar.entity.User;
import com.example.rentailmotorcar.exception.AppException;
import com.example.rentailmotorcar.exception.ErrorCode;
import com.example.rentailmotorcar.mapper.UserMapper;
import com.example.rentailmotorcar.repository.RoleRepository;
import com.example.rentailmotorcar.repository.UserRepository;

import jakarta.mail.MessagingException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@FieldDefaults(level =  AccessLevel.PRIVATE,makeFinal = true)
@Slf4j
public class UserService {
   UserRepository userRepository;
   RoleRepository roleRepository;
   UserMapper userMapper;
   CloudinaryService cloudinaryService;
   PasswordEncoder passwordEncoder;
   MailService mailService;
   // create
   // register 
   // update
   // delete
   // cái này để quyền admin

   @PreAuthorize("hasRole('ADMIN')")
   public UserResponseDto createUser(UserRequestDto userRequestDto,MultipartFile avatar) throws IOException{
       User user = userMapper.toUser(userRequestDto);
      if(avatar != null && !avatar.isEmpty()){
         user.setImageUrl(cloudinaryService.uploadFile(avatar));
      }
      user.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
      var roles = roleRepository.findAllById(userRequestDto.getRoles()); //trường hợp không tìm thấy sẽ handle trong fe
      user.setRoles(new HashSet<>(roles));
      userRepository.save(user);
      return userMapper.toUserResponseDto(user);
   }
// đăng kí thì auto là client
   public UserResponseDto registerUser(UserRequestDto userRequestDto,MultipartFile avatar) throws IOException{
         User user = userMapper.toUser(userRequestDto);
      if(avatar != null && !avatar.isEmpty()){
         user.setImageUrl(cloudinaryService.uploadFile(avatar));
      }
      Role clientRole = roleRepository.findByName("CLIENT")
      .orElseThrow(() -> new AppException(ErrorCode.ROLE_NOT_FOUND));

      user.setRoles(Set.of(clientRole));
      userRepository.save(user);
      return userMapper.toUserResponseDto(user);
   }

   
   @PreAuthorize("hasRole('ADMIN')")
   public UserResponseDto findUserbyId(String userId){
      User userhehe = userRepository.findById(userId).orElseThrow(()->(new AppException(ErrorCode.USER_NOT_FOUND)));
      return userMapper.toUserResponseDto(userhehe);
   }
   // admin update user
   // @PreAuthorize("hasRole('ADMIN')")
   public UserResponseDto updateUser(String userId,UserRequestDto userRequestDto,MultipartFile avatar) throws IOException{
      // 
      User user = userRepository.findById(userId).orElseThrow(()->new AppException(ErrorCode.USER_NOT_FOUND)); // user cũ
      if(userRequestDto.getEmail()!= null){
         user.setEmail(userRequestDto.getEmail());
      }
      if(userRequestDto.getUsername() != null){
         user.setUsername(userRequestDto.getUsername());
      }
      if(userRequestDto.getPassword() != null){
           user.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
      }
      
      if(avatar != null && !avatar.isEmpty()){
         log.info("Avatar received: name={}, size={}", avatar.getOriginalFilename(), avatar.getSize());
    user.setImageUrl(cloudinaryService.uploadFile(avatar));
    log.error("aaaaaaaaaa"); // Kiểm tra log này
      }

      // lấy mảng role mới áp vào mảng cũ
     if (userRequestDto.getRoles() != null) { // Chỉ kiểm tra null, không kiểm tra isEmpty
        Set<Role> roles = new HashSet<>();
        if (!userRequestDto.getRoles().isEmpty()) {
            roles = userRequestDto.getRoles().stream()
                .map(roleName -> roleRepository.findById(roleName)
                    .orElseThrow(() -> new AppException(ErrorCode.ROLE_NOT_FOUND)))
                .collect(Collectors.toSet());
        }
        user.setRoles(roles); // Cập nhật roles, kể cả khi rỗng
    }
    return userMapper.toUserResponseDto(userRepository.save(user));
   }
   // user update user
@PostAuthorize("returnObject.id == authentication.name")
      public UserResponseDto userUpdateUser(String userId,UserRequestDto userRequestDto,MultipartFile avatar) throws IOException{
      // 
      User user = userRepository.findById(userId).orElseThrow(()->new AppException(ErrorCode.USER_NOT_FOUND)); // user cũ
      if(userRequestDto.getEmail()!= null){
         user.setEmail(userRequestDto.getEmail());
      }
      if(userRequestDto.getUsername() != null){
         user.setUsername(userRequestDto.getUsername());
      }
      if(userRequestDto.getPassword() != null){
           user.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
      }
      if(avatar != null && !avatar.isEmpty()){
         user.setImageUrl(cloudinaryService.uploadFile(avatar));
      }

      // lấy mảng role mới áp vào mảng cũ
        if (userRequestDto.getRoles() != null && !userRequestDto.getRoles().isEmpty()) {
        Set<Role> roles = userRequestDto.getRoles().stream()
                .map(roleName -> roleRepository.findById(roleName)
                        .orElseThrow(() -> new AppException(ErrorCode.ROLE_NOT_FOUND)))
                .collect(Collectors.toSet());
        user.setRoles(roles);
    }
    return userMapper.toUserResponseDto(userRepository.save(user));
   }
// forgot password
  public Void sendCodeToEmail(String email) throws MessagingException {
   User user = userRepository.findByEmail(email).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
    String resetToken = UUID.randomUUID().toString();
    user.setResetToken(resetToken);
    userRepository.save(user);
    String subject = "Mã khôi phục mật khẩu của bạn";
    String content = "Xin chào " + user.getEmail() +
            ",\n\nMã khôi phục của bạn là: " + resetToken +
            "\nMã này sẽ hết hạn sau 15 phút.\n\nTrân trọng!";
    
     mailService.sendMail(user.getEmail(), subject, content);
   return null;
}

// reset password
 public Void checkResetToken(String token,String newPassword){
      User user = userRepository.findByResetToken(token).orElseThrow(()->new AppException(ErrorCode.RESETCODE_ERROR));
      user.setPassword(newPassword);
      return null;
 }
 // login fb
 // nếu k có thì tạo mới, nếu có thì tạo key như login
 // get all user
}
