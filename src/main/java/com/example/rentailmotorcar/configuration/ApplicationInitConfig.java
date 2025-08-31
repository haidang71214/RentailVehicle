package com.example.rentailmotorcar.configuration;

import java.util.HashSet;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.rentailmotorcar.entity.Role;
import com.example.rentailmotorcar.entity.User;
import com.example.rentailmotorcar.enums.UserRole;
import com.example.rentailmotorcar.repository.UserRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Configuration
@RequiredArgsConstructor 
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ApplicationInitConfig {
   final PasswordEncoder passwordEncoder;
   @Bean
   ApplicationRunner applicationRunner(UserRepository userRepository){ // nó luôn chạy khi app khởi động
      return _ ->{
       if (userRepository.findByUsername("admin").isEmpty()) {
    Role roleAdmin = Role.builder()
            .name(UserRole.ADMIN.name()) 
            .build();

    HashSet<Role> roles = new HashSet<>();
    roles.add(roleAdmin);

    User user = User.builder()
            .username("admin")
            .password(passwordEncoder.encode("123456789"))
            .email("haidang71214@gmail.com")
            .roles(roles)
            .build();

    userRepository.save(user);
}

      };
   }   
}
