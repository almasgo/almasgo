package com.luthfihariz.almasgocore.exception;

import com.luthfihariz.almasgocore.controller.dto.ErrorCode;
import org.springframework.http.HttpStatus;

public class ContentNotFoundException extends ApplicationException {
    public ContentNotFoundException() {
        super(ErrorCode.ERROR_CONTENT_NOT_FOUND.toString(),
                "Content not found", HttpStatus.BAD_REQUEST);
    }
}
