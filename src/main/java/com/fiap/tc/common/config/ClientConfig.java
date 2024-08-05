package com.fiap.tc.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "app.client")
@Data
public class ClientConfig {
    private String name;
    private String secret;
    private String signKey;
    private Integer tokenExpirationTime;
    private Integer refreshTokenExpirationTime;
}
