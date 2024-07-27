package com.fiap.tc.common.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "app.feature.toggles")
@Configuration
@Getter
@Setter
public class AppConfiguration {
    private Boolean orderPaymentMockSuccess;
}
