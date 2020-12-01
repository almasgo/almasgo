package com.luthfihariz.almasgocore.security;

import com.luthfihariz.almasgocore.model.principal.EnginePrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFacadeImpl implements AuthenticationFacade {

    @Override
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @Override
    public EnginePrincipal getEnginePrincipal() {
        if (getAuthentication().getPrincipal() instanceof EnginePrincipal) {
            return (EnginePrincipal) getAuthentication().getPrincipal();
        } else {
            return null;
        }
    }
}
