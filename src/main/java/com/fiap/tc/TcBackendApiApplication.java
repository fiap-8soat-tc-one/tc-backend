package com.fiap.tc;

import com.fiap.tc.common.config.property.OriginApiProperty;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.BufferedImageHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;

import java.awt.image.BufferedImage;


@SpringBootApplication
@EnableConfigurationProperties(OriginApiProperty.class)
public class TcBackendApiApplication {

    public static void main(String[] args) {

        SpringApplication.run(TcBackendApiApplication.class, args);
    }

    @Bean
    public HttpMessageConverter<BufferedImage> createImageHttpMessageConverter() {
        return new BufferedImageHttpMessageConverter();
    }

}
