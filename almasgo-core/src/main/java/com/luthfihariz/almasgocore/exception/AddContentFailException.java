package com.luthfihariz.almasgocore.exception;

import com.luthfihariz.almasgocore.controller.dto.ErrorCode;
import org.springframework.http.HttpStatus;

public class AddContentFailException extends ApplicationException {
    public AddContentFailException() {
        super(String.valueOf(ErrorCode.ERROR_ADD_CONTENT_FAILURE),
                "Fail to add content please try again", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
