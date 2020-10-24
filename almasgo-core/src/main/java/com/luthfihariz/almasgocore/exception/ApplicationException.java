package com.luthfihariz.almasgocore.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Accessors
@Getter
public class ApplicationException extends RuntimeException {
    private String errorCode;
    private String errorMessage;
    private HttpStatus httpStatus;
}
