package com.fiap.tc.infrastructure.core.security.configurations;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "app.config.upload")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UploadConfig {
    private List<String> mimeTypes;
    private Integer maxLength;
    private Integer maxProductImages;
}
