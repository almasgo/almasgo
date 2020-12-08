package com.luthfihariz.almasgocore.security;

import com.luthfihariz.almasgocore.model.principal.SearchClientPrincipal;
import org.springframework.security.core.Authentication;

public interface AuthenticationFacade {

    Authentication getAuthentication();

    SearchClientPrincipal getSearchClientPrincipal();
}
