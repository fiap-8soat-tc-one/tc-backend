package com.fiap.tc.adapters.driven.security.configurations;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityClientContext {

    public static Authentication get() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public static String getLogin() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
