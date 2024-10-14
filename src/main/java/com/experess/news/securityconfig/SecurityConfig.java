package com.experess.news.securityconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig{

    private final String[] PUBLIC_ENDPOINT = {
            "/api/**",
            "/login/**",
            "/swagger-ui/**"
    };



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable())
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(PUBLIC_ENDPOINT).permitAll() // Công khai các endpoint trong PUBLIC_ENDPOINT
                        .anyRequest().authenticated() // Yêu cầu xác thực với các yêu cầu còn lại
                )
//                .addFilterBefore(, UsernamePasswordAuthenticationFilter.class) // Thêm JwtAuthenticationFilter trước UsernamePasswordAuthenticationFilter
                .httpBasic(Customizer.withDefaults()) // Cấu hình cơ bản cho HTTP Basic Auth
                .build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Sử dụng BCrypt để mã hóa mật khẩu
    }
}
