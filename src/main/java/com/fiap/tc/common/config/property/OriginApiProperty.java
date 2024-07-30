package com.fiap.tc.common.config.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "tc-backend-api")
@Data
public class OriginApiProperty {

    private final Security security = new Security();
    private String origin;

    public Security getSecurity() {
        return security;
    }


    public static class Security {

        private boolean enableHttps;

        public boolean isEnableHttps() {
            return enableHttps;
        }

        public void setEnableHttps(boolean enableHttps) {
            this.enableHttps = enableHttps;
        }

    }

}
