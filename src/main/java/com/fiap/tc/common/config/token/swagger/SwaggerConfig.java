package com.fiap.tc.common.config.token.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

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
                .build()
                .apiInfo(apiInfo());
    }

    @Bean
    public Docket privateDocumentation(Environment env) {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Private")
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(regex("(/api/v1/private/.*)"))
                .build()
                .apiInfo(apiInfo());
    }

    @Bean
    public Docket publicDocumentation(Environment env) {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Public")
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(regex("/api/v1/public/.*"))
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title(TITLE).version(VERSION).build();
    }

}
