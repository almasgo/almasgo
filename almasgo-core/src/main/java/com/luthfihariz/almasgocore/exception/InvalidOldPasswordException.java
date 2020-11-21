package com.luthfihariz.almasgocore.exception;

import com.luthfihariz.almasgocore.controller.dto.ErrorCode;
import org.springframework.http.HttpStatus;

public class InvalidOldPasswordException extends ApplicationException {

    public InvalidOldPasswordException() {
        super(ErrorCode.ERROR_INVALID_OLD_PASSWORD.toString(),
                "invalid old password", HttpStatus.UNAUTHORIZED);
    }
}
