package com.experess.news.securityconfig;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API NEWSEXPRESS")
                        .version("1.0")
                        .description("Your API Description"))
//                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
//                .components(new Components().addSecuritySchemes("bearerAuth",
//                        new SecurityScheme().type(SecurityScheme.Type.HTTP)
//                                .scheme("bearer")
//                                .bearerFormat("JWT")));
        ;
    }
}
