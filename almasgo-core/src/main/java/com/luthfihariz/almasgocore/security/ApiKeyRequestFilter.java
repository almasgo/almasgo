package com.luthfihariz.almasgocore.security;

import com.luthfihariz.almasgocore.model.principal.SearchClientPrincipal;
import com.luthfihariz.almasgocore.service.ApiKeyValidatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@Component
public class ApiKeyRequestFilter extends OncePerRequestFilter {

    @Autowired
    private ApiKeyValidatorService apiKeyValidatorService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String apiKey = request.getHeader("api-key");
        String engineId = request.getHeader("engine-id");
        if (apiKey != null && engineId != null) {
            var engine = apiKeyValidatorService.isValid(apiKey, Long.parseLong(engineId));
            if (engine != null) {
                var authorities = new ArrayList<GrantedAuthority>();
                authorities.add(new SimpleGrantedAuthority("search"));
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        new SearchClientPrincipal(engine.getUser().getId(), engine.getId()), null,
                        authorities);
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }

        filterChain.doFilter(request, response);
    }
}
