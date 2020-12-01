package com.luthfihariz.almasgocore.security;

import com.luthfihariz.almasgocore.model.principal.EnginePrincipal;
import org.springframework.security.core.Authentication;

public interface AuthenticationFacade {

    Authentication getAuthentication();

    EnginePrincipal getEnginePrincipal();
}
