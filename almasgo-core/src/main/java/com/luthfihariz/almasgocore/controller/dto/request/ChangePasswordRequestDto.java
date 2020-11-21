package com.luthfihariz.almasgocore.controller.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class ChangePasswordRequestDto{
    private String oldPassword;
    private String newPassword;
}
