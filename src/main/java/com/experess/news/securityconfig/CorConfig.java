//package com.experess.news.securityconfig;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class CorConfig implements WebMvcConfigurer {
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//
//        registry.addMapping("/**")
//                .allowedOrigins("/**")
//                .allowedHeaders("*")
//                .allowedMethods("PUT", "POST", "GET", "DELETE")
//                .maxAge(1440000);
//    }
//}