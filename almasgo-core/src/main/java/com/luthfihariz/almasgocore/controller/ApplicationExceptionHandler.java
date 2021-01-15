package com.luthfihariz.almasgocore.controller;

import com.luthfihariz.almasgocore.controller.dto.ErrorCode;
import com.luthfihariz.almasgocore.controller.dto.ErrorDto;
import com.luthfihariz.almasgocore.exception.ApplicationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
@RestController
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(ApplicationExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {

        ErrorDto errorDto;
        HttpStatus httpStatus;
        if (ex instanceof ApplicationException) {
            ApplicationException appException = (ApplicationException) ex;
            errorDto = new ErrorDto(appException.getErrorCode(), appException.getErrorMessage());
            httpStatus = appException.getHttpStatus();
        } else if (ex instanceof InternalAuthenticationServiceException) {
            errorDto = new ErrorDto(ErrorCode.ERROR_WRONG_CREDENTIAL.toString(), "Wrong credential");
            httpStatus = HttpStatus.UNAUTHORIZED;
        } else{
            errorDto = new ErrorDto(ErrorCode.ERROR_INTERNAL_SERVER.toString(), "Internal server error");
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        logger.error("Exception Thrown", ex);

        return new ResponseEntity<>(errorDto, httpStatus);
    }
}
