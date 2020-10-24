package com.luthfihariz.almasgocore.dto.request;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RegisterUserRequestDto {

    private String email;
    private String password;
    private String name;
}
