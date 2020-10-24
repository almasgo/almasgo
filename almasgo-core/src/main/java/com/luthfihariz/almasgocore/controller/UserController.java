package com.luthfihariz.almasgocore.controller;

import com.luthfihariz.almasgocore.dto.request.RegisterUserRequestDto;
import com.luthfihariz.almasgocore.dto.response.RegisterUserResponseDto;
import com.luthfihariz.almasgocore.model.User;
import com.luthfihariz.almasgocore.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @PostMapping
    public RegisterUserResponseDto register(@RequestBody RegisterUserRequestDto userRequest) {
        User user = new User(userRequest.getName(), userRequest.getEmail(), userRequest.getPassword());
        User newUser = userService.register(user);
        return new RegisterUserResponseDto(newUser.getId(), newUser.getName(), newUser.getEmail());
    }
}
