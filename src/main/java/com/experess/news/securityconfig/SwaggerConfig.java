package com.experess.news.securityconfig;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springdoc.core.properties.SpringDocConfigProperties;
import org.springdoc.core.properties.SwaggerUiConfigParameters;
import org.springdoc.core.properties.SwaggerUiConfigProperties;
import org.springdoc.webmvc.ui.SwaggerWelcomeCommon;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@OpenAPIDefinition(info = @io.swagger.v3.oas.annotations.info.Info(title = "Authentication Service", version = "v0.0.1", description = "This is auth service used for validating the user."))
@Configuration
@EnableWebMvc
@EnableSwagger2
public class SwaggerConfig implements WebMvcConfigurer {
    private final SwaggerUiConfigProperties swaggerUiConfigProperties;
    private final SwaggerUiConfigParameters swaggerUiConfigParameters;
    private final SpringDocConfigProperties springDocConfigProperties;

    public SwaggerConfig(SwaggerUiConfigProperties swaggerUiConfigProperties,
                         SwaggerUiConfigParameters swaggerUiConfigParameters,
                         SpringDocConfigProperties springDocConfigProperties) {
        this.swaggerUiConfigProperties = swaggerUiConfigProperties;
        this.swaggerUiConfigParameters = swaggerUiConfigParameters;
        this.springDocConfigProperties = springDocConfigProperties;
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("My API")
                        .version("v0.0.1")
                        .description("API Documentation"));
    }

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
               .select()
               .apis(RequestHandlerSelectors.any())
               .paths(PathSelectors.any())
               .build();
    }

//    @Bean
//    public OpenAPI customOpenAPI() {
//        final String securitySchemeName = "bearerAuth";
//        return new OpenAPI()
//                .info(new Info().title("Authentication Service")
//                        .description("This is auth service use for validate the user.")
//                        .version("v0.0.1")
//                        .license(new License().name("Apache 2.0").url("https://www.apache.org/licenses/LICENSE-2.0.html")))
//                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
//                .components(
//                        new Components()
//                                .addSecuritySchemes(securitySchemeName,
//                                        new SecurityScheme()
//                                                .name(securitySchemeName)
//                                                .type(SecurityScheme.Type.HTTP)
//                                                .scheme("bearer")
//                                                .bearerFormat("JWT")
//                                )
//                );
//    }

    @Bean
    public SwaggerWelcomeCommon swaggerWelcomeCommon() {
        return new SwaggerWelcomeCommon(swaggerUiConfigProperties, springDocConfigProperties, swaggerUiConfigParameters) {
            @Override
            protected String buildUrlWithContextPath(String swaggerUiUrl) {
                return "/swagger-ui/index.html";
            }

            @Override
            protected void calculateUiRootPath(StringBuilder... sbUrls) {
            }

            @Override
            protected String buildApiDocUrl() {
                return "/v3/api-docs";
            }

            @Override
            protected String buildSwaggerConfigUrl() {
                return "/v3/api-docs/swagger-config";
            }
        };
    }

    @Bean
    public GroupedOpenApi groupedOpenApi() {
        return GroupedOpenApi.builder()
                .group("public")
                .pathsToMatch("/api/**")
                .build();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/swagger-ui/**");
    }
}
