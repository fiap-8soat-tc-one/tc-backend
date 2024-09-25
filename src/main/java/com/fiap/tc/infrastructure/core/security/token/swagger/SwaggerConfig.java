package com.fiap.tc.infrastructure.core.security.token.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;
import java.util.List;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
@Profile({"local", "dev", "hmg"})
public class SwaggerConfig {

    private static final String TITLE = "Tech Challenge Backend APIs";
    private static final String VERSION = "1.0";

    @Bean
    public Docket api(Environment env) {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("General")
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.regex("/oauth/.*").negate())
                .paths(PathSelectors.regex("/error").negate())
                .build()
                .apiInfo(apiInfo())
                .securitySchemes(List.of(getBearer()))
                .securityContexts(Collections.singletonList(securityContext()));
    }


    @Bean
    public Docket privateDocumentation(Environment env) {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Private")
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(regex("(/api/private/v1.*)"))
                .build()
                .apiInfo(apiInfo())
                .securitySchemes(List.of(getBearer()))
                .securityContexts(Collections.singletonList(securityContext()));
    }

    @Bean
    public Docket publicDocumentation(Environment env) {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Public")
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(regex("/api/public/v1.*"))
                .build()
                .apiInfo(apiInfo())
                .securitySchemes(List.of(getBearer()))
                .securityContexts(Collections.singletonList(securityContext()));
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title(TITLE).version(VERSION).build();
    }

    private ApiKey getBearer() {
        return new ApiKey("JWT", "Authorization", "header");
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return List.of(new SecurityReference("JWT", authorizationScopes));
    }
}
