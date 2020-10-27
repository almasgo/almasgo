package com.luthfihariz.almasgocore.controller.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RegisterUserResponseDto {

    private Long id;
    private String name;
    private String email;
}
