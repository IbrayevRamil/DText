package com.dtproject.services;

import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {

    private SecurityUtils() {
    }

    public static boolean isCurrentAuthenticationAnonymous(AuthenticationTrustResolver trustResolver) {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return trustResolver.isAnonymous(authentication);
    }
}
