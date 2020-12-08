package com.luthfihariz.almasgocore.exception;

import com.luthfihariz.almasgocore.controller.dto.ErrorCode;
import org.springframework.http.HttpStatus;

public class EngineNotFoundException extends ApplicationException {
    public EngineNotFoundException() {
        super(String.valueOf(ErrorCode.ERROR_ENGINE_NOT_FOUND),
                "Engine not found",
                HttpStatus.BAD_REQUEST);
    }
}
