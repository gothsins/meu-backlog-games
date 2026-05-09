package com.guiverme.meu_backlog_games.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        final String securitySchemeName = "bearerAuth";
        return new OpenAPI()
                .info(new Info()
                        .title("Backlog de Games API")
                        .version("1.0")
                        .description("API REST para gestão de um catálogo pessoal de jogos (Backlog). Desenvolvida com Spring Boot."))
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                .components(
                        new Components()
                                .addSecuritySchemes(securitySchemeName,
                                        new SecurityScheme()
                                                .name(securitySchemeName)
                                                .type(SecurityScheme.Type.HTTP)
                                                .scheme("bearer")
                                                .bearerFormat("JWT")
                                )
                );
    }

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("backlog-games") // Nome do grupo
                .packagesToScan("com.guiverme.meu_backlog_games.controller")
                .build();
    }
}