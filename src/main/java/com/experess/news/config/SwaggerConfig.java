package com.experess.news.config;

import org.springdoc.core.GroupedOpenApi;
import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springdoc.core.properties.SpringDocConfigProperties;
import org.springdoc.core.properties.SwaggerUiConfigProperties;
import org.springdoc.core.providers.SpringWebProvider;
import org.springdoc.webmvc.ui.SwaggerWelcomeCommon;
import org.springdoc.webmvc.ui.SwaggerWelcomeWebMvc;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.RouterFunctions;
import org.springframework.web.servlet.function.ServerResponse;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi api() {
        return GroupedOpenApi.builder()
                .group("public-api")
                .pathsToMatch("/**")
                .build();
    }

    @RouterOperations({ @RouterOperation(path = "/getAllPersons", beanClass = PersonService.class, beanMethod = "getAll"),
            @RouterOperation(path = "/getPerson/{id}", beanClass = PersonService.class, beanMethod = "getById"),
            @RouterOperation(path = "/createPerson", beanClass = PersonService.class, beanMethod = "save"),
            @RouterOperation(path = "/deletePerson/{id}", beanClass = PersonService.class, beanMethod = "delete") })
    @Bean
    public RouterFunction<ServerResponse> personRoute(PersonHandler handler) {
        return RouterFunctions
                .route(GET("/getAllPersons").and(accept(MediaType.APPLICATION_JSON)), handler::findAll)
                .andRoute(GET("/getPerson/{id}").and(accept(MediaType.APPLICATION_STREAM_JSON)), handler::findById)
                .andRoute(POST("/createPerson").and(accept(MediaType.APPLICATION_JSON)), handler::save)
                .andRoute(DELETE("/deletePerson/{id}").and(accept(MediaType.APPLICATION_JSON)), handler::delete);
    }



    @Bean
    @ConditionalOnMissingBean
    public SwaggerWelcomeWebMvc swaggerWelcome(SwaggerUiConfigProperties swaggerUiConfig,
                                               SpringDocConfigProperties springDocConfigProperties,
                                               SwaggerWelcomeCommon swaggerWelcomeCommon,
                                               SpringWebProvider springWebProvider) {
        return new SwaggerWelcomeWebMvc(swaggerUiConfig, springDocConfigProperties, swaggerWelcomeCommon, springWebProvider);
    }
}
