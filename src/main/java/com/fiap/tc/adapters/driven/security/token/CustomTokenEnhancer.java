package com.fiap.tc.common.config.token;

import com.fiap.tc.adapters.driven.security.SystemUser;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

public class CustomTokenEnhancer implements TokenEnhancer {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        SystemUser systemUser = (SystemUser) authentication.getPrincipal();

        Map<String, Object> addInfo = new HashMap<>();
        addInfo.put("uuid", systemUser.getUser().getUuid());
        addInfo.put("name", systemUser.getUser().getName());
        addInfo.put("profile", systemUser.getUser().getProfiles().stream().findFirst().get().getName());

        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(addInfo);
        return accessToken;
    }

}
