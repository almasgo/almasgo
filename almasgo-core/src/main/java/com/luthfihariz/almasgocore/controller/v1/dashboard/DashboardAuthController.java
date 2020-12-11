package com.luthfihariz.almasgocore.controller.v1.dashboard;

import com.luthfihariz.almasgocore.controller.v1.dto.request.AuthRequestDto;
import com.luthfihariz.almasgocore.controller.dto.response.AuthResponseDto;
import com.luthfihariz.almasgocore.middleware.v1.AuthMiddleware;
import com.luthfihariz.almasgocore.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/dashboard/v1/auth")
public class DashboardAuthController {

    @Autowired
    private AuthMiddleware authMiddleware;

    @PostMapping
    public AuthResponseDto login(@RequestBody AuthRequestDto loginCredential) throws Exception {
        return authMiddleware.login(new User(loginCredential.getEmail(), loginCredential.getPassword()));
    }

}