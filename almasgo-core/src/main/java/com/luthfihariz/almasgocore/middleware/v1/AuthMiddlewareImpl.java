package com.luthfihariz.almasgocore.middleware.v1;

import com.luthfihariz.almasgocore.controller.dto.response.AuthResponseDto;
import com.luthfihariz.almasgocore.model.User;
import com.luthfihariz.almasgocore.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthMiddlewareImpl implements AuthMiddleware{

    @Autowired
    private AuthService authService;

    @Override
    public AuthResponseDto login(User user) throws Exception{
        return authService.login(user);
    }
}
