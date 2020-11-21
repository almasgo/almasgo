package com.luthfihariz.almasgocore.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.luthfihariz.almasgocore.controller.dto.mapper.ContentMapper;
import com.luthfihariz.almasgocore.controller.dto.request.ChangePasswordRequestDto;
import com.luthfihariz.almasgocore.controller.dto.request.RegisterUserRequestDto;
import com.luthfihariz.almasgocore.controller.dto.response.ContentResponseDto;
import com.luthfihariz.almasgocore.controller.dto.response.RegisterUserResponseDto;
import com.luthfihariz.almasgocore.model.Content;
import com.luthfihariz.almasgocore.model.User;
import com.luthfihariz.almasgocore.service.ContentService;
import com.luthfihariz.almasgocore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ContentService contentService;

    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping
    public RegisterUserResponseDto register(@RequestBody RegisterUserRequestDto userRequest) {
        User user = new User(userRequest.getName(), userRequest.getEmail(), userRequest.getPassword());
        User newUser = userService.register(user);
        return new RegisterUserResponseDto(newUser.getId(), newUser.getName(), newUser.getEmail());
    }


    @PostMapping("password")
    public ResponseEntity<Void> changePassword(@RequestBody ChangePasswordRequestDto passwordRequest,
                                               Principal principal) {
        userService.changePassword(passwordRequest.getOldPassword(), passwordRequest.getNewPassword(), principal.getName());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
