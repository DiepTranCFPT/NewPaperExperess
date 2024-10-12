package com.experess.news.securityconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.io.IOException;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private String[] PUBLIC_ENDPOINTS;

    public SecurityConfig() throws IOException {
        EndpointsConfig endpointsConfig = EndpointsConfig.getEndpointsConfig();
        PUBLIC_ENDPOINTS = endpointsConfig.publicEndpoints.toArray(new String[0]);

    }

    @Bean
    public UserDetailsService userDetailsService() throws Exception {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("user")
                .password(getPasswordEncoder().encode("password"))
                .roles("USER").build());
        manager.createUser(User.withUsername("admin")
                .password(getPasswordEncoder().encode("password"))
                .roles("USER", "ADMIN").build());
        return manager;
    }


    @Bean
    @Order(1)
    public SecurityFilterChain apiFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable())
                .exceptionHandling(exception ->
                        exception.authenticationEntryPoint(new UnauthorizedHandler())
                )
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(PUBLIC_ENDPOINTS)
                        .permitAll()
                        .anyRequest().authenticated()
                )
                .cors(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }


    @Bean
//    @Order(2)
    PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
