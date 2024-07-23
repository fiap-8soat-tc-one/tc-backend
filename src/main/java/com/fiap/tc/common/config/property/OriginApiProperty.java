package com.fiap.tc.common.config.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "tc-backend-api")
@Data
public class OriginApiProperty {

    private final Seguranca seguranca = new Seguranca();
    private String origem;

    public Seguranca getSeguranca() {
        return seguranca;
    }


    public static class Seguranca {

        private boolean enableHttps;

        public boolean isEnableHttps() {
            return enableHttps;
        }

        public void setEnableHttps(boolean enableHttps) {
            this.enableHttps = enableHttps;
        }

    }

}
