package com.luthfihariz.almasgocore.controller.v1.dto.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class AuthRequestDto {
    private String email;
    private String password;
}
