package com.experess.news.securityconfig;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.io.IOException;
import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final UserDetailsService userDetailsService;

    private final String[] PUBLIC_ENDPOINT = {
            "/login/**"
    };

    private final String[] PUBLIC_API = {
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "/swagger-resources/**",
    };

    @Autowired
    public SecurityConfig(@Lazy UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(new RequestMatcher() {
                            @Override
                            public boolean matches(HttpServletRequest request) {
                                String method = request.getMethod();
                                String path = request.getRequestURI();
                                return "GET".equalsIgnoreCase(method) && Arrays.stream(PUBLIC_API).anyMatch(path::startsWith);
                            }
                        }).permitAll()
                        .anyRequest().authenticated())
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider())
                .httpBasic(Customizer.withDefaults())
                .addFilterBefore(new Filter() {
                    @Override
                    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
                            throws IOException, ServletException {
                        HttpServletRequest httpRequest = (HttpServletRequest) request;
                        String requestURI = httpRequest.getRequestURI();
                        String method = httpRequest.getMethod();

                        boolean isPublicEndpoint = Arrays.stream(PUBLIC_ENDPOINT).anyMatch(requestURI::startsWith);
                        boolean isPublicEndpointMethod = Arrays.stream(PUBLIC_API).anyMatch(requestURI::startsWith) && "GET".equalsIgnoreCase(method);

                        if (!isPublicEndpoint && !isPublicEndpointMethod) {
                            chain.doFilter(request, response);
                        } else {
                            response.setContentType("text/plain");
                            response.getWriter().write("Access to this endpoint is public.");
                        }
                    }
                }, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config)
            throws Exception {
        return config.getAuthenticationManager();
    }
}
