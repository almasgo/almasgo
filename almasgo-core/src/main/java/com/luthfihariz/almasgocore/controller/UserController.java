package com.luthfihariz.almasgocore.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.luthfihariz.almasgocore.dto.mapper.ContentMapper;
import com.luthfihariz.almasgocore.dto.request.RegisterUserRequestDto;
import com.luthfihariz.almasgocore.dto.response.ContentResponseDto;
import com.luthfihariz.almasgocore.dto.response.RegisterUserResponseDto;
import com.luthfihariz.almasgocore.model.Content;
import com.luthfihariz.almasgocore.model.User;
import com.luthfihariz.almasgocore.service.ContentService;
import com.luthfihariz.almasgocore.service.UserService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.function.Function;
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


    @GetMapping("/content")
    public List<ContentResponseDto> getContentByUserId(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                       @RequestParam(value = "size", defaultValue = "20") Integer size,
                                                       Principal principal) {
        return contentService.getPaginatedContentByUserId(principal.getName(), page, size)
                .stream()
                .map(content -> ContentMapper.toResponseDto(objectMapper, content))
                .collect(Collectors.toList());
    }
}
