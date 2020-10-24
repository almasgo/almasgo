package com.luthfihariz.almasgocore.service;

import com.luthfihariz.almasgocore.dto.response.AuthResponseDto;
import com.luthfihariz.almasgocore.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthService extends UserDetailsService {
    AuthResponseDto login(User user) throws Exception;
}
