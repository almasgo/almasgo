package com.luthfihariz.almasgocore.exception;

import com.luthfihariz.almasgocore.dto.ErrorCode;
import org.springframework.http.HttpStatus;

public class UserNotFoundException extends ApplicationException {

    public UserNotFoundException() {
        super(ErrorCode.ERROR_USER_NOT_FOUND.toString(),
                "Please check your credential", HttpStatus.UNAUTHORIZED);
    }
}
