package com.fiap.tc.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "app.config.upload")
@Data
public class UploadConfig {
    private List<String> mimeTypes;
    private Integer maxLength;
    private Integer maxProductImages;
}
