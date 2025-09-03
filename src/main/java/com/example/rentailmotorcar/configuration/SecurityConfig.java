package com.example.rentailmotorcar.configuration;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@Configuration
@EnableWebSecurity
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@EnableMethodSecurity // thực tế thì đây là cách phân quyền được sử dụng phổ biển hơn trong các dự án
public class SecurityConfig {
    String [] PUBLIC={"/auth/**","/permission/**","/role/**","/users/**"};
    String [] SWAGGEER_PUBLIC_ENDPOINT={"/v3/api-docs/**","/swagger-ui/**","/swagger-ui.html"};
        @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(request -> request
            .requestMatchers(SWAGGEER_PUBLIC_ENDPOINT).permitAll()
            .requestMatchers(PUBLIC).permitAll()    
            // Cho phép truy cập không cần token
                .anyRequest().authenticated()           
            );
            http.csrf(AbstractHttpConfigurer::disable);

            http.oauth2ResourceServer(oauth2 -> oauth2
                .jwt(jwtConfigurer -> jwtConfigurer.decoder(jwtDecoder())
                .jwtAuthenticationConverter(jwtAuthenticationConverter()))
                .authenticationEntryPoint(new JwtAuthenticationEntryPoint()) // khi authen fail thì điều hướng user đi đâu ? 
                // Bật xác thực JWT
                // khi chưa đăng nhập thì không được truy cập những cái cần đăng nhập, có thể nó nhảy 401 trước nên mình handle 403 trước nó
            );

        return http.build();
    }
// hasAnyAuthority("ROLE_ADMIN") là 1 cách dùng, nhưng hasrole mình nghĩ là mình thích hơn
    // custom claim
    @Bean
    JwtAuthenticationConverter jwtAuthenticationConverter(){
        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        jwtGrantedAuthoritiesConverter.setAuthorityPrefix("");
        JwtAuthenticationConverter jwtAuthenticationConverter =new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
        return jwtAuthenticationConverter; 
    }
   
    @Bean
    JwtDecoder jwtDecoder(){
        SecretKeySpec secretKeySpec = new SecretKeySpec("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9".getBytes(),"HS256"); // nó chính là lấy cái secret trong cái thằng authservice
        return NimbusJwtDecoder
        .withSecretKey(secretKeySpec).macAlgorithm(MacAlgorithm.HS256).build();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(10);
    }
}