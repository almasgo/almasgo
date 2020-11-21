package com.luthfihariz.almasgocore.exception;

import com.luthfihariz.almasgocore.controller.dto.ErrorCode;
import org.springframework.http.HttpStatus;

public class InvalidPasswordException extends ApplicationException {

    public InvalidPasswordException() {
        super(ErrorCode.ERROR_INVALID_PASSWORD.toString(),
                "invalid password", HttpStatus.UNAUTHORIZED);
    }
}
