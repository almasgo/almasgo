package com.luthfihariz.almasgocore.exception;

import com.luthfihariz.almasgocore.controller.dto.ErrorCode;
import org.springframework.http.HttpStatus;

public class UserAlreadyRegisteredException extends ApplicationException {

    public UserAlreadyRegisteredException() {
        super(ErrorCode.ERROR_USER_ALREADY_REGISTERED.toString(),
                "User already registered",
                HttpStatus.BAD_REQUEST);
    }
}
