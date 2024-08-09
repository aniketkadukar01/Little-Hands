package com.app.security;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerConfig {

    private static final String BEARER_KEY = "bearer-key";

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(createApiInfo())
                .addSecurityItem(new SecurityRequirement().addList(BEARER_KEY))
                .components(createSecurityComponents());
    }

    private Info createApiInfo() {
        return new Info()
                .title("My REST API")
                .description("Some custom description of API.")
                .version("1.0")
                .contact(new Contact()
                        .name("Sallo Szrajbman")
                        .url("https://www.baeldung.com")
                        .email("salloszraj@gmail.com"))
                .license(new License()
                        .name("License of API")
                        .url("https://example.com/api-license"));
    }

    private io.swagger.v3.oas.models.Components createSecurityComponents() {
        return new io.swagger.v3.oas.models.Components()
                .addSecuritySchemes(BEARER_KEY, new SecurityScheme()
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("bearer")
                        .bearerFormat("JWT"));
    }

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("public")
                .pathsToMatch("/**")
                .build();
    }
}

