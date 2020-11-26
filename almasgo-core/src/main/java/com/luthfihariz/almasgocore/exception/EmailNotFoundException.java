package com.luthfihariz.almasgocore.exception;

import com.luthfihariz.almasgocore.controller.dto.ErrorCode;
import org.springframework.http.HttpStatus;

public class EmailNotFoundException extends ApplicationException {

    public EmailNotFoundException() {
        super(ErrorCode.ERROR_EMAIL_NOT_FOUND.toString(),
                "error email not found", HttpStatus.BAD_REQUEST);
    }
}
