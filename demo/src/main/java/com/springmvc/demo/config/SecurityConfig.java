package com.springmvc.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/resources/**").permitAll() // Cho phép truy cập file tĩnh
                        .anyRequest().permitAll() // Các request khác cũng được phép
                )
                .csrf(csrf -> csrf.disable()) // Tắt CSRF nếu không cần
                .formLogin(login -> login.disable()) // Tắt trang login
                .httpBasic(basic -> basic.disable()); // Tắt xác thực Basic Auth

        return http.build();
    }
}

// KHI CẦN BẬT LẠI SECURITY

// @Bean
// public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//     http
//         .authorizeHttpRequests(auth -> auth
//             .requestMatchers("/admin/**").authenticated() // Chỉ trang `/admin` yêu cầu login
//             .anyRequest().permitAll()
//         )
//         .formLogin(withDefaults()); // Bật lại trang login

//     return http.build();
// }