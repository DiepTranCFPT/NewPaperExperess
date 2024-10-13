//package com.experess.news.securityconfig;
//
//import org.springframework.context.annotation.Configuration;
//
//
//@Configuration
////@EnableWebSecurity
//public class SecurityConfig {
//
////    private String[] PUBLIC_ENDPOINTS = {
////            "/v2/api-docs/**",
////            "/swagger-resources/**",
////            "/swagger-ui.html",
////            "/webjars/**",
////            "/swagger-ui/**",
////            "/api/auth/**",
////            "/api/test/**"
////    };
////
////
////    @Bean
//////    @Primary
////    public UserDetailsService userDetailsService() throws Exception {
////        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
////        manager.createUser(User.withUsername("user")
////                .password(getPasswordEncoder().encode("password"))
////                .roles("USER").build());
////        manager.createUser(User.withUsername("admin")
////                .password(getPasswordEncoder().encode("password"))
////                .roles("USER", "ADMIN").build());
////        return manager;
////    }
//
//
////    @Bean
////    @Order(1)
////    public SecurityFilterChain apiFilterChain(HttpSecurity http) throws Exception {
////        http
////                .csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable())
////                .httpBasic(Customizer.withDefaults());
////
////        return http.build();
////    }
////
////
////    @Bean
////    @Order(2)
////    PasswordEncoder getPasswordEncoder() {
////        return new BCryptPasswordEncoder();
////    }
//}
