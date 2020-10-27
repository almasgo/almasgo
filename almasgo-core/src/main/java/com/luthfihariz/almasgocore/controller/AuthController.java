package com.luthfihariz.almasgocore.controller;

import com.luthfihariz.almasgocore.controller.dto.request.AuthRequestDto;
import com.luthfihariz.almasgocore.controller.dto.response.AuthResponseDto;
import com.luthfihariz.almasgocore.model.User;
import com.luthfihariz.almasgocore.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/")
    public AuthResponseDto login(@RequestBody AuthRequestDto loginCredential) throws Exception {
        return authService.login(new User(loginCredential.getEmail(), loginCredential.getPassword()));
    }

}