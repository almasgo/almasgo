package com.luthfihariz.almasgocore.controller.v1.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class ForgotPasswordRequestDto {
    private String email;
}
