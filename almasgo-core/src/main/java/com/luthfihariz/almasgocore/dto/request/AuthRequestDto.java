package com.luthfihariz.almasgocore.dto.request;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AuthRequestDto {
    private String email;
    private String password;
}
